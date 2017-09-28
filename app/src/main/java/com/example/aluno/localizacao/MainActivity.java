package com.example.aluno.localizacao;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView txtLatitude;
    private TextView txtLongitude;
    private TextView txtPais;
    private TextView txtEstado;
    private TextView txtCidade;

    private Location location;   // devolve a coordenada gps
    private LocationManager locationManager; //vai buscar qual o provedor de dispostitivo disponivel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitude =(TextView) findViewById(R.id.txtLatitude);
        txtLongitude=(TextView) findViewById(R.id.txtLongitude);


        double latitude =0.0;
        double longitude =0.0;


        if( ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);


        }else{
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

             if(location == null){
                 location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
             }


            if (location !=null){
                longitude = location.getLongitude();
                latitude = location.getLatitude();

                txtLongitude.setText("Longitude: " + longitude);
                txtLatitude.setText("Latitude: " + latitude);
            }


        }


    }


}


