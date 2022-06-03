package com.aurosaswatraj.countmycrunch.Fooding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.aurosaswatraj.countmycrunch.Fooding.Adapters.RandomMealAdapter;
import com.aurosaswatraj.countmycrunch.Fooding.Listeners.CustomOnClickListener;
import com.aurosaswatraj.countmycrunch.Fooding.Listeners.RandomAPIResponseListener;
import com.aurosaswatraj.countmycrunch.Fooding.Models.RandomRecipe;
import com.aurosaswatraj.countmycrunch.R;

import java.util.ArrayList;
import java.util.List;

public class Foodz extends AppCompatActivity {


    RequestManager manager;
    RandomMealAdapter adapter;
    //    ProgressDialog dialog;
    RecyclerView recyclerView;
    List<String> tags = new ArrayList<>();
    Spinner spinner;
    SearchView searchView_home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodz);

        recyclerView = findViewById(R.id.recycler_random);
        spinner = findViewById(R.id.spinner_tags);
        searchView_home = findViewById(R.id.searchView_home);


        manager = new RequestManager(this);
        ArrayAdapter arrayAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.tags,
                R.layout.spinner_text
        );
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(spinnerSelectedListener);

        searchView_home.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(Foodz.this, "Will be added soon!", Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });





    }


    private final RandomAPIResponseListener listener = new RandomAPIResponseListener() {
        @Override
        public void didFetch(List<RandomRecipe> responses, String message) {
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL));
            adapter = new RandomMealAdapter(Foodz.this, responses, customOnClickListener);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);

        }

        @Override
        public void didError(String message) {
            recyclerView.setVisibility(View.VISIBLE);

            Toast.makeText(Foodz.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final AdapterView.OnItemSelectedListener spinnerSelectedListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            tags.clear();
            tags.add(adapterView.getSelectedItem().toString().toLowerCase());
            manager.GetRandomRecipes(listener, tags);
            recyclerView.setVisibility(View.GONE);

        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    };

    private final CustomOnClickListener customOnClickListener = new CustomOnClickListener() {
        @Override
        public void onClick(String text) {
            startActivity(new Intent(Foodz.this, RecipeDetailActivity.class)
                    .putExtra("id", text));
        }
    };
}