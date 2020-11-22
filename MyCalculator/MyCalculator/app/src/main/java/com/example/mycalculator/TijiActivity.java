package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TijiActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener{

    private int beforetiji=0;
    private int aftertiji=0;

    private TextView textViewtiji;
    private EditText editTexttiji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiji);
        textViewtiji = (TextView) findViewById(R.id.result_view_tiji);
        editTexttiji = (EditText) findViewById(R.id.input_tijinumber);

        RadioGroup radioGroupBeforetiji = (RadioGroup) findViewById(R.id.listBefore_tiji);
        radioGroupBeforetiji.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);

        RadioGroup radioGroupResulttiji = (RadioGroup) findViewById(R.id.listAfter_tiji);
        radioGroupResulttiji.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);

        Button buttonDO = (Button) findViewById(R.id.do_button_tiji);
        buttonDO.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checked) {
        switch (checked) {
            case R.id.before_chooseCM3:
                beforetiji = 1;
                break;
            case R.id.before_chooseM3:
                beforetiji = 1000000;
                break;
            case R.id.before_chooseML:
                beforetiji = 1;
                break;
            case R.id.before_chooseL:
                beforetiji = 1000;
                break;
            case R.id.after_chooseCM3:
                aftertiji = 1;
                break;
            case R.id.after_chooseM3:
                aftertiji = 1000000;
                break;
            case R.id.after_chooseML:
                aftertiji = 1;
                break;
            case R.id.after_chooseL:
                aftertiji = 1000;
                break;
            default:
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.do_button_tiji:
                String inputtext = editTexttiji.getText().toString();
                if (beforetiji == 0 || aftertiji == 0 || inputtext.equals("") == true) {
                    Toast.makeText(TijiActivity.this, "请将体积单位的进制和原始数据填选完整", Toast.LENGTH_SHORT).show();
                } else
                    Dotiji(inputtext);
                break;
            default:
        }
    }

    public void Dotiji(String inputnumber) {

        float number=0; //记录整数部分
        float decimal=0; //记录小数部分
        float total=0; //记录总和
        int NoD = 0; //判断整数(0)或小数(1)
        int d = 0;//小数位数
        char[] textChar;
        textChar = inputnumber.toCharArray();

        for (int i = 0; i < textChar.length; i++) {

            if (NoD == 0 && textChar[i] <= '9' && textChar[i] >= '0') {
                number = number * 10 + (textChar[i] - '0');
            }

            if (NoD == 1 && textChar[i] <= '9' && textChar[i] >= '0') {
                d++;
                float x = textChar[i] - '0';
                for (int j = 0; j < d; j++) {
                    x = x / 10;
                }
                decimal = decimal + x;
            }

            if (textChar[i] == '.') {
                NoD = 1;
            }
        }
        total = number + decimal;
        number=0;
        decimal=0;
        NoD=0;
        d=0;

        float f = 0;
        f =(float) beforetiji/aftertiji ;
        total = total * f;
        String s;
        s=String.valueOf(total);
        textViewtiji.setText(s);

    }
}
