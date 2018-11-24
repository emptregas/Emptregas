package com.emptregas.emptregas.fragment;


import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.emptregas.emptregas.R;
import com.emptregas.emptregas.adapter.FoodCategoryAdapter;
import com.emptregas.emptregas.adapter.RestaurantAdapter;
import com.emptregas.emptregas.checkout.CheckoutActivity;
import com.emptregas.emptregas.entities.RestGoogleApi;
import com.emptregas.emptregas.interfaces.DirectionFinderListener;
import com.emptregas.emptregas.interfaces.RetrofitMaps;
import com.emptregas.emptregas.model.DistanceMatrix.Element;
import com.emptregas.emptregas.model.DistanceMatrix.Moduls.DirectionFinder;
import com.emptregas.emptregas.model.DistanceMatrix.Moduls.Route;
import com.emptregas.emptregas.model.OrderForm;
import com.emptregas.emptregas.responses.DistanceMatrixResponse;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements OnMapReadyCallback, DirectionFinderListener {
    private static final String TAG = "NewOrderMap";

    private RecyclerView recyclerFood, recyclerRestaurants;
    private TextView cartNotificationCount,txtPrecio,txtDistancia;
    private Button  siguiente;
    TextView edtOrigen,edtDestino;

    private String cursorEt="";
    private String originsCoo, destinationsCoo;
    String adressRec,adressEnt;
    private LatLng latLngRec, latLngEnt = null;
    private Location locationa, locationb = null;
    private Button btnNext;
    private CheckBox idayvuelta;
    OrderForm order = new OrderForm();



    //partes del Map
    private GoogleMap mMap;
    private MapView mapView;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;

    private int PLACE_AUTOCOMPLETE_REQUEST_CODE = 1;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_home, menu);
        View cartActionView = menu.findItem(R.id.action_cart).getActionView();
        cartNotificationCount = ((TextView) cartActionView.findViewById(R.id.notification_count));
        cartActionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // startActivity(new Intent(getContext(), NotificationsActivity.class));
            }
        });
        setCartCount();
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void setCartCount() {
        int NOTIFICATION_COUNT = 1;
        if (cartNotificationCount != null) {
            if (NOTIFICATION_COUNT <= 0) {
                cartNotificationCount.setVisibility(View.GONE);
            } else {
                cartNotificationCount.setVisibility(View.VISIBLE);
                cartNotificationCount.setText(String.valueOf(NOTIFICATION_COUNT));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home2, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
               .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        edtOrigen = (TextView) view.findViewById(R.id.edtOrigen);
        edtDestino = (TextView) view.findViewById(R.id.edtDestino);

         txtPrecio = ((TextView) view.findViewById(R.id.txtPrecio));
         txtDistancia = ((TextView) view.findViewById(R.id.txtDistancia));
         siguiente = ((Button) view.findViewById(R.id.txtCrearOrden));
         siguiente.setVisibility(View.GONE);
        idayvuelta = ((CheckBox) view.findViewById(R.id.idayVuelta));

        edtOrigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                            .setTypeFilter(Place.TYPE_LOCALITY)
                            .setCountry("CO")
                            .build();
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .setFilter(autocompleteFilter)
                                    .setBoundsBias(new LatLngBounds(new LatLng(6.153801, -75.612657),new LatLng(6.247165, -75.568239)))
                                    .build(getActivity());
                    cursorEt = "Origen";
                   startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });

        edtDestino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    AutocompleteFilter autocompleteFilter = new AutocompleteFilter.Builder()
                            .setTypeFilter(Place.TYPE_LOCALITY)
                            .setCountry("CO")
                            .build();
                    Intent intent =
                            new PlaceAutocomplete.IntentBuilder(PlaceAutocomplete.MODE_OVERLAY)
                                    .setFilter(autocompleteFilter)
                                    .setBoundsBias(new LatLngBounds(new LatLng(6.153801, -75.612657),new LatLng(6.247165, -75.568239)))
                                    .build(getActivity());
                     cursorEt = "Destino";
                    startActivityForResult(intent, PLACE_AUTOCOMPLETE_REQUEST_CODE);
                } catch (GooglePlayServicesRepairableException e) {
                    // TODO: Handle the error.
                } catch (GooglePlayServicesNotAvailableException e) {
                    // TODO: Handle the error.
                }
            }
        });
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crea el nuevo fragmento y la transacción.

            //    startActivity(new Intent(getActivity(), Main2Activity.class));
                String origen = edtOrigen.getText().toString();
                String destino = edtDestino.getText().toString();

                if(TextUtils.isEmpty(origen)||TextUtils.isEmpty(destino)|| origen.equals("Direccion o Lugar de Recoleccion")||destino.equals("Direccion o Lugar de Entrega")){

                    Toast.makeText(getContext(),"Alguna de las direcciones es incorrecta, por favor verifique e intente de nuevo!",Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(getActivity(),CheckoutActivity.class);

                Bundle orderBundle = new Bundle();
                Toast.makeText(getContext(),adressRec + adressEnt,Toast.LENGTH_SHORT).show();

                String origen2= edtOrigen.getText().toString();

                intent.putExtra("bundleOrigen", origen2);
                intent.putExtra("bundleDestino", edtDestino.getText().toString());
                intent.putExtra("bundleDistancia",txtDistancia.getText().toString());
                intent.putExtra("bundlePrecio",Integer.parseInt(txtPrecio.getText().toString().substring(1)));
                intent.putExtra("adressRec",adressRec);
                intent.putExtra("adressEnt",adressEnt);
                startActivity(intent);
            }
        });

        idayvuelta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idayvuelta.isChecked()){
                    if (locationa != null && locationb != null) {
                        getDistanceInfo(originsCoo,destinationsCoo);
                    }
                }else{
                    if (locationa != null && locationb != null) {
                        getDistanceInfo(originsCoo,destinationsCoo);
                    }
                }
            }
        });
        return view;
    }


    public void onMapReady(GoogleMap googleMap) {
        Log.e("OnMapReady","Ejecucion del metodo");
        mMap = googleMap;
        LatLng hcmus = new LatLng(6.158518, -75.606783);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(hcmus, 16));
       originMarkers.add(mMap.addMarker(new MarkerOptions()
                .title("Hola Mundo")
                .position(hcmus)));

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
    }

    //Funcion que recibe la info seleccionada en el Edit Text

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_AUTOCOMPLETE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                if (cursorEt == "Origen") {
                    latLngRec = place.getLatLng();
                    originsCoo= String.valueOf(latLngRec.latitude)+","+String.valueOf(latLngRec.longitude);
                    Log.d(TAG, "Click en :" + latLngRec.latitude+","+latLngRec.longitude);
                 //   stringdouble = Double.toString(latLngRec.latitude);
                    locationa = new Location("A");
                    locationa.setLatitude(latLngRec.latitude);
                    locationa.setLongitude(latLngRec.longitude);
                    edtOrigen.setText(place.getName());

                } else {
                    latLngEnt = place.getLatLng();
                    destinationsCoo =String.valueOf(latLngEnt.latitude)+","+String.valueOf(latLngEnt.longitude);;
                    locationb = new Location("B");
                    locationb.setLatitude(latLngEnt.latitude);
                    locationb.setLongitude(latLngEnt.longitude);
                    edtDestino.setText(place.getName());

                }
                getDistanceInfo(originsCoo,destinationsCoo);
                if (locationa != null && locationb != null) {
                    getDistanceInfo(originsCoo,destinationsCoo);
                    sendRequest();

                }
            }
        }
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
      //  setupRecyclerFood();
      //  setupRecyclerRestaurants();
    }

    private void setupRecyclerRestaurants() {
        recyclerRestaurants.setNestedScrollingEnabled(false);
        recyclerRestaurants.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerRestaurants.setAdapter(new RestaurantAdapter(getContext()));
    }

    private void setupRecyclerFood() {
        recyclerFood.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerFood.setAdapter(new FoodCategoryAdapter(getContext()));
    }

    private void getDistanceInfo(String origin, String destination) {
        // http://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=Washington,DC&destinations=New+York+City,NY
        Map<String, String> mapQuery = new HashMap<>();
        mapQuery.put("units", "imperial");
        mapQuery.put("origins", origin);
        mapQuery.put("destinations", destination);
        mapQuery.put("key", "AIzaSyB65IUk9-GiPp2a2Zn5k_KCD6WsVVbbbBo");

        RetrofitMaps client = RestGoogleApi.getInstance().getRetrofit().create(RetrofitMaps.class);

        Call<DistanceMatrixResponse> call = client.getDistanceInfo(mapQuery);
        call.enqueue(new Callback<DistanceMatrixResponse>() {
            @Override
            public void onResponse(Call<DistanceMatrixResponse> call, Response<DistanceMatrixResponse> response) {
                if (response.body() != null &&
                        response.body().getRows() != null &&
                        response.body().getRows().size() > 0 &&
                        response.body().getRows().get(0) != null &&
                        response.body().getRows().get(0).getElements() != null &&
                        response.body().getRows().get(0).getElements().size() > 0 &&
                        response.body().getRows().get(0).getElements().get(0) != null &&
                        response.body().getRows().get(0).getElements().get(0).getDistance() != null &&
                        response.body().getRows().get(0).getElements().get(0).getDuration() != null) {

                    Element element = response.body().getRows().get(0).getElements().get(0);

                    double dist= getDist(Double.valueOf(element.getDistance().getValue()));
                    txtDistancia.setText(Double.toString(dist) + " Kms");


                   int precioResult = calculatePrice(element.getDistance().getValue());
                    txtPrecio.setText("$" + Integer.toString(precioResult));
                    adressRec = (String) response.body().getOriginAddresses().get(0);
                     adressEnt =(String) response.body().getDestinationAddresses().get(0);
                    order.setDistancia(txtDistancia.getText().toString());
                    order.setPrecio(precioResult);
                }
            }

            @Override
            public void onFailure(Call<DistanceMatrixResponse> call, Throwable t) {
              //  txtPrecio.setText("23542642");

            }
        });
    }
    private double getDist(Double value) {
        double distance;
        if (idayvuelta.isChecked()==true) {
            distance = value * 2;
            distance = distance / 1000;
        }else{
            distance = value / 1000;
        }

        return distance;
    }

    private int calculatePrice(Integer value) {
        int distancia2 = value / 1000;
        int price,diferencia;

        if (idayvuelta.isChecked()==true){
            distancia2 = distancia2*2;
            if(distancia2<=7){
                price = 9000;
            }else{
                diferencia = distancia2-7;
                price = 9000 + (diferencia * 700);
            }
        }else {

            if (distancia2 <= 7) {
                price = 7000;
            } else {
                diferencia = distancia2 - 7;
                price = 7000 + (diferencia * 700);
            }
        }

        return price;

    }

    private void sendRequest() {
    //    String origin = etOrigin.getText().toString();
       if (edtOrigen.getText().toString() == "") {
            Toast.makeText(getActivity(), "Por favor ingrese Direccion o Lugar de Recoleccion", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtDestino.getText().toString() == "") {
            Toast.makeText(getActivity(), "Por favor ingrese Direccion o Lugar de Entrega", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            new DirectionFinder(this, originsCoo, destinationsCoo).execute();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onDirectionFinderStart() {
        progressDialog = ProgressDialog.show(getContext(), "Por favor espere.",
                "Calculando Servicio.!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {

        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_recoger_24dp))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_entrega_24dp))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.GREEN).
                    width(12);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 13));
            //agregado por mi
            siguiente.setVisibility(View.VISIBLE);
        }
    }

}
