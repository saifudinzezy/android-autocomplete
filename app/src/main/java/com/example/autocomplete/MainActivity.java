package com.example.autocomplete;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AutoCompleteTextView;

import com.example.autocomplete.adapter.AutoCompleteCountryAdapter;
import com.example.autocomplete.model.CountryItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<CountryItem> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fillCountryList();

        AutoCompleteTextView editText = findViewById(R.id.actv);
        AutoCompleteCountryAdapter adapter = new AutoCompleteCountryAdapter(this, countryList);
        editText.setAdapter(adapter);
    }

    private void fillCountryList() {
        countryList = new ArrayList<>();
        countryList.add(new CountryItem("China", R.drawable.china));
        countryList.add(new CountryItem("Francis", R.drawable.france));
        countryList.add(new CountryItem("German", R.drawable.germany));
    }
}