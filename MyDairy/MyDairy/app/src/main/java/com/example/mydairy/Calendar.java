package com.example.mydairy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CalendarView.OnDateChangeListener;

import java.io.IOException;

public class Calendar extends AppCompatActivity {

    CalendarView cv;
    private MediaPlayer mediaPlayer11;
    private MediaPlayer mediaPlayer111;
    private MyDairyDatabaseHelper myDairyDatabaseHelper;
    private TextView datestring ;
    private TextView weatherstring ;
    private TextView positionstring;
    private TextView wenzistring;
    private ImageView picture ;
    private Button music ;
    private Button sound;
    private String soundUri;
//    private TextView datestring;
//    private TextView weatherstring;
//    private TextView positionstring;
//    private TextView wenzhistring;
//    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        myDairyDatabaseHelper = new MyDairyDatabaseHelper(Calendar.this,"DairyStore.db",null,1);

        Button music = (Button) findViewById(R.id.pauseMusicXS_button);
        final Button sound = (Button) findViewById(R.id.bofangluyinXS_button);

        setContentView(R.layout.activity_calendar);
        cv = (CalendarView) findViewById(R.id.calendar_view);


        //为CalendarView组件的日期改变事件添加事件监听器//月要加1才是当前月
        cv.setOnDateChangeListener(new OnDateChangeListener(){

            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {

                SQLiteDatabase db = myDairyDatabaseHelper.getWritableDatabase();
                Cursor cursor = db.query("Dairy",null,"date = ?" ,new String[]{year+"年"+(month+1)+"月"+dayOfMonth+"日"},null,null,null);

                if(cursor.getCount() == 0) {
                    if(mediaPlayer11 != null){
                    mediaPlayer11.reset();}
                    //使用Toast显示用户选择的日期
                    Toast.makeText(Calendar.this,
                            "日期是" + year + "年" + (month + 1) + "月" + dayOfMonth + "日"
                            , Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Calendar.this, WriteDairy.class);
                    intent.putExtra("datestring", year + "年" + (month + 1) + "月" + dayOfMonth + "日");
                    startActivity(intent);
                } else {

                    if(cursor.moveToFirst()) {
                        String date = cursor.getString(cursor.getColumnIndex("date"));
                        String picture1 = cursor.getString(cursor.getColumnIndex("pictureUri"));
                        soundUri = cursor.getString(cursor.getColumnIndex("luyinUri"));
                        TextView datestring = (TextView) findViewById(R.id.dateXS_textview);
                        TextView weatherstring = (TextView) findViewById(R.id.weatherXS_textview);
                        TextView positionstring = (TextView) findViewById(R.id.dingweiXS_textview);
                        TextView wenzistring = (TextView) findViewById(R.id.wenziXS_edittext);
                        ImageView picture = (ImageView) findViewById(R.id.pictureXS_xiangce);
                        datestring.setText(date);
                        weatherstring.setText(cursor.getString(cursor.getColumnIndex("weather")));
                        positionstring.setText(cursor.getString(cursor.getColumnIndex("place")));
                        wenzistring.setText(cursor.getString(cursor.getColumnIndex("text")));
                        Bitmap bitmap = BitmapFactory.decodeFile(cursor.getString(cursor.getColumnIndex("pictureUri")));
                        picture.setImageBitmap(bitmap);
                        String musicuri = cursor.getString(cursor.getColumnIndex("musicUri"));

                            if (mediaPlayer11 == null) {
                                mediaPlayer11 = new MediaPlayer();
                                // 绑定播放完毕监听器
                            }
                            try {
                                // 切歌之前先重置，释放掉之前的资源
                                mediaPlayer11.reset();
                                // 设置播放源
                                if(musicuri != null) {
                                    mediaPlayer11.setDataSource(cursor.getString(cursor.getColumnIndex("musicUri")));
                                    // 开始播放前的准备工作，加载多媒体资源，获取相关信息
                                    mediaPlayer11.prepare();
                                    // 开始播放
                                    mediaPlayer11.start();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                Toast.makeText(Calendar.this, "播放错误", Toast.LENGTH_SHORT).show();
                            }
                    }
                }
                cursor.close();
            }
        });

        //暂停音乐按钮点击事件
        final Button pauseMusic = (Button) findViewById(R.id.pauseMusicXS_button);
        pauseMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer11.isPlaying()) {
                    mediaPlayer11.pause();
                    pauseMusic.setText("播放背景音乐");
                } else if (!mediaPlayer11.isPlaying()) {
                    mediaPlayer11.start();
                    pauseMusic.setText("暂停背景音乐");
                }
            }
        });

        //播放录音
        Button playsounds = (Button) findViewById(R.id.bofangluyinXS_button);
        playsounds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (mediaPlayer111 == null) {
                        mediaPlayer111 = new MediaPlayer();
                        // 绑定播放完毕监听器
                    }
                    try {
                        // 切歌之前先重置，释放掉之前的资源
                        mediaPlayer111.reset();
                        // 设置播放源
                        if(soundUri != null) {
                            mediaPlayer111.setDataSource(soundUri);
                            // 开始播放前的准备工作，加载多媒体资源，获取相关信息
                            mediaPlayer111.prepare();
                            // 开始播放
                            mediaPlayer111.start();
                        } else {
                            Toast.makeText(Calendar.this,"当天无语音日记",Toast.LENGTH_SHORT).show();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(Calendar.this, "播放错误", Toast.LENGTH_SHORT).show();
                    }

            }
        });



    }

    public void musicPlay(Music music) {
        if (mediaPlayer11 == null) {
            mediaPlayer11 = new MediaPlayer();
            // 绑定播放完毕监听器
        }
        try {
            // 切歌之前先重置，释放掉之前的资源
            mediaPlayer11.reset();
            // 设置播放源
            mediaPlayer11.setDataSource(music.getMusicUri());
            // 开始播放前的准备工作，加载多媒体资源，获取相关信息
            mediaPlayer11.prepare();
            // 开始播放
            mediaPlayer11.start();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "播放错误", Toast.LENGTH_SHORT).show();
        }

//        shijianzhou.setProgress(0);
//        shijianzhou.setMax(mediaPlayer.getDuration()); //获取时长
//        totalTime.setText(parseTime(mediaPlayer.getDuration()));
//
//        updateProgress();
    }
}
