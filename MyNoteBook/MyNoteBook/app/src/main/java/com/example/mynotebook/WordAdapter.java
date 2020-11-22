package com.example.mynotebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {

    private int resourceId;

    public WordAdapter(Context context,int textViewResourceId,List<Word> objects){
        super(context,textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Word word = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView wordtext = (TextView) view.findViewById(R.id.word_textview);
        TextView meaningtext = (TextView) view.findViewById(R.id.meaning_textview);
        wordtext.setText(word.getWord());
        meaningtext.setText(word.getMeaning());
        return view;
    }
}
