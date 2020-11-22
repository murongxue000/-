package com.example.mydairy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddMusic extends AppCompatActivity implements View.OnClickListener{

    private MediaPlayer mediaPlayer;
    private MyDatabaseHelper dbHelper;
    private List<Music> mymusic = new ArrayList<>();
    private int musicPosition;
    private SeekBar shijianzhou;
    private TextView currentTime;
    private TextView totalTime;

//    private Handler mHandler = new Handler(new Handler.Callback() {
//        @Override
//        public boolean handleMessage(Message msg) {
//
//            // 展示给进度条和当前时间
//            if(mediaPlayer != null) {
//                int progress = mediaPlayer.getCurrentPosition();
//                shijianzhou.setProgress(progress);
//                currentTime.setText(parseTime(progress));
//                // 继续定时发送数据
//                updateProgress();
//            }
//                return true;
//
//        }
//    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_music);dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);

        Button addMusic = (Button) findViewById(R.id.tianjia_button);

        final ListView myMusicList = (ListView) findViewById(R.id.musiclistY_view);

        addMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mymusic.clear();
                dbHelper.getWritableDatabase();
                initMusic();
                musicAdapter adapter = new musicAdapter(AddMusic.this, R.layout.music_item, mymusic);
                myMusicList.setAdapter(adapter);
            }
        });

        //音乐列表的点击事件
        myMusicList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Music music = mymusic.get(position);
                musicPlay(music);
                musicPosition = position;
                Toast.makeText(AddMusic.this, music.getMusicTitle(), Toast.LENGTH_SHORT).show();

            }
        });

        Button play = (Button) findViewById(R.id.play_button);
        Button pause = (Button) findViewById(R.id.pause_button);
        Button delete = (Button) findViewById(R.id.delete_button);
        Button chooseThis = (Button) findViewById(R.id.chooseThis_button);
        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        delete.setOnClickListener(this);
        chooseThis.setOnClickListener(this);

