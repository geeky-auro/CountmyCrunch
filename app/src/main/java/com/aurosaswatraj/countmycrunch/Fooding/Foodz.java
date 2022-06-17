package com.aurosaswatraj.countmycrunch.Fooding;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.aurosaswatraj.countmycrunch.Dialogs.ErrorDialog;
import com.aurosaswatraj.countmycrunch.Dialogs.UserDarkModeDialog;
import com.aurosaswatraj.countmycrunch.Fooding.Adapters.RandomMealAdapter;
import com.aurosaswatraj.countmycrunch.Fooding.Listeners.CustomOnClickListener;
import com.aurosaswatraj.countmycrunch.Fooding.Listeners.RandomAPIResponseListener;
import com.aurosaswatraj.countmycrunch.Fooding.Models.RandomRecipe;
import com.aurosaswatraj.countmycrunch.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.ArrayList;
import java.util.List;

public class Foodz extends AppCompatActivity {


    ErrorDialog errorDialog;

    RequestManager manager;
    RandomMealAdapter adapter;
    //    ProgressDialog dialog;
    RecyclerView recyclerView;
    List<String> tags = new ArrayList<>();
    Spinner spinner;
    SearchView searchView_home;
    ProgressBar progressBar;

    UserDarkModeDialog darkModeDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodz);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // Fixed portrait orientation
        errorDialog=new ErrorDialog(this);
        darkModeDialog=new UserDarkModeDialog();
        darkModeDialog.darkModeDialog(this,this);



        Window window = this.getWindow();
        window.setStatusBarColor(Color.parseColor("#C51162"));
        progressBar = findViewById(R.id.loader);
        Sprite doubleBounce = new Wave();
        progressBar.setIndeterminateDrawable(doubleBounce);

        recyclerView = findViewById(R.id.recycler_random);
        spinner = findViewById(R.id.spinner_tags);
        searchView_home = findViewById(R.id.searchView_home);


        manager = new RequestManager(this);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(
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
              tags.clear();
                tags.add(query);
                manager.GetRandomRecipes(listener,tags);
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
            progressBar.setVisibility(View.GONE);

        }

        @Override
        public void didError(String message) {
            recyclerView.setVisibility(View.VISIBLE);
            errorDialog.serverBusy();
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
            progressBar.setVisibility(View.VISIBLE);

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