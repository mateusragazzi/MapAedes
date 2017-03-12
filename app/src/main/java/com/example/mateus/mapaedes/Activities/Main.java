package com.example.mateus.mapaedes.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.mateus.mapaedes.Fragments.AdicionarCaso;
import com.example.mateus.mapaedes.Fragments.AdicionarFoco;
import com.example.mateus.mapaedes.Fragments.Buscar;
import com.example.mateus.mapaedes.Fragments.Configuracoes;
import com.example.mateus.mapaedes.Fragments.Informacoes;
import com.example.mateus.mapaedes.Fragments.Logout;
import com.example.mateus.mapaedes.Fragments.MeusCasosAdicionados;
import com.example.mateus.mapaedes.R;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import butterknife.BindView;
import butterknife.ButterKnife;

public class
Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, AdicionarCaso.OnFragmentInteractionListener,
        AdicionarFoco.OnFragmentInteractionListener,
        Buscar.OnFragmentInteractionListener,
        Configuracoes.OnFragmentInteractionListener,
        Informacoes.OnFragmentInteractionListener,
        Logout.OnFragmentInteractionListener,
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
    private static final double CG_LAT = -20.4435,
            CG_LGT = -54.6478;
    @BindView(R.id.content_main)
    RelativeLayout mContentMain;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.main_toolbar)
    Toolbar mToolbar;

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

        //se for medico, desativa focos
        if (mUsers[0].equals(mUser)) {
            mMenu.findItem(R.id.nav_title_focos).setVisible(false);
            configuraTitle(mMenu.findItem(R.id.nav_title_casos));
            switch (item.getItemId()) {
                case R.id.nav_map:

                    mFragMap = new SupportMapFragment();
                    mFragMap.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            mMap = googleMap;

                            LatLng latlng ;
                            latlng = new LatLng(CG_LAT, CG_LGT);
                            final CameraPosition cp = new CameraPosition.Builder().target(latlng)
                                    .zoom(13).bearing(0).tilt(00).build();
                            CameraUpdate cam = CameraUpdateFactory.newCameraPosition(cp);
                            mMap.moveCamera(cam);

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
                case R.id.nav_buscarcasos:
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
                    frag = new Logout();
                    trocaFrag(fm, frag);
                    break;
            }
        } else {
            mMenu.findItem(R.id.nav_title_casos).setVisible(false);
            configuraTitle(mMenu.findItem(R.id.nav_title_focos));
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
