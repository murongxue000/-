package com.example.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ChangduActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private int beforechangdu=0;
    private int afterchangdu=0;

    private TextView textViewchangdu;
    private EditText editTextchangdu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changdu);
        textViewchangdu = (TextView) findViewById(R.id.result_view_changdu);
        editTextchangdu = (EditText) findViewById(R.id.input_changdunumber);

        RadioGroup radioGroupBeforechangdu = (RadioGroup) findViewById(R.id.listBefore_changdu);
        radioGroupBeforechangdu.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);

        RadioGroup radioGroupResultchangdu = (RadioGroup) findViewById(R.id.listAfter_changdu);
        radioGroupResultchangdu.setOnCheckedChangeListener((RadioGroup.OnCheckedChangeListener) this);

        Button buttonDO = (Button) findViewById(R.id.do_button_changdu);
        buttonDO.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checked) {
        switch (checked) {
            case R.id.before_chooseMM:
                beforechangdu = 1;
                break;
            case R.id.before_chooseCM:
                beforechangdu = 10;
                break;
            case R.id.before_chooseM:
                beforechangdu = 1000;
                break;
            case R.id.before_chooseKM:
                beforechangdu = 1000000;
                break;
            case R.id.after_chooseMM:
                afterchangdu = 1;
                break;
            case R.id.after_chooseCM:
                afterchangdu = 10;
                break;
            case R.id.after_chooseM:
                afterchangdu = 1000;
                break;
            case R.id.after_chooseKM:
                afterchangdu = 1000000;
                break;
            default:
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.do_button_changdu:
                String inputtext = editTextchangdu.getText().toString();
                if (beforechangdu == 0 || afterchangdu == 0 || inputtext.equals("") == true) {
                    Toast.makeText(ChangduActivity.this, "请将长度单位的进制和原始数据填选完整", Toast.LENGTH_SHORT).show();
                } else
                    Dochangdu(inputtext);
                break;
            default:
        }
    }

    public void Dochangdu(String inputnumber) {

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
        f =(float) beforechangdu/afterchangdu ;
        total = total * f;
        String s;
        s=String.valueOf(total);
        textViewchangdu.setText(s);

    }
}