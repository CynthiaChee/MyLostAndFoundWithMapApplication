package com.example.mylostandfoundwithmapapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class LostAndFoundList extends AppCompatActivity {
    LinearLayout itemListLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found_list);

        itemListLayout = findViewById(R.id.itemListLayout);
        showItemList();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemListLayout.removeAllViews();
        showItemList();
    }

    private void showItemList(){
        ArrayList<Item> lostAndFoundItemList = MainActivity.databaseHelper.getAllItems();
        for (int i = 0; i < lostAndFoundItemList.size(); i++){
            final Item lostAndFound = lostAndFoundItemList.get(i);
            TextView newText = new TextView(this);
            newText.setBackgroundResource(R.drawable.pink_input_border);
            newText.setPadding(16, 8, 0, 8);
            newText.setHeight(100);
            newText.setTextSize(20);
            newText.setText((lostAndFound.getStatus() + ": " + lostAndFound.getDescription()));
            newText.setTag(lostAndFound.getId());

            newText.setOnClickListener(view -> {
                Intent passData = new Intent(LostAndFoundList.this, RemoveAdvert.class);
                passData.putExtra("id", lostAndFound.getId());
                passData.putExtra("status", lostAndFound.getStatus());
                passData.putExtra("name", lostAndFound.getName());
                passData.putExtra("phone", lostAndFound.getPhone());
                passData.putExtra("description", lostAndFound.getDescription());
                passData.putExtra("date", lostAndFound.getDate());
                passData.putExtra("location", lostAndFound.getLocation());
                startActivity(passData);
            });
            itemListLayout.addView(newText);
        }
    }
}
