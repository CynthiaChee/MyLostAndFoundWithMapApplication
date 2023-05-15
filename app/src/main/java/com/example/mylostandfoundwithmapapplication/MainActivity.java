package com.example.mylostandfoundwithmapapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //Initializing variables
    public static DatabaseHelper databaseHelper = null;
    Button createAdvertButton, lostAndFoundListButton, showOnMapButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(getApplicationContext());

        //Find view by ID
        createAdvertButton = findViewById(R.id.createAdvertButton);
        lostAndFoundListButton = findViewById(R.id.lostAndFoundListButton);
        showOnMapButton = findViewById(R.id.showOnMapButton);

        createAdvertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent createAdvertIntent = new Intent(getApplicationContext(), CreateAdvert.class);
                startActivity(createAdvertIntent);
            }
        });

        lostAndFoundListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showItemListIntent = new Intent(getApplicationContext(), LostAndFoundList.class);
                startActivity(showItemListIntent);
            }
        });

        showOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showMapIntent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(showMapIntent);
            }
        });
    }
}
