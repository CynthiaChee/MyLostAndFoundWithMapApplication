package com.example.mylostandfoundwithmapapplication;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;


@SuppressWarnings("ALL")
public class CreateAdvert extends AppCompatActivity {

    //Initializing variables
    EditText enterName, enterPhone, enterItemDesc, enterDate, enterLocation;
    RadioButton lostRadioButton, foundRadioButton;
    Button getLocationButton, saveButton, getLocation;
    LocationManager locationManager;
    LocationListener locationListener;
    String name, status, phone, description, date, location, currentLocation;
    Double latitude, longitude;

    @Override
    public void onRequestPermissionsResult(int requestCode,@NonNull String[]permissions,@NonNull int[]grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_advert);

        //Find view by ID
        lostRadioButton = findViewById(R.id.lostRadioButton);
        foundRadioButton = findViewById(R.id.foundRadioButton);
        enterName = findViewById(R.id.nameEditText);
        enterPhone = findViewById(R.id.phoneEditText);
        enterItemDesc = findViewById(R.id.descriptionEditText);
        enterDate = findViewById(R.id.dateEditText);
        enterLocation = findViewById(R.id.locationEditText);
        getLocation = findViewById(R.id.getLocationButton);
        saveButton = findViewById(R.id.saveButton);

        //If Get Location button is clicked
        enterLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setLocationIntent = new Intent(CreateAdvert.this, LocationActivity.class);
                startActivityForResult(setLocationIntent, 3);
            }
        });

        //If location is entered, set it to the textView
        getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterLocation.setText(currentLocation);
            }
        });
        locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        locationListener = location -> currentLocation = location.toString();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,0, locationListener);

        //If save button is clicked
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lostRadioButton.isChecked()){
                    status = "Lost";
                }
                else if (foundRadioButton.isChecked()){
                    status = "Found";
                }
                name = enterName.getText().toString();
                phone = enterPhone.getText().toString();
                description = enterItemDesc.getText().toString();
                date = enterDate.getText().toString();
                location = enterLocation.getText().toString();

                Item newItem = new Item(null, status, name, phone, description, date, location, latitude, longitude);
                boolean addNewItem = MainActivity.databaseHelper.insertNew(newItem);

                if (addNewItem){
                    Toast.makeText(CreateAdvert.this, "New advert added!", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(CreateAdvert.this, "Error. Please try again.", Toast.LENGTH_SHORT).show();
                }

                Intent putIntoListIntent = new Intent( CreateAdvert.this, LostAndFoundList.class);
                startActivity(putIntoListIntent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 3)
        {
            if (resultCode == RESULT_OK && data != null)
            {
                latitude = data.getDoubleExtra("latitude", 0);
                longitude = data.getDoubleExtra("longitude", 0);
                String name = data.getStringExtra("name");
                enterLocation.setText(name);
            }
        }
    }
}
