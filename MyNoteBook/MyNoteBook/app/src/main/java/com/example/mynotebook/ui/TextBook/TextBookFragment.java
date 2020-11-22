package com.example.mynotebook.ui.TextBook;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.mynotebook.AddActivity;
import com.example.mynotebook.App;
import com.example.mynotebook.MyDatabaseHelper;
import com.example.mynotebook.R;
import com.example.mynotebook.Word;
import com.example.mynotebook.WordActivity;
import com.example.mynotebook.WordAdapter;

import java.util.ArrayList;
import java.util.List;
import android.support.v4.app.*;

public class TextBookFragment extends Fragment {

    private TextBookViewModel notificationsViewModel;
    private MyDatabaseHelper dbHelper;
    private List<Word> wordlist = new ArrayList<>();
    private List<Word> wordlistchaxun11 = new ArrayList<>();

    public interface MyListener{
        public void sendValue(String word,String mean,String sentence);
    }

    private MyListener myListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(TextBookViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_textbook, container, false);

        setHasOptionsMenu(true);
        dbHelper = new MyDatabaseHelper(App.getContext(),"BookStore.db",null,2);
        final EditText chaxun = (EditText) root.findViewById(R.id.chazhao_edittext);
        Button chazhaoButton = (Button) root.findViewById(R.id.chazhao_button);
        Button createDatabase = (Button) root.findViewById(R.id.buttonAdd);

        initWord();
        final WordAdapter adapter = new WordAdapter(this.getContext(),R.layout.word_item,wordlist);
        final WordAdapter adapter1 = new WordAdapter(this.getContext(),R.layout.word_item,wordlistchaxun11);
        ListView listView = (ListView) root.findViewById(R.id.wordlist);
        listView.setAdapter(adapter);

        chazhaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordlistchaxun11.clear();
                String chaxunText = chaxun.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,"word like ? or meaning like ?" ,
                        new String[]{"%"+chaxunText+"%","%"+chaxunText+"%"},null,null,null);
                if(cursor !=null ) {
                    while (cursor.moveToNext()) {
                        Word word = new Word();
                        word.setWord(cursor.getString(cursor.getColumnIndex("word")));
                        word.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
                        word.setSentence(cursor.getString(cursor.getColumnIndex("sentence")));
                        wordlistchaxun11.add(word);
                        ListView listView1 = (ListView) root.findViewById(R.id.wordlistchaxun1);
                        listView1.setAdapter(adapter1);

                        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                Word word = wordlistchaxun11.get(position);

                                String word1 = word.getWord();
                                String meaning1 = word.getMeaning();
                                String sentence1 = word.getSentence();
                                myListener.sendValue(word1,meaning1,sentence1);

                                Toast.makeText(App.getContext(),word.getWord(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                else if(cursor == null){
                    Toast.makeText(App.getContext(),"没有结果",Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });

        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), AddActivity.class);
                getActivity().startActivity(intent);

            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = wordlist.get(position);

                String word1 = word.getWord();
                String meaning1 = word.getMeaning();
                String sentence1 = word.getSentence();
                myListener.sendValue(word1,meaning1,sentence1);

                Toast.makeText(App.getContext(),word.getWord(),Toast.LENGTH_SHORT).show();
            }
        });

        return root;

    }

    public void AddWord(MyDatabaseHelper dbHelper){
              Bundle bundle = TextBookFragment.this.getArguments();
              String word = bundle.getString("wordtext");
              String meaning = bundle.getString("meaningtext");
              String sentence = bundle.getString("sentencetext");
              String yORn = bundle.getString("yORntext");

              int n=isReptitive(word);

              if(n==0){
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("word",word);
                values.put("meaning",meaning);
                values.put("sentence",sentence);
                values.put("yORn",yORn);
                db.insert("Book",null,values);
                values.clear();
                Toast.makeText(App.getContext(),word+"已成功添入词典",Toast.LENGTH_SHORT).show();
                Word word1 = new Word(word,meaning,sentence,yORn);
                wordlist.add(word1);
              }
              else{
                  Toast.makeText(App.getContext(),word+"表中已有该单词",Toast.LENGTH_SHORT).show();
              }

    }



    private void initWord(){  //将数据库中的单词加到单词列表中
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book",null,null,null,null,null,null);
        if(cursor !=null )
            while (cursor.moveToNext()){
                    Word word = new Word();
                    word.setWord(cursor.getString(cursor.getColumnIndex("word")));
                    word.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
                    word.setSentence(cursor.getString(cursor.getColumnIndex("sentence")));
                    word.setyORn(cursor.getString(cursor.getColumnIndex("yORn")));
                    wordlist.add(word);
            }
        cursor.close();

    }

    public void onViewCreated(View view,Bundle savedInstanceState){}

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main,menu);
    }

    public int isReptitive(String newWord){ //判断数据库中是否有重复单词 重复:1 不重复:0

        MyDatabaseHelper dbHelper = new MyDatabaseHelper(App.getContext(),"BookStore.db",null,2);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book",null,null,null,null,null,null);
        int x=0;
        if(cursor !=null ) {
            while (cursor.moveToNext()) {
                String word = cursor.getString(cursor.getColumnIndex("word"));
                if (word.equals(newWord) == true) {
                    x=1;
                }
            }
        }
        cursor.close();
        return x;
    }

    //activity和fragment联系时候调用，fragment必须依赖activty
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //获取实现接口的activity
        myListener = (MyListener) getActivity();//或者myListener=(MainActivity) context;

    }


    public void XiugaiWord(MyDatabaseHelper dbHelper){
        Bundle bundle = TextBookFragment.this.getArguments();
        String xiugaiword = bundle.getString("wordxiugai");
        String xiugaiMean = bundle.getString("meaningxiugai");
        String xiugaiSentence = bundle.getString("sentencexiugai");


        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("meaning",xiugaiMean);
        values.put("sentence",xiugaiSentence);
        db.update("Book",values,"word = ?",new String[]{xiugaiword});
        values.clear();
        Toast.makeText(App.getContext(),"修改成功",Toast.LENGTH_SHORT).show();

    }




}