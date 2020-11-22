package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mynotebook.ui.TextBook.TextBookFragment;

public class XiugaiActivity extends AppCompatActivity {

    String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai);

        final TextView wordTextview = (TextView) findViewById(R.id.word_textview);
        final EditText meanText = (EditText) findViewById(R.id.xiugaimeaning_text);
        final EditText sentenceText = (EditText) findViewById(R.id.xiugaisentence_text);
        Button wanchengXiugai = (Button) findViewById(R.id.xiugaiOK_button);

        word = getIntent().getStringExtra("word");
        wordTextview.setText(word);


       wanchengXiugai.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               TextBookFragment fragment = new TextBookFragment();
               Bundle bundle2 = new Bundle();
               bundle2.putString("wordxiugai",wordTextview.getText().toString());
               bundle2.putString("meaningxiugai",meanText.getText().toString());
               bundle2.putString("sentencexiugai",sentenceText.getText().toString());
               fragment.setArguments(bundle2);

               MyDatabaseHelper dbHelper2 = new MyDatabaseHelper(App.getContext(),"BookStore.db",null,2);
               fragment.XiugaiWord(dbHelper2);
           }
       });

    }
}
