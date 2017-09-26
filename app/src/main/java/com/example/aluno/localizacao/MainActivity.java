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

    private Address endereco; //trazer o nome da rua, do estado, telefone...

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLatitude =(TextView) findViewById(R.id.txtLatitude);
        txtLongitude=(TextView) findViewById(R.id.txtLongitude);
        txtLongitude=(TextView) findViewById(R.id.txtCidade);
        txtLongitude=(TextView) findViewById(R.id.txtEstado);
        txtLongitude=(TextView) findViewById(R.id.txtPais);

        double latitude =0.0;
        double longitude =0.0;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED){
        // solicitar permissão ao usuário
        }else{
            locationManager=(LocationManager)
                    getSystemService(Context.LOCATION_SERVICE);
            location= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        if (location !=null){
            longitude = location.getLongitude();
            latitude = location.getLatitude();
        }

        txtLongitude.setText("Longitude: " + longitude);
        txtLatitude.setText("Latitude: " + latitude);
        try{
            endereco = buscarEndereco(latitude, longitude);

            txtCidade.setText("Cidade " + endereco.getLocality());
            txtEstado.setText("Estado " + endereco.getSubAdminArea());
            txtPais.setText("Pais..:"+ endereco.getCountryName());

        } catch(IOException e){
            Log.i("GPS", e.getMessage());
        }
    }
        public Address buscarEndereco(double latitude, double longitude) throws IOException{

                Geocoder geocoder;
                Address address = null;
                List<Address> addresses;

                geocoder = new Geocoder(getApplicationContext());

                addresses=geocoder.getFromLocation(latitude, longitude,1);   //quantidade de endereços, no caso 1

                if (addresses.size()>0){     //se existe algum endereço
                    address = addresses.get(0);  //atribuo ao address

                }
                return address;

        }

}


