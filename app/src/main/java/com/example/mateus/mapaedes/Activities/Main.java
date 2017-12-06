package com.example.mateus.mapaedes.Activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mateus.mapaedes.Fragments.AdicionarCaso;
import com.example.mateus.mapaedes.Fragments.AdicionarFoco;
import com.example.mateus.mapaedes.Fragments.Buscar;
import com.example.mateus.mapaedes.Fragments.Configuracoes;
import com.example.mateus.mapaedes.Fragments.Informacoes;
import com.example.mateus.mapaedes.Fragments.MeusCasosAdicionados;
import com.example.mateus.mapaedes.R;
import com.example.mateus.mapaedes.helpers.Disease;
import com.example.mateus.mapaedes.helpers.LoggedUser;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdicionarCaso.OnFragmentInteractionListener,
        AdicionarFoco.OnFragmentInteractionListener,
        Configuracoes.OnFragmentInteractionListener,
        Informacoes.OnFragmentInteractionListener,
        MeusCasosAdicionados.OnFragmentInteractionListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private String mUser;
    private String[] mUsers;
    private Menu mMenu;
    public FragmentManager fm = getSupportFragmentManager();
    public static GoogleMap mMap;
    public GoogleApiClient client;
    public GoogleApiClient mGoogleApiClient = null;
    public SupportMapFragment mFragMap = null;
    public static LatLng positionMarker;
    protected LocationManager locationManager;


    @BindView(R.id.content_main)
    RelativeLayout mContentMain;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;


    List<LoggedUser> cityUser = LoggedUser.listAll(LoggedUser.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        searchData();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mMenu = navigationView.getMenu();
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

        buildGoogleApiClient();
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }

    private void LocalizacaoAtual() {
        Toast.makeText(this, " rola", Toast.LENGTH_SHORT).show();

    }

    private void searchData() {
        mUsers = getResources().getStringArray(R.array.users);
        mUser = mUsers[0];
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment frag = null;
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        int id = item.getItemId();

        List<LoggedUser> userTotal = LoggedUser.listAll(LoggedUser.class);
        // 0 ou 1
        int tipo = userTotal.get(0).getUser().getType();
        if (tipo == 0) {
            mMenu.findItem(R.id.nav_title_focos).setVisible(false);
            mMenu.findItem(R.id.nav_title_dois).setVisible(false);
            configuraTitle(mMenu.findItem(R.id.nav_title_casos));
            switch (item.getItemId()) {
                case R.id.nav_map:

                    mFragMap = new SupportMapFragment();
                    mFragMap.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mMap = googleMap;

                            try {
                                configuraMapa();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //gotoLocation(CG_LAT,CG_LGT, DEFAULTZOM);
                        }


                    });


                    fm.beginTransaction()
                            .replace(R.id.Conteiner, mFragMap)
                            .addToBackStack("")
                            .commit();

                    break;
                case R.id.nav_addcasos:
                    frag = new AdicionarCaso();
                    trocaFrag(fm, frag);
                    break;
                case R.id.nav_listarcasos:
                    frag = new MeusCasosAdicionados();
                    trocaFrag(fm, frag);
                    break;
                case R.id.nav_buscar:
                    frag = new Buscar();
                    trocaFrag(fm, frag);
                    break;
                case R.id.nav_info:
                    frag = new Informacoes();
                    trocaFrag(fm, frag);
                    break;
                case R.id.nav_settings:
                    frag = new Configuracoes();
                    trocaFrag(fm, frag);
                    break;
                case R.id.nav_logout:
                    sair();
                    break;
            }
            //FOCO
        } else if (tipo == 1) {
            mMenu.findItem(R.id.nav_title_casos).setVisible(false);
            mMenu.findItem(R.id.nav_title_dois).setVisible(false);
            configuraTitle(mMenu.findItem(R.id.nav_title_focos));
            switch (item.getItemId()) {
                case R.id.nav_map:
                    mFragMap = new SupportMapFragment();
                    mFragMap.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mMap = googleMap;

                            try {
                                configuraMapa();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //gotoLocation(CG_LAT,CG_LGT, DEFAULTZOM);
                        }


                    });
                    fm.beginTransaction()
                            .replace(R.id.Conteiner, mFragMap)
                            .addToBackStack("")
                            .commit();

                    break;
                case R.id.nav_addfoco:
                    frag = new AdicionarFoco();
                    trocaFrag(fm, frag);
                    break;
                case R.id.nav_listarfoco:
                    frag = new MeusCasosAdicionados();
                    trocaFrag(fm, frag);
                    break;
                case R.id.nav_buscar:
                    frag = new Buscar();
                    trocaFrag(fm, frag);
                    break;
                case R.id.nav_info:
                    frag = new Informacoes();
                    trocaFrag(fm, frag);
                    break;
                case R.id.nav_settings:
                    frag = new Configuracoes();
                    trocaFrag(fm, frag);
                    break;
                case R.id.nav_logout:
                    sair();
                    break;
            }
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void configuraMapa() throws IOException {

        Double lat = cityUser.get(0).getUser().getLat();
        Double lng = cityUser.get(0).getUser().getLng();

        LatLng latLng = new LatLng(lat, lng);


        final CameraPosition cp = new CameraPosition.Builder().target(latLng).zoom(13).bearing(0).tilt(00).build();
        CameraUpdate cam = CameraUpdateFactory.newCameraPosition(cp);
        mMap.moveCamera(cam);


        List<Disease> diseasesList = Disease.listAll(Disease.class);
        for (int i = 0; i < diseasesList.size(); i++) {
            String disease = diseasesList.get(i).getDisease();
            String name = diseasesList.get(i).getNameUser();
            String address = diseasesList.get(i).getAddress();

            Double latP = diseasesList.get(i).getLat();
            Double lngP = diseasesList.get(i).getLng();

            positionMarker = new LatLng(latP, lngP);

            switch (disease) {
                case "Dengue":
                    MarkerOptions options = new MarkerOptions()
                            .title(name + " - Dengue")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_RED))

                            .position(positionMarker);

                    mMap.addMarker(options);
                    break;
                case "Zika vírus":
                    MarkerOptions options1 = new MarkerOptions()
                            .title(name + " - Zika vírus")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_GREEN))

                            .position(positionMarker);

                    mMap.addMarker(options1);
                    break;
                case "Chikungunya":
                    MarkerOptions options2 = new MarkerOptions()
                            .title(name + " - Chicungunya")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_AZURE))
                            .position(positionMarker);

                    mMap.addMarker(options2);
                    break;
                case "Nyongnyong":
                    MarkerOptions options3 = new MarkerOptions()
                            .title(name + " - Nyongnyong")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_ORANGE))
                            .position(positionMarker);

                    mMap.addMarker(options3);
                    break;
                case "Guillaint barré":
                    MarkerOptions options4 = new MarkerOptions()
                            .title(name + " - Guillaint barré")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_YELLOW))
                            .position(positionMarker);

                    mMap.addMarker(options4);
                    break;
                case "Foco":
                    MarkerOptions options5 = new MarkerOptions()
                            .title(name + " - Foco")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_AZURE))
                            .position(positionMarker);

                    mMap.addMarker(options5);
                    drawCircle(positionMarker);
                    //BitmapDescriptorFactory.fromResource(R.mipmap.point
                    break;

            }
        }

    }


    public static void drawCircle(LatLng point) {

      /*  // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(point);

        // Radius of the circle
        circleOptions.radius(500);

        // Border color of the circle
        circleOptions.strokeColor(Color.BLACK);

        // Fill color of the circle
        circleOptions.fillColor(0x550000);

        // Border width of the circle
        circleOptions.strokeWidth(2);

        // Adding the circle to the GoogleMap
        mMap.addCircle(circleOptions);

        */
        for (int rad = 100; rad <= 500; rad = rad + 100) {

            CircleOptions circleOptions = new CircleOptions()
                    .center(point)   //set center
                    .radius(rad)   //set radius in meters
                    .fillColor(Color.TRANSPARENT)  //default
                    .strokeColor(0x1000000)
                    .strokeWidth(5);

            mMap.addCircle(circleOptions);
        }

    }

    private void sair() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Logout");
        alertDialog.setMessage("Caso saia, você será deslogado de sua conta");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
               /* SQLiteDatabase banco = bd.getReadableDatabase();

                banco.execSQL("DELETE FROM login"); //delete all rows in a table*/

                List<LoggedUser> loggedUsers = LoggedUser.listAll(LoggedUser.class);

                LoggedUser.deleteAll(LoggedUser.class);
                Intent myIntent = new Intent(((Dialog) dialog).getContext(), Login.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);

                return;
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent myIntent = new Intent(((Dialog) dialog).getContext(), Main.class);
                myIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(myIntent);

            }
        });

        alertDialog.show();
    }

    private void trocaFrag(FragmentManager fragmentManager, Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.Conteiner, fragment)
                .addToBackStack("")
                .commit();
    }

    private void configuraTitle(MenuItem tools) {
        SpannableString s = new SpannableString(tools.getTitle());
        s.setSpan(new TextAppearanceSpan(this, R.style.Title), 0, s.length(), 0);
        tools.setTitle(s);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public GoogleApiClient getmGoogleApiClient() {
        if (mGoogleApiClient == null) {
            buildGoogleApiClient();
        }
        return mGoogleApiClient;
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .enableAutoManage(this, 0 /* clientId */, this)
                .addApi(Places.GEO_DATA_API)
                .build();
    }

    public void PinarMapa(View v) throws IOException {
        try {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

            String datePerson = df.format(c.getTime());
            String namePerson = AdicionarCaso.nomeE.getText().toString();
            String addressPerson = AdicionarCaso.Endereço.getText().toString();
            String diseasePerson = (String) AdicionarCaso.spinner.getSelectedItem();

            Geocoder gcc = new Geocoder(this);
            List<Address> list = gcc.getFromLocationName(addressPerson, 1);
            Address address = list.get(0);

            final double lat = address.getLatitude();

            final double lng = address.getLongitude();

            // 0 ou 1 no get()

            long idUser = cityUser.get(0).getId();

            Disease disease = new Disease();

            disease.setRegisterID(idUser);
            disease.setDate(datePerson);
            disease.setDisease(diseasePerson);
            disease.setNameUser(namePerson);
            disease.setAddress(addressPerson);
            disease.setLat(lat);
            disease.setLng(lng);

            disease.save();

            Toast.makeText(this, " Case registered", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Toast.makeText(this, " O caso não pode ser registrado. Conecte-se a internet, e tente" +
                    " novamente.", Toast.LENGTH_SHORT).show();
        }
    }


    public void onFragmentInteraction(Uri uri) {

    }

    public void LOCATUAL(View v) {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    @Override
    public void onLocationChanged(Location location) {


        double lat = location.getLatitude();
        double lng = location.getLongitude();

        Toast.makeText(this, " Lat:" + location.getLatitude() + "Lng: " + location.getLongitude(), Toast.LENGTH_SHORT).show();


        // adicionarFoco.endereço.setText();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public void ADICIONARFOCO(View view) {

        String addressFocus = AdicionarFoco.endereço.getText().toString().toLowerCase();
        Geocoder geocoder;
        geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> list = null;

        try {
            list = geocoder.getFromLocationName(addressFocus, 1);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Address add = list.get(0);

        final double lat = add.getLatitude();
        final double lng = add.getLongitude();

        String foco = "Foco";

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        String datePerson = df.format(c.getTime());

        // 0 ou 1 no get()

        long idUser = cityUser.get(0).getId();


        Disease disease = new Disease();

        disease.setRegisterID(idUser);
        disease.setDate(datePerson);
        disease.setDisease(foco);
        disease.setNameUser(foco);
        disease.setAddress(foco);
        disease.setLat(lat);
        disease.setLng(lng);

        disease.save();
        Toast.makeText(this, "Focus registered!", Toast.LENGTH_SHORT).show();
    }


}