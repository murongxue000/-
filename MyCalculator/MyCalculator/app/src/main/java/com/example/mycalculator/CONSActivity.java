package com.example.mycalculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CONSActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private int before;
    private int after;
    private TextView textView;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons);
        textView = (TextView) findViewById(R.id.result_view);
        editText = (EditText) findViewById(R.id.input_number);

        RadioGroup radioGroupBefore = (RadioGroup) findViewById(R.id.listBefore);
        radioGroupBefore.setOnCheckedChangeListener(this);

        RadioGroup radioGroupResult = (RadioGroup) findViewById(R.id.listAfter);
        radioGroupResult.setOnCheckedChangeListener(this);

        Button buttonDO = (Button) findViewById(R.id.do_button);
        buttonDO.setOnClickListener(this);
        Button buttonChangdu = (Button) findViewById(R.id.changdu_button);
        buttonChangdu.setOnClickListener(this);
        Button buttonTiji = (Button) findViewById(R.id.tiji_button);
        buttonTiji.setOnClickListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checked) {
        switch (checked) {
            case R.id.before_choose10:
                before = 10;
                break;
            case R.id.before_choose2:
                before = 2;
                break;
            case R.id.before_choose8:
                before = 8;
                break;
            case R.id.before_choose16:
                before = 16;
                break;
            case R.id.after_choose10:
                after = 10;
                break;
            case R.id.after_choose2:
                after = 2;
                break;
            case R.id.after_choose8:
                after = 8;
                break;
            case R.id.after_choose16:
                after = 16;
                break;
            default:
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.do_button:
                String inputtext = editText.getText().toString();
                if (before == 0 || after == 0 || inputtext.equals("") == true) {
                    Toast.makeText(CONSActivity.this, "请将相互转换的进制和原始数据填选完整", Toast.LENGTH_SHORT).show();
                } else
                    Do(before, after, inputtext);
                break;
            case R.id.changdu_button:
                Intent intent = new Intent(CONSActivity.this,ChangduActivity.class);
                startActivity(intent);
                break;
            case R.id.tiji_button:
                Intent intent1 = new Intent(CONSActivity.this,TijiActivity.class);
                startActivity(intent1);
                break;
            default:
        }
    }

    public void Do(int before, int after, String inputNumber) {
        String n="";
        switch (before) {
            case 10:
                n=doFirst(10, inputNumber);
                break;
            case 2:
                n=doFirst(2, inputNumber);
                break;
            case 8:
                n=doFirst(8, inputNumber);
                break;
            case 16:
                n=doFirst(16, inputNumber);
                break;
            default:
        }


        switch (after) {
            case 10:
                doAfter(10, n);
                break;
            case 2:
                doAfter(2, n);
                break;
            case 8:
                doAfter(8, n);
                break;
            case 16:
                doAfter(16,n);
                break;
            default:
        }

    }

    public String doFirst(int before, String inputNumber) {

        String m="";
        switch (before) {
            case 10:
                int number = Integer.parseInt(inputNumber);
                int sum;
                String x="";
                for (int i = number; i >= 1; i /= 2) {
                    if (i % 2 == 0) {
                        sum = 0;
                    } else {
                        sum = 1;
                    }
                    m = m + sum;
                }
                for(int j=m.length()-1;j>=0;j--){
                    x= x+m.charAt(j);
                }
                return x;

            case 2:
                m = inputNumber;
                return m;

            case 8:
                for (int i = 0; i < inputNumber.length(); i++) {
                    char a = inputNumber.charAt(i);
                    switch (a) {
                        case '0':
                            m = m + "000";
                            break;
                        case '1':
                            m = m + "001";
                            break;
                        case '2':
                            m = m + "010";
                            break;
                        case '3':
                            m = m + "011";
                            break;
                        case '4':
                            m = m + "100";
                            break;
                        case '5':
                            m = m + "101";
                            break;
                        case '6':
                            m = m + "110";
                            break;
                        case '7':
                            m = m + "111";
                            break;
                        default:
                    }
                }
                return m;

            case 16:
                for (int i = 0; i < inputNumber.length(); i++) {
                    char b = inputNumber.charAt(i);
                    switch (b) {
                        case '0':
                            m = m + "0000";
                            break;
                        case '1':
                            m = m + "0001";
                            break;
                        case '2':
                            m = m + "0010";
                            break;
                        case '3':
                            m = m + "0011";
                            break;
                        case '4':
                            m = m + "0100";
                            break;
                        case '5':
                            m = m + "0101";
                            break;
                        case '6':
                            m = m + "0110";
                            break;
                        case '7':
                            m = m + "0111";
                            break;
                        case '8':
                            m = m + "1000";
                            break;
                        case '9':
                            m = m + "1001";
                            break;
                        case 'A':
                            m = m + "1010";
                            break;
                        case 'B':
                            m = m + "1011";
                            break;
                        case 'C':
                            m = m + "1100";
                            break;
                        case 'D':
                            m = m + "1101";
                            break;
                        case 'E':
                            m = m + "1110";
                            break;
                        case 'F':
                            m = m + "1111";
                            break;
                        default:
                    }
                }
                return m;
            default:
                return "";
        }
    }

    public void doAfter(int after,String m) {
        switch (after) {
            case 2:
                textView.setText(m);
                break;
            case 8:
                String N="";
                String n = "";
                int x = 0;
                int y = 0;
                for (int i = m.length()-1; i >= 0; i--) {
                    y++;
                    if(y != m.length()) {
                        switch (y % 3) {
                            case 1:
                                x = x + 1 * (m.charAt(i)-'0');
                                break;
                            case 2:
                                x = x + 2 * (m.charAt(i)-'0');
                                break;
                            case 0:
                                x = x + 4 * (m.charAt(i)-'0');
                                n = n + x;
                                x = 0;
                                break;
                            default:
                        }
                    }else
                    if (y == m.length()) {
                        switch (y % 3) {
                            case 1:
                                x = x + 1 * (m.charAt(i) - '0');
                                n = n + x;
                                x = 0;
                                break;
                            case 2:
                                x = x + 2 * (m.charAt(i) - '0');
                                n = n + x;
                                x = 0;
                                break;
                            case 0:
                                x = x + 4 * (m.charAt(i) - '0');
                                n = n + x;
                                x = 0;
                                break;
                            default:
                        }
                    }
                }
                for(int j=n.length()-1;j>=0;j--){
                    N= N+n.charAt(j);
                }
                textView.setText(N);
                break;

            case 16:
                String NN="";
                String nn = "";
                int xx = 0;
                int yy = 0;
                for (int i = m.length()-1; i >= 0; i--) {
                    yy++;
                    if(yy != m.length()) {
                        switch (yy % 4) {
                            case 1:
                                xx = xx + 1 * (m.charAt(i)-'0');
                                break;
                            case 2:
                                xx = xx + 2 * (m.charAt(i)-'0');
                                break;
                            case 3:
                                xx = xx + 4 * (m.charAt(i)-'0');
                                break;
                            case 0:
                                xx = xx + 8 * (m.charAt(i)-'0');
                                if(xx>9){
                                    switch (xx){
                                        case 10: nn = nn + "A"; xx=0; break;
                                        case 11: nn = nn + "B"; xx=0;break;
                                        case 12: nn = nn + "C"; xx=0;break;
                                        case 13: nn = nn + "D"; xx=0;break;
                                        case 14: nn = nn + "E"; xx=0;break;
                                        case 15: nn = nn + "F"; xx=0;break;
                                        default:
                                    }
                                }
                                else{
                                nn = nn + xx;
                                xx = 0;}
                                break;
                            default:
                        }
                    }else
                    if (yy == m.length()) {
                            switch (yy % 4) {
                                case 1:
                                    xx = xx + 1 * (m.charAt(i)-'0');
                                    if(xx>9){
                                        switch (xx){
                                            case 10: nn = nn + "A"; xx=0; break;
                                            case 11: nn = nn + "B"; xx=0;break;
                                            case 12: nn = nn + "C"; xx=0;break;
                                            case 13: nn = nn + "D"; xx=0;break;
                                            case 14: nn = nn + "E"; xx=0;break;
                                            case 15: nn = nn + "F"; xx=0;break;
                                            default:
                                        }
                                    }
                                    else{
                                        nn = nn + xx;
                                        xx = 0;}
                                    break;
                                case 2:
                                    xx = xx + 2 * (m.charAt(i)-'0');
                                    if(xx>9){
                                        switch (xx){
                                            case 10: nn = nn + "A"; xx=0; break;
                                            case 11: nn = nn + "B"; xx=0;break;
                                            case 12: nn = nn + "C"; xx=0;break;
                                            case 13: nn = nn + "D"; xx=0;break;
                                            case 14: nn = nn + "E"; xx=0;break;
                                            case 15: nn = nn + "F"; xx=0;break;
                                            default:
                                        }
                                    }
                                    else{
                                        nn = nn + xx;
                                        xx = 0;}
                                    break;
                                case 3:
                                    xx = xx + 4 * (m.charAt(i)-'0');
                                    if(xx>9){
                                        switch (xx){
                                            case 10: nn = nn + "A"; xx=0; break;
                                            case 11: nn = nn + "B"; xx=0;break;
                                            case 12: nn = nn + "C"; xx=0;break;
                                            case 13: nn = nn + "D"; xx=0;break;
                                            case 14: nn = nn + "E"; xx=0;break;
                                            case 15: nn = nn + "F"; xx=0;break;
                                            default:
                                        }
                                    }
                                    else{
                                        nn = nn + xx;
                                        xx = 0;}
                                    break;
                                case 0:
                                    xx = xx + 8 * (m.charAt(i)-'0');
                                    if(xx>9){
                                        switch (xx){
                                            case 10: nn = nn + "A"; xx=0; break;
                                            case 11: nn = nn + "B"; xx=0;break;
                                            case 12: nn = nn + "C"; xx=0;break;
                                            case 13: nn = nn + "D"; xx=0;break;
                                            case 14: nn = nn + "E"; xx=0;break;
                                            case 15: nn = nn + "F"; xx=0;break;
                                            default:
                                        }
                                    }
                                    else{
                                        nn = nn + xx;
                                        xx = 0;}
                                    xx = 0;
                                    break;
                                default:
                        }
                    }
                }
                for(int j=nn.length()-1;j>=0;j--){
                    NN= NN+nn.charAt(j);
                }
                textView.setText(NN);
                break;

            case 10:
                double u=0;
                int g=-1; //计位数
                for (int i = m.length()-1; i >= 0; i--){
                    g++;
                    u = u+(m.charAt(i)-'0')*Math.pow(2,g);
                }
                String uu = String.valueOf(u);
                textView.setText(uu);
                break;
                default:
        }
    }
}
