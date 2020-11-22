package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mynotebook.ui.TextBook.TextBookFragment;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText wordEdittext = (EditText) findViewById(R.id.word_text);
        final EditText meaningEdittext = (EditText) findViewById(R.id.meaning_text);
        final EditText sentenceEdittext = (EditText) findViewById(R.id.sentence_text);

        Button buttonAddOK = (Button) findViewById(R.id.AddOK_button);
        buttonAddOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String wordtext = wordEdittext.getText().toString();
                String meaningtext = meaningEdittext.getText().toString();
                String sentencetext = sentenceEdittext.getText().toString();

              TextBookFragment fragment = new TextBookFragment();
                Bundle bundle = new Bundle();
                bundle.putString("wordtext",wordtext);
                bundle.putString("meaningtext",meaningtext);
                bundle.putString("sentencetext",sentencetext);
                bundle.putString("yORntext","Y");
                fragment.setArguments(bundle);

                MyDatabaseHelper dbHelper1 = new MyDatabaseHelper(App.getContext(),"BookStore.db",null,2);
                fragment.AddWord(dbHelper1);



            }
        });
    }
}
