package com.example.mynotebook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.mynotebook.ui.Dictionary.DictionaryFragment;
import com.example.mynotebook.ui.TextBook.TextBookFragment;
import com.facebook.stetho.Stetho;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements DictionaryFragment.MyListener1,TextBookFragment.MyListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stetho.initializeWithDefaults(this);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_Dictionary, R.id.navigation_News, R.id.navigation_TextBook)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


    }

    public void sendValue(String word ,String mean,String sentence) {
        String word1 = word;
        String mean1 = mean;
        String sentence1 = sentence;

        Intent intent = new Intent(MainActivity.this,WordActivity.class);
        intent.putExtra("word",word1);
        intent.putExtra("meaning",mean1);
        intent.putExtra("sentence",sentence1);
        startActivity(intent);
    }

    public void sendValue1(String word ,String mean,String sentence) {
        String word1 = word;
        String mean1 = mean;
        String sentence1 = sentence;

        Intent intent = new Intent(MainActivity.this,WordActivity.class);
        intent.putExtra("word",word1);
        intent.putExtra("meaning",mean1);
        intent.putExtra("sentence",sentence1);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.help_item:
                Toast.makeText(this, "这是帮助", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

}
