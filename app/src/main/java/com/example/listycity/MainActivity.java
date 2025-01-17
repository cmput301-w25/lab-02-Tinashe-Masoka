package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText enterCityName;
    Button addCity;
    Button deleteCity;
    int selectedButton = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this); //might have to remove this line
        setContentView(R.layout.activity_main);
        cityList = findViewById(R.id.city_list);

        enterCityName = findViewById(R.id.city_name_input);
        addCity = findViewById(R.id.addCityButton);
        deleteCity = findViewById(R.id.deleteCityButton);

        String[] cities = {};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                selectedButton = position;
            }
        });

        addCity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String City = enterCityName.getText().toString().trim();
                if (!City.isEmpty()){
                    dataList.add(City);
                    cityAdapter.notifyDataSetChanged();
                    enterCityName.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Must enter a city name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        deleteCity.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(selectedButton != -1){
                    dataList.remove(selectedButton);
                    cityAdapter.notifyDataSetChanged();
                    selectedButton = -1;
                } else {
                    Toast.makeText(MainActivity.this, "Must select a city to delete" , Toast.LENGTH_SHORT).show();
                }
            }
        });

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/

    }
}