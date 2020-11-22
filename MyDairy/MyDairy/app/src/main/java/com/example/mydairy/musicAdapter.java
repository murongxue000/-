package com.example.mydairy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class musicAdapter extends ArrayAdapter<Music> {

    private int resourceId;

    public musicAdapter(Context context, int textViewResourceId, List<Music> objects){
        super(context,textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Music music = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        TextView wordtext = (TextView) view.findViewById(R.id.musictitle_textview);
        wordtext.setText(music.getMusicTitle());
        return view;
    }
}
