package com.example.mateus.mapaedes.Activities;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdicionarCaso.OnFragmentInteractionListener,
        AdicionarFoco.OnFragmentInteractionListener,
        Configuracoes.OnFragmentInteractionListener,
        Informacoes.OnFragmentInteractionListener,
        MeusCasosAdicionados.OnFragmentInteractionListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

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
    public static final String BASE_URL = "http://localhost/mapaedes/model/get_model.php?type=getAllFromTable&table=wp_disease";


    @BindView(R.id.content_main)
    RelativeLayout mContentMain;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;


    public Date dateD;
    public String nameD;
    public String addressD;
    public String diseaseD;
    public String typeD;
    public Double lat=0.2, lng=1.1;
    public long idUser;



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
        //Casos
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
        } else if (tipo == 2) {
            mMenu.findItem(R.id.nav_title_casos).setVisible(false);
            mMenu.findItem(R.id.nav_title_dois).setVisible(false);
            mMenu.findItem(R.id.nav_title_focos).setVisible(false);
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
                        }


                    });
                    fm.beginTransaction()
                            .replace(R.id.Conteiner, mFragMap)
                            .addToBackStack("")
                            .commit();
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
            //String name = diseasesList.get(i).getNameUser();
            String address = diseasesList.get(i).getAddress();

            Double latP = diseasesList.get(i).getLat();
            Double lngP = diseasesList.get(i).getLng();

            positionMarker = new LatLng(latP, lngP);

            switch (disease) {
                case "Dengue":
                    MarkerOptions options = new MarkerOptions()
                            //.title(name + " - Dengue")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_RED))

                            .position(positionMarker);

                    mMap.addMarker(options);
                    break;
                case "Zika":
                    MarkerOptions options1 = new MarkerOptions()
                            //.title(name + " - Zika vírus")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_GREEN))

                            .position(positionMarker);

                    mMap.addMarker(options1);
                    break;
                case "Chicungunya":
                    MarkerOptions options2 = new MarkerOptions()
                            //.title(name + " - Chicungunya")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_AZURE))
                            .position(positionMarker);

                    mMap.addMarker(options2);
                    break;
                case "Nyongnyong":
                    MarkerOptions options3 = new MarkerOptions()
                            //.title(name + " - Nyongnyong")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_ORANGE))
                            .position(positionMarker);

                    mMap.addMarker(options3);
                    break;
                case "Guillain barré":
                    MarkerOptions options4 = new MarkerOptions()
                            //.title(name + " - Guillaint barré")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.defaultMarker(
                                    BitmapDescriptorFactory.HUE_YELLOW))
                            .position(positionMarker);

                    mMap.addMarker(options4);
                    break;
                case "Foco":
                    MarkerOptions options5 = new MarkerOptions()
                            //.title(name + " - Foco")
                            .snippet(address)
                            .icon(BitmapDescriptorFactory.fromResource(R.mipmap.marker_black))
                            .position(positionMarker);

                    mMap.addMarker(options5);
                    drawCircle(positionMarker);
                    //BitmapDescriptorFactory.fromResource(R.mipmap.point
                    break;

            }
        }

    }


    public static void drawCircle(LatLng point) {

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();

        // Specifying the center of the circle
        circleOptions.center(point);

        // Radius of the circle
        circleOptions.radius(300);

        // Border color of the circle
        circleOptions.strokeColor(Color.RED);

        // Fill color of the circle
        circleOptions.fillColor(0x35ff0000);

        // Border width of the circle
        circleOptions.strokeWidth(2);

        // Adding the circle to the GoogleMap
        mMap.addCircle(circleOptions);


       /* for (int rad = 50; rad <= 150; rad = rad + 50) {

            CircleOptions circleOptions = new CircleOptions();
            circleOptions.center(point);   //set center
            circleOptions.radius(rad);  //set radius in meters
            circleOptions.fillColor(Color.argb(120, 255, 0, 0));  //default
            circleOptions.strokeColor(Color.TRANSPARENT);
            circleOptions.strokeWidth(5);

            mMap.addCircle(circleOptions);
        }*/

    }

    private void sair() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Logout");
        alertDialog.setMessage("Caso saia, você será deslogado de sua conta");
        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                LoggedUser.executeQuery("DELETE FROM LOGGED_USER");

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
            typeD = "focus";
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

             dateD = df.parse(df.format(c.getTime()));
             nameD= AdicionarCaso.nomeE.getText().toString();
             addressD = AdicionarCaso.Endereço.getText().toString();
             diseaseD = (String) AdicionarCaso.spinner.getSelectedItem();

            Geocoder gcc = new Geocoder(this);
            List<Address> list = gcc.getFromLocationName(addressD, 1);
            Address address = list.get(0);

            lat = address.getLatitude();

             lng = address.getLongitude();

            // 0 ou 1 no get()

             idUser = cityUser.get(0).getId();

            Disease disease = new Disease();

            //disease.setRegisterID(idUser);
            disease.setDate(dateD);
            disease.setDisease(diseaseD);
            //disease.setNameUser(namePerson);
            disease.setAddress(addressD);
            disease.setLat(lat);
            disease.setLng(lng);

            disease.save();

            //  Toast.makeText(this, R.string.caso_sucesso, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "sucesso", Toast.LENGTH_SHORT).show();

            postData();

            AdicionarCaso.Endereço.setText("");
            AdicionarCaso.nomeE.setText("");
        } catch (IOException e) {
            // Toast.makeText(this, R.string.caso_falho, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "falha", Toast.LENGTH_SHORT).show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void onFragmentInteraction(Uri uri) {

    }

    public void LOCATUAL(View v) {

        callConnection();
    }

    private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    // LISTENER
    @Override
    public void onConnected(Bundle bundle) {
        Log.i("LOG", "onConnected(" + bundle + ")");

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location l = LocationServices
                .FusedLocationApi
                .getLastLocation(mGoogleApiClient);

        if(l != null){
            Log.i("LOG", "latitude: "+l.getLatitude());
            Log.i("LOG", "longitude: "+l.getLongitude());

            AdicionarFoco adicionarFoco = new AdicionarFoco();
          //  adicionarFoco.endereço.setText(l.getLatitude()+" | "+l.getLongitude());
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i("LOG", "onConnectionSuspended(" + i + ")");
    }
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i("LOG", "onConnectionFailed("+connectionResult+")");
    }

    public void ADICIONARFOCO(View view) throws IOException {
        typeD= "focus";
        diseaseD = "focus";

        addressD = AdicionarFoco.endereço.getText().toString().toLowerCase();
        Geocoder geocoder;
        geocoder = new Geocoder(this, Locale.getDefault());
        List<Address> list = null;

        try {
            list = geocoder.getFromLocationName(addressD, 1);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Address add = list.get(0);

        lat = add.getLatitude();
        lng = add.getLongitude();

        String foco = "Foco";


        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        dateD = null;
        try {
            dateD = df.parse(df.format(c.getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // 0 ou 1 no get()

        idUser = cityUser.get(0).getId();


        Disease disease = new Disease();


        disease.setDate(dateD);
        disease.setDisease(foco);
        //disease.setNameUser(foco);
        disease.setAddress(addressD);
        disease.setLat(lat);
        disease.setLng(lng);

        disease.save();

        postData();
        Toast.makeText(this, "Foco Registrado!", Toast.LENGTH_SHORT).show();

    }


    public void postData() {
      /*  try {
            int user = 1;
            //type=case&disease=dengue&address=aa&lat=11&lng=12&userID=1
            URL httpbinEndpoint = new URL("http://mapaedes.net/wp-json/api/v1/disease");
            HttpURLConnection myConnection
                    = (HttpURLConnection) httpbinEndpoint.openConnection();

            myConnection.setRequestMethod("POST");

            typeD = "case";
            diseaseD = "dengue";
            addressD = "Rua Jintoku Minei, 45";
            lat = 54.155552;
            lng = 42.664512;



            // Create the data
            String disease = "type=" + typeD + "&disease=" + diseaseD + "&address=" + addressD + "&lat=" + lat +
                    "&lng=" + lng + "&userID=" + user;

            Log.v("DOENÇAS", String.valueOf(disease));

            // Enable writing
            myConnection.setDoOutput(true);

            // Write the data
            myConnection.getOutputStream().write(disease.getBytes());

        }catch (IOException e ){
            e.printStackTrace();
        }
        */
        RequestQueue MyRequestQueue = Volley.newRequestQueue(this);
        String url = "http://mapaedes.net/wp-json/api/v1/disease";
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();


                //type=case&disease=dengue&address=aa&lat=11&lng=12&userID=1
                MyData.put("type", typeD.toLowerCase());
                MyData.put("disease", diseaseD.toLowerCase());
                MyData.put("address", addressD.toLowerCase());
                MyData.put("lat", String.valueOf(lat));
                MyData.put("lng", String.valueOf(lng));
                MyData.put("userID", String.valueOf(idUser));
                return MyData;
            }
        };

        MyRequestQueue.add(MyStringRequest);
    }


    }




