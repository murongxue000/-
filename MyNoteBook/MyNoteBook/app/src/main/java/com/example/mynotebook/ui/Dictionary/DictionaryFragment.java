package com.example.mynotebook.ui.Dictionary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.mynotebook.WordAdapter;
import com.example.mynotebook.ui.TextBook.TextBookFragment;

import java.util.ArrayList;
import java.util.List;

public class DictionaryFragment extends Fragment {

    private DictionaryViewModel dashboardViewModel;
    private MyDatabaseHelper dbHelper;
    private List<Word> wordlistchaxun = new ArrayList<>();
    private List<Word> wordlist = new ArrayList<>();

    public interface MyListener1{
        public void sendValue1(String word,String mean,String sentence);
    }

    MyListener1 myListener1;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DictionaryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_dictionary, container, false);

        setHasOptionsMenu(true);
        dbHelper = new MyDatabaseHelper(App.getContext(),"BookStore.db",null,2);
        final EditText chaxun = (EditText) root.findViewById(R.id.chazhao_edittext);
        Button chazhaoButton = (Button) root.findViewById(R.id.chazhao_button);
        final WordAdapter adapter = new WordAdapter(this.getContext(),R.layout.word_item,wordlistchaxun);

        initWord();
        final WordAdapter adapter1 = new WordAdapter(this.getContext(),R.layout.word_item,wordlist);
        ListView listView = (ListView) root.findViewById(R.id.wordlist);
        listView.setAdapter(adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Word word = wordlist.get(position);

                String word1 = word.getWord();
                String meaning1 = word.getMeaning();
                String sentence1 = word.getSentence();
                myListener1.sendValue1(word1,meaning1,sentence1);

                Toast.makeText(App.getContext(),word.getWord(),Toast.LENGTH_SHORT).show();
            }
        });


        chazhaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordlistchaxun.clear();
                String chaxunText = chaxun.getText().toString();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                Cursor cursor = db.query("Book",null,"yORn=? and ( word like ? or meaning like ? and yORn = ? )" ,new String[]{"N","%"+chaxunText+"%","%"+chaxunText+"%"},null,null,null);
                    if(cursor !=null ) {
                    while (cursor.moveToNext()) {
                        Word word = new Word();
                        word.setWord(cursor.getString(cursor.getColumnIndex("word")));
                        word.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
                        word.setSentence(cursor.getString(cursor.getColumnIndex("sentence")));
                        wordlistchaxun.add(word);
                        ListView listView = (ListView) root.findViewById(R.id.wordlistchaxun2);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                                Word word = wordlistchaxun.get(position);

                                String word1 = word.getWord();
                                String meaning1 = word.getMeaning();
                                String sentence1 = word.getSentence();
                                myListener1.sendValue1(word1,meaning1,sentence1);

                                Toast.makeText(App.getContext(),word.getWord(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
                else {
                    Toast.makeText(App.getContext(),"没有结果",Toast.LENGTH_SHORT).show();
                }
                cursor.close();
            }
        });


        return root;
    }

    private void initWord(){  //将数据库中的单词加到单词列表中
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query("Book",null,null,null,null,null,null);
        if(cursor !=null )
            while (cursor.moveToNext()){
                if(cursor.getString(cursor.getColumnIndex("yORn")).equals("N") == true) {
                    Word word = new Word();
                    word.setWord(cursor.getString(cursor.getColumnIndex("word")));
                    word.setMeaning(cursor.getString(cursor.getColumnIndex("meaning")));
                    word.setSentence(cursor.getString(cursor.getColumnIndex("sentence")));
                    word.setyORn(cursor.getString(cursor.getColumnIndex("yORn")));
                    wordlist.add(word);
                }
            }
        cursor.close();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //获取实现接口的activity
        myListener1 = (MyListener1) getActivity();//或者myListener=(MainActivity) context;

    }

}