package com.example.mynotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynotebook.ui.TextBook.TextBookFragment;

public class WordActivity extends AppCompatActivity implements View.OnClickListener {

    TextView wordText;
    TextView meaningText;
    TextView sentenceText;
    String word;
    String meaning;
    String sentence;
    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        Button buttonXiugai = (Button) findViewById(R.id.xiugai_button);
        Button buttonShanchu = (Button) findViewById(R.id.shanchu_button);
        Button buttonTianJiaShengCiBen = (Button) findViewById(R.id.tianJiaShengciben_button);
        Button buttonYiChushengciben = (Button) findViewById(R.id.yichuShengciben_button);
        dbHelper = new MyDatabaseHelper(this,"BookStore.db",null,2);

        wordText = (TextView) findViewById(R.id.word_wordActivity);
        meaningText = (TextView) findViewById(R.id.meaning_wordActivity);
        sentenceText = (TextView) findViewById(R.id.sentence_wordActivity);

        word = getIntent().getStringExtra("word");
        meaning = getIntent().getStringExtra("meaning");
        sentence = getIntent().getStringExtra("sentence");

        wordText.setText(word);
        meaningText.setText(meaning);
        sentenceText.setText(sentence);

        buttonShanchu.setOnClickListener(this);
        buttonXiugai.setOnClickListener(this);
        buttonTianJiaShengCiBen.setOnClickListener(this);
        buttonYiChushengciben.setOnClickListener(this);

    }

    public  void onClick(View v) {
        switch (v.getId()) {
            case R.id.xiugai_button:

                Intent intent = new Intent(WordActivity.this,XiugaiActivity.class);
                intent.putExtra("word",word);
                startActivity(intent);
                break;

            case R.id.shanchu_button:
                SQLiteDatabase db1 = dbHelper.getWritableDatabase();
                db1.delete("Book","word = ?",new String[]{word});
                Toast.makeText(WordActivity.this,"删除成功",Toast.LENGTH_SHORT).show();
                break;

            case R.id.tianJiaShengciben_button:
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("yORn","N");
                db.update("Book",values,"word = ?",new String[]{word});
                values.clear();
                Toast.makeText(App.getContext(),"已添加到生词本",Toast.LENGTH_SHORT).show();
                break;

            case R.id.yichuShengciben_button:
                SQLiteDatabase db11 = dbHelper.getWritableDatabase();
                ContentValues values1 = new ContentValues();
                values1.put("yORn","Y");
                db11.update("Book",values1,"word = ?",new String[]{word});
                values1.clear();
                Toast.makeText(App.getContext(),"已从生词本中移除",Toast.LENGTH_SHORT).show();
                default:
        }
    }


}
