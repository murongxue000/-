package com.example.mydairy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteDairy extends AppCompatActivity {

    private ImageView pictureXiangce;
    public static final int CHOOSE_PHOTO = 2;
    String dateString;
    String textString;
    String imagePathString;
    String positionString;
    String musicPathString;
    String soundPathString;
    String weatherString;
    private boolean isStart = false;
    private MediaRecorder mr = null;
    private MediaPlayer mediaPlayer1;
    public LocationClient mLocationClient;
    private TextView positionText;

    private MyDairyDatabaseHelper myDairyDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_dairy);

        EditText textString1 = (EditText) findViewById(R.id.wenzi_edittext);

        //日记本数据库
        myDairyDatabaseHelper = new MyDairyDatabaseHelper(WriteDairy.this,"DairyStore.db",null,1);

        //提交按钮
        Button submit = (Button) findViewById(R.id.submit_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent3 = getIntent();
//                musicPathString = intent3.getStringExtra("musicPath");
                soundPathString = "/storage/emulated/0/sounds/"+dateString+".amr";
                EditText textString1 = (EditText) findViewById(R.id.wenzi_edittext);
                textString = textString1.getText().toString();
                EditText weather = (EditText) findViewById(R.id.weather_textview);
                weatherString = weather.getText().toString();


                Dairy todayDairy = new Dairy(dateString,weatherString,positionString,textString,imagePathString,musicPathString,soundPathString);

                    SQLiteDatabase db = myDairyDatabaseHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("date",dateString);
                    values.put("weather",weatherString);
                    values.put("place",positionString);
                    values.put("text",textString);
                    values.put("pictureUri",imagePathString);
                    values.put("musicUri",musicPathString);
                    values.put("luyinUri",soundPathString);
                    db.insert("Dairy",null,values);
                    values.clear();
                    Toast.makeText(WriteDairy.this,dateString+"的日记已保存",Toast.LENGTH_SHORT).show();

                    Intent intent2 = new Intent(WriteDairy.this,Calendar.class);
                    startActivity(intent2);
            }
        });

        //获取定位
        positionText = (TextView) findViewById(R.id.dingwei_textview);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        List<String> permissionList = new ArrayList<>();
        if(ContextCompat.checkSelfPermission(WriteDairy.this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(WriteDairy.this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(ContextCompat.checkSelfPermission(WriteDairy.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if(!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(WriteDairy.this,permissions,1);
        } else {
            requestLocation();
        }



        //获取日期
        Intent intent = getIntent();
        dateString = intent.getStringExtra("datestring");
        TextView data = (TextView) findViewById(R.id.data_textview);
        data.setText(dateString);

        //获取文字
        EditText text = (EditText) findViewById(R.id.wenzi_edittext);
        textString = text.getText().toString();

        //从相册获取照片
        Button chooseFromAlbum = (Button) findViewById(R.id.addPicture_button);
        pictureXiangce = (ImageView) findViewById(R.id.picture_xiangce);
        chooseFromAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.
                checkSelfPermission(WriteDairy.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager
                        .PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(WriteDairy.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else{
                    openAlbum();
                }
            }
        });

        //点击音乐按钮跳转音乐列表
        Button addMusic = (Button) findViewById(R.id.addMusic_button);
        addMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WriteDairy.this,AddMusic.class);
                startActivityForResult(intent,1);
//                Intent intent1 = getIntent();
//                musicPathString = intent1.getStringExtra("musicPath");
            }
        });

        //开始录音
        final Button luyin = (Button) findViewById(R.id.luyin_button);
        luyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isStart){
                    startRecord();
                    luyin.setText("停止录制");
                    isStart = true;
                }else{
                    stopRecord();
                    luyin.setText("开始录制");
                    isStart = false;
                }
                    }
                });


        //播放录音
        Button bofang = (Button) findViewById(R.id.bofang_button);
        bofang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer1 == null) {
                    mediaPlayer1 = new MediaPlayer();
                    // 绑定播放完毕监听器
                }
                try {
                    // 切歌之前先重置，释放掉之前的资源
                    mediaPlayer1.reset();
                    // 设置播放源
                    mediaPlayer1.setDataSource("/storage/emulated/0/sounds/"+dateString+".amr");
                    // 开始播放前的准备工作，加载多媒体资源，获取相关信息
                    mediaPlayer1.prepare();
                    // 开始播放
                    mediaPlayer1.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(WriteDairy.this, "播放错误", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //开始录制
    private void startRecord(){
        if(mr == null){
            File dir = new File(Environment.getExternalStorageDirectory(),"sounds");
            if(!dir.exists()){
                dir.mkdirs();
            }
            File soundFile = new File(dir,dateString+".amr");
            if(!soundFile.exists()){
                try {
                    soundFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            mr = new MediaRecorder();
            mr.setAudioSource(MediaRecorder.AudioSource.MIC);  //音频输入源
            mr.setOutputFormat(MediaRecorder.OutputFormat.AMR_WB);   //设置输出格式
            mr.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_WB);   //设置编码格式
            mr.setOutputFile(soundFile.getAbsolutePath());
            try {
                mr.prepare();
                mr.start();  //开始录制
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        switch (requestCode) {
            case 1:
                if(grantResults.length > 0) {
                    for(int result : grantResults) {
                        if(result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this,"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    openAlbum();
                }
                else {
                    Toast.makeText(this,"发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
                default:
        }
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(final BDLocation location) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();
                    currentPosition.append(location.getCountry()).append(" · ");
                    currentPosition.append(location.getProvince()).append(" · ");
                    currentPosition.append(location.getCity()).append(" · ");
                    currentPosition.append(location.getDistrict()).append(" · ");
                    currentPosition.append(location.getStreet()).append("\n");
//                    if(location.getLocType() == BDLocation.TypeGpsLocation) {
//                        currentPosition.append("GPS");
//                    } else if (location.getLocType() == BDLocation.TypeNetWorkLocation){
//                        currentPosition.append("网络");
//                    }
//                    Toast.makeText(WriteDairy.this,"定位成功",Toast.LENGTH_SHORT).show();
                    positionString = currentPosition.toString();
                    positionText.setText(currentPosition);
                }
            });

        }
        public void onConnectHotSpotMessage(String s , int i){}

    }

    //停止录制，资源释放
    private void stopRecord(){
        if(mr != null){
            mr.stop();
            mr.release();
            mr = null;
        }
    }


    private void openAlbum(){
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }


//    public void onRequestPermissionResult(int requestCode, String[] permissions,int[] grantResults) {
//        switch (requestCode) {
//            case 1:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                    openAlbum();
//                } else {
//                    Toast.makeText(this,"You denied the permisson",Toast.LENGTH_SHORT).show();
//                }
//                break;
//                default:
//        }
//    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if(resultCode == RESULT_OK) {
                    //判断手机系统版本号
                    if(Build.VERSION.SDK_INT >= 19){
                        //4.4及以上系统使用这个办法处理图片
                        handleImageOnkitkat(data);
                    } else {
                        //4.4及以下系统使用这个办法处理图片
                        handleImageBeforeKitkat(data);
                    }
                }
                break;

            case 1:
                if(resultCode == RESULT_OK) {
                    String returneData = data.getStringExtra("musicPath");
                    musicPathString = returneData;
                }
                default:
                    break;
        }
    }


    @TargetApi(19)
    private void handleImageOnkitkat(Intent data){
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri)) {
            //如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];//解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.document".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content:" +
                        "//downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        }else if("file".equalsIgnoreCase(uri.getScheme())) {
                //如果是file类型的Uri，直接获取图片路径即可
                imagePath = uri.getPath();
            }
            displayImage(imagePath);
        }


        private void handleImageBeforeKitkat(Intent data){
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
        }

        private String getImagePath(Uri uri,String selection){
        String path = null;
        //通过Uri和selection来获取真实的图片路径
            Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
            if(cursor != null) {
                if(cursor.moveToFirst()) {
                    path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                }
                cursor.close();
            }
            //全局变量获取照片路径
            imagePathString = path;
            return path;
        }

        private void displayImage(String imagePath){
        if(imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            pictureXiangce.setImageBitmap(bitmap);
        } else {
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
          }
        }



    }