//        shijianzhou = (SeekBar) findViewById(R.id.shijianzhou_seek_bar);
//        shijianzhou.setOnSeekBarChangeListener(this);
//        currentTime = (TextView) findViewById(R.id.current_time_tv);
//        totalTime = (TextView) findViewById(R.id.total_time_tv);

        if (ContextCompat.checkSelfPermission(AddMusic.this, Manifest.permission.
                WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(AddMusic.this, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

    }


    public void musicPlay(Music music) {
        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
            // 绑定播放完毕监听器
        }
        try {
            // 切歌之前先重置，释放掉之前的资源
            mediaPlayer.reset();
            // 设置播放源
            mediaPlayer.setDataSource(music.getMusicUri());
            // 开始播放前的准备工作，加载多媒体资源，获取相关信息
            mediaPlayer.prepare();
            // 开始播放
            mediaPlayer.start();
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    initMediaPlayer();
                } else {
                    Toast.makeText(this, "拒绝使用权限无法使用程序", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }


    private void initMusic() {  //初始化歌单列表，将数据库中的歌填入
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete("Book", "musicTitle != ?", new String[]{""});
        ContentValues values = new ContentValues();
        values.put("musicTitle", "再见.mp3");
        File file1 = new File(Environment.getExternalStorageState(), "再见.mp3");
        values.put("musicUri", "/storage/emulated/0/Pictures/再见.mp3");
        db.insert("Book", null, values);
        values.clear();

        values.put("musicTitle", "单行的轨道.mp3");
        File file2 = new File(Environment.getExternalStorageState(), "单行的轨道.mp3");
        values.put("musicUri", "/storage/emulated/0/Pictures/单行的轨道.mp3");
        db.insert("Book", null, values);
        values.clear();

        values.put("musicTitle", "Dancing.mp3");
        File file3 = new File(Environment.getExternalStorageState(), "Dancing.mp3");
        values.put("musicUri", "/storage/emulated/0/Pictures/dancing.mp3");
        db.insert("Book", null, values);
        values.clear();

        values.put("musicTitle", "We Don't Talk Anymore.mp3");
        File file4 = new File(Environment.getExternalStorageState(), "We Don't Talk Anymore.mp3");
        values.put("musicUri", "/storage/emulated/0/Pictures/We Don't Talk Anymore.mp3");
        db.insert("Book", null, values);
        values.clear();

        values.put("musicTitle", "新的心跳.mp3");
        File file5 = new File(Environment.getExternalStorageState(), "新的心跳.mp3");
        values.put("musicUri", "/storage/emulated/0/Pictures/新的心跳.mp3");
        db.insert("Book", null, values);
        values.clear();

        values.put("musicTitle", "木偶人.mp3");
        File file6 = new File(Environment.getExternalStorageState(), "木偶人.mp3");
        values.put("musicUri", "/storage/emulated/0/Pictures/木偶人.mp3");
        db.insert("Book", null, values);
        values.clear();

        values.put("musicTitle", "于是.mp3");
        File file7 = new File(Environment.getExternalStorageState(), "于是.mp3");
        values.put("musicUri", "/storage/emulated/0/Pictures/于是.mp3");
        db.insert("Book", null, values);
        values.clear();

        values.put("musicTitle", "盲点.mp3");
        File file8 = new File(Environment.getExternalStorageState(), "盲点.mp3");
        values.put("musicUri", "/storage/emulated/0/Pictures/盲点.mp3");
        db.insert("Book", null, values);
        values.clear();

        values.put("musicTitle", "毒苹果.mp3");
        File file9 = new File(Environment.getExternalStorageState(), "毒苹果.mp3");
        values.put("musicUri", "/storage/emulated/0/Pictures/毒苹果.mp3");
        db.insert("Book", null, values);
        values.clear();


        Cursor cursor = db.query("Book", null, null, null, null, null, null);
        if (cursor != null)
            while (cursor.moveToNext()) {
                Music music = new Music();
                music.setMusicName(cursor.getString(cursor.getColumnIndex("musicTitle")));
                music.setMusicUri(cursor.getString(cursor.getColumnIndex("musicUri")));
                mymusic.add(music);
            }
        cursor.close();

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play_button:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.pause_button:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;

            case R.id.delete_button:
                mediaPlayer.stop();
                Music music4 = mymusic.get(musicPosition);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.delete("Book", "musicTitle = ?", new String[]{music4.getMusicTitle()});
                mymusic.clear();
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor != null)
                    while (cursor.moveToNext()) {
                        Music music = new Music();
                        music.setMusicName(cursor.getString(cursor.getColumnIndex("musicTitle")));
                        music.setMusicUri(cursor.getString(cursor.getColumnIndex("musicUri")));
                        mymusic.add(music);
                    }
                cursor.close();
                musicAdapter adapter = new musicAdapter(AddMusic.this, R.layout.music_item, mymusic);
                ListView myMusicList = (ListView) findViewById(R.id.musiclistY_view);
                myMusicList.setAdapter(adapter);
                break;

            case R.id.chooseThis_button:
                Music music11 = mymusic.get(musicPosition);
                //获取音乐的路径 name:musicPath
                String musicpath = music11.getMusicUri();
                Intent intent1 = new Intent();
                intent1.putExtra("musicPath",musicpath);
                setResult(RESULT_OK,intent1);
                mediaPlayer.stop();
            default:
                break;
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    // 解析时间
//    private String parseTime(int oldTime) {
//        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");// 时间格式
//        String newTime = sdf.format(new Date(oldTime));
//        return newTime;
//    }
//
//    // 每间隔1s通知更新进度
//    private void updateProgress() {
//        // 使用Handler每间隔1s发送一次空消息，通知进度条更新
//        Message msg = Message.obtain();// 获取一个现成的消息
//        mHandler.sendMessageDelayed(msg, INTERNAL_TIME);
//    }
//    private static final int INTERNAL_TIME = 1000;// 音乐进度间隔时间
//
//    @Override
//    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//
//    }
//
//    @Override
//    public void onStartTrackingTouch(SeekBar seekBar) {
//
//    }
//
//    // 当手停止拖拽进度条时执行该方法
//// 获取拖拽进度
//// 将进度对应设置给MediaPlayer
//    @Override
//    public void onStopTrackingTouch(SeekBar seekBar) {
//        int progress = seekBar.getProgress();
//        mediaPlayer.seekTo(progress);
//    }

}
