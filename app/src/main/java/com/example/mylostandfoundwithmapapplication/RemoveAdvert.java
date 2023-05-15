package com.example.mylostandfoundwithmapapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RemoveAdvert extends AppCompatActivity {

    //Initializing variables
    TextView detailsItem, detailsName, detailsDateLocation;
    Button removeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_advert);

        //Find view by ID
        detailsItem = findViewById(R.id.detailsItem);
        detailsName = findViewById(R.id.detailsName);
        detailsDateLocation = findViewById(R.id.detailsDateLocation);
        removeButton = findViewById(R.id.removeButton);

        Intent intent = getIntent();
        Integer id = intent.getIntExtra("id", 0);
        String status = intent.getStringExtra("status");
        String name = intent.getStringExtra("name");
        String phone = intent.getStringExtra("phone");
        String description = intent.getStringExtra("description");
        String date = intent.getStringExtra("date");
        String location = intent.getStringExtra("location");

        //Set text to advert details
        detailsItem.setText(status + ": " + description);
        detailsName.setText("Posted by " + name + ", Phone no. : " + phone);
        detailsDateLocation.setText(date + ", " + location);

        //If Remove Entry button is clicked
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.databaseHelper.deleteEntry(id);
                finish();
            }
        });
    }
}
