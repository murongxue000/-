package com.example.mycalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    final String S="sin";
    final String C="con";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number_layout);
        textView = (TextView) findViewById(R.id.text_view);

        Button button1 = (Button) findViewById(R.id.Button1);
        button1.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.Button2);
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.Button3);
        button3.setOnClickListener(this);
        Button button4 = (Button) findViewById(R.id.Button4);
        button4.setOnClickListener(this);
        Button button5 = (Button) findViewById(R.id.Button5);
        button5.setOnClickListener(this);
        Button button6 = (Button) findViewById(R.id.Button6);
        button6.setOnClickListener(this);
        Button button7 = (Button) findViewById(R.id.Button7);
        button7.setOnClickListener(this);
        Button button8 = (Button) findViewById(R.id.Button8);
        button8.setOnClickListener(this);
        Button button9 = (Button) findViewById(R.id.Button9);
        button9.setOnClickListener(this);
        Button button0 = (Button) findViewById(R.id.Button0);
        button0.setOnClickListener(this);
        Button buttonAC = (Button) findViewById(R.id.ButtonAC);
        buttonAC.setOnClickListener(this);
        Button buttonPoint = (Button) findViewById(R.id.ButtonPoint);
        buttonPoint.setOnClickListener(this);
        Button buttonPlus = (Button) findViewById(R.id.ButtonPlus);
        buttonPlus.setOnClickListener(this);
        Button buttonMinus = (Button) findViewById(R.id.ButtonMinus);
        buttonMinus.setOnClickListener(this);
        Button buttonMultiple = (Button) findViewById(R.id.ButtonMultiple);
        buttonMultiple.setOnClickListener(this);
        Button buttonDivision = (Button) findViewById(R.id.ButtonDivision);
        buttonDivision.setOnClickListener(this);
        Button buttonRight = (Button) findViewById(R.id.ButtonRight);
        buttonRight.setOnClickListener(this);
        Button buttonLeft = (Button) findViewById(R.id.ButtonLeft);
        buttonLeft.setOnClickListener(this);
        Button buttonMI = (Button) findViewById(R.id.ButtonMI);
        buttonMI.setOnClickListener(this);
        Button buttonBack = (Button) findViewById(R.id.ButtonBack);
        buttonBack.setOnClickListener(this);
        Button buttonSin = (Button) findViewById(R.id.ButtonSin);
        buttonSin.setOnClickListener(this);
        Button buttonCon = (Button) findViewById(R.id.ButtonCos);
        buttonCon.setOnClickListener(this);
        Button buttonEqual = (Button) findViewById(R.id.ButtonEqual);
        buttonEqual.setOnClickListener(this);
        Button buttonCONS = (Button) findViewById(R.id.ButtonCONS);
        buttonCONS.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.exit_item:
                finish();
                break;
            case R.id.help_item:
                Toast.makeText(this, "这是帮助", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Button1:
                changeText(textView, "1");
                break;
            case R.id.Button2:
                changeText(textView, "2");
                break;
            case R.id.Button3:
                changeText(textView, "3");
                break;
            case R.id.Button4:
                changeText(textView, "4");
                break;
            case R.id.Button5:
                changeText(textView, "5");
                break;
            case R.id.Button6:
                changeText(textView, "6");
                break;
            case R.id.Button7:
                changeText(textView, "7");
                break;
            case R.id.Button8:
                changeText(textView, "8");
                break;
            case R.id.Button9:
                changeText(textView, "9");
                break;
            case R.id.Button0:
                changeText(textView, "0");
                break;
            case R.id.ButtonAC:
                textView.setText("");
                break;
            case R.id.ButtonDivision:
                changeText(textView, "÷");
                break;
            case R.id.ButtonLeft:
                changeText(textView, "(");
                break;
            case R.id.ButtonRight:
                changeText(textView, ")");
                break;
            case R.id.ButtonPlus:
                changeText(textView, "+");
                break;
            case R.id.ButtonPoint:
                changeText(textView, ".");
                break;
            case R.id.ButtonMinus:
                changeText(textView, "-");
                break;
            case R.id.ButtonMultiple:
                changeText(textView, "×");
                break;
            case R.id.ButtonMI:
                changeText(textView, "^");
                break;
            case R.id.ButtonCos:
                changeText(textView, "cos");
                break;
            case R.id.ButtonSin:
                changeText(textView, "sin");
                break;
            case R.id.ButtonBack:
                backtext(textView);
                break;
            case R.id.ButtonEqual:
                changeText(textView,"=");
                Deal(textView);
                break;
            case R.id.ButtonCONS:
                Intent intent = new Intent(MainActivity.this,CONSActivity.class);
                startActivity(intent);
                break;
            default:
        }
    }

    public void backtext(TextView textView) {
        String text;
        char[] textchar;
        text = textView.getText().toString();
        textchar = text.toCharArray();

        if (text == "") {
            textView.setText(text);
        } else if (textchar.length == 1) {
            textView.setText("");
        } else if (text != "" && textchar[textchar.length-1]!='n' && textchar[textchar.length-1]!='s') {
            char[] textchar1 = new char[textchar.length - 1];
            for (int i = 0; i <= textchar.length - 2; i++) {
                textchar1[i] = textchar[i];
            }
            String textnew = new String(textchar1);
            textView.setText(textnew);
        }else if(textchar[textchar.length-1]=='n' || textchar[textchar.length-1]=='s'){
            if(textchar.length==3){textView.setText("");}
            else {
                char[] textchar2 = new char[textchar.length - 3];
                for (int i = 0; i <= textchar.length - 4; i++) {
                    textchar2[i] = textchar[i];
                }
                String textnew = new String(textchar2);
                textView.setText(textnew);
            }
        }
    }


    public void changeText(TextView textView, String string) {
        String text;
        char[] textchar;
        char last;
        text = textView.getText().toString();

        if (text == "") {
            if (string == "+" || string == "-" || string == "×" || string == "÷" || string == ")" || string == "^" ||
                    string == "=" || string == "." || string == "←") {
                textView.setText(text);
            }
            else{text = text + string;
                textView.setText(text);}
        }

        else {

            textchar = text.toCharArray();
            last = textchar[textchar.length - 1];

            if (last != '0' && last != '1' && last != '2' && last != '3' && last != '4' && last != '5' &&
                    last != '6' && last != '7' && last != '8' && last != '9' && last !=')') {
                if (string == "+" || string == "-" || string == "×" || string == "÷" || string == ")" || string == "^" ||
                        string == "=" || string == "." ) { textView.setText(text); }
                else{text = text + string;
                    textView.setText(text);}
            }
            else
            {text = text + string;
             textView.setText(text);}

        }
    }


    public void Deal(TextView textView){
        String text;
        text = textView.getText().toString();
        char[] textChar;
        textChar = text.toCharArray();
        Double [] numberArray = new Double[50];

        String houzhui = ""; // 后缀表达式
        double number=0; //记录整数部分
        double decimal=0; //记录小数部分
        double total=0; //记录总和
        int NoD = 0; //判断整数(0)或小数(1)
        int YorNMI = 0;
        float MI1=0;
        int d = 0;//小数位数
        int cosORsin=0;//cos（1）还是sin（0）

        SeqStack NumSeqStack = new SeqStack();
        SeqStack MobelSeqStack = new SeqStack();

        for(int i=0;i<textChar.length;i++){

          /*  if(textChar[i]=='^'){ YorNMI=1; MI1=number; number=0;}*/

            if(NoD==0 && textChar[i]<='9' && textChar[i]>='0'){
                number = number*10 + (textChar[i]-'0');
            }

            if(NoD==1 && textChar[i]<='9' && textChar[i]>='0'){
                d++;
                float x = textChar[i]-'0';
                for(int j=0;j<d;j++){x = x/10;}
                decimal = decimal + x;
            }

            if(textChar[i]=='.'){
                NoD = 1;
            }

            if(textChar[i]=='^'){
                MobelSeqStack.push(textChar[i]);
                total = number + decimal;
                if(total!=0) {
                    NumSeqStack.push(total);
                }
                number=0;
                decimal=0;
                total=0;
                NoD=0;
                d=0;
            }

            if(cosORsin==0 && (textChar[i]=='s' || textChar[i]=='i' || textChar[i]=='n')){
                switch (textChar[i]){
                    case 'n':
                        MobelSeqStack.push(textChar[i]);
                        total = number + decimal;
                        if(total!=0) {
                            NumSeqStack.push(total);
                        }
                        else if(total==0){
                            double o=1;
                            NumSeqStack.push(o);}
                        number=0;
                        decimal=0;
                        total=0;
                        NoD=0;
                        d=0;
                        break;
                    case 's':
                        break;
                    case 'i':
                        break;
                        default:
                }
            }
            if(cosORsin==1 && (textChar[i]=='c' || textChar[i]=='o' || textChar[i]=='s')){
                switch (textChar[i]){
                    case 's':
                        MobelSeqStack.push(textChar[i]);
                        total = number + decimal;
                        if(total!=0) {
                            NumSeqStack.push(total);
                        }
                        else if(total==0){
                            double o=1;
                            NumSeqStack.push(o);}
                        number=0;
                        decimal=0;
                        total=0;
                        NoD=0;
                        d=0;
                        cosORsin=0;
                        break;
                    case 'c':
                        cosORsin = 1;
                        break;
                    case 'o':
                        break;
                    default:
                }
            }
            if(textChar[i]=='c'){cosORsin = 1;}

            if(textChar[i]=='×' || textChar[i] == '÷' || textChar[i]=='('){
                if(MobelSeqStack.isEmpty()==false &&(  MobelSeqStack.peek().toString().equals("s")|| MobelSeqStack.peek().toString().equals("n") || MobelSeqStack.peek().toString().equals("^") )){
                    total = number + decimal;
                    NumSeqStack.push(total);
                    decimal=0;
                    number=0;
                    total = 0;
                    NoD=0;
                    d=0;
                    double a=0;
                    double b=0;
                    a=(double)NumSeqStack.pop();
                    b=(double)NumSeqStack.pop();

                    switch (MobelSeqStack.pop().toString()){
                        case "^":
                            NumSeqStack.push(Math.pow(b,a));
                            break;
                        case "n":
                            NumSeqStack.push(b*Math.sin(a));
                            break;
                        case "s":
                            NumSeqStack.push(b*Math.cos(a));
                            break;
                        default:
                    }
                    MobelSeqStack.push(textChar[i]);
                }
                else {
                    MobelSeqStack.push(textChar[i]);
                    total = number + decimal;
                    if(total!=0) {
                        NumSeqStack.push(total);
                    }
                    number=0;
                    decimal=0;
                    total=0;
                    NoD=0;
                    d=0;
                }
            }

            if(textChar[i]=='+' || textChar[i] == '-' ){
                if(MobelSeqStack.isEmpty()==false && ( MobelSeqStack.peek().toString().equals("s")|| MobelSeqStack.peek().toString().equals("n") || MobelSeqStack.peek().toString().equals("^") || MobelSeqStack.peek().toString().equals("×") || MobelSeqStack.peek().toString().equals("÷"))){
                    total = number + decimal;
                    NumSeqStack.push(total);
                    decimal=0;
                    number=0;
                    total = 0;
                    NoD=0;
                    d=0;
                    double a=0;
                    double b=0;
                    a=(double)NumSeqStack.pop();
                    b=(double)NumSeqStack.pop();

                    switch (MobelSeqStack.pop().toString()){
                        case "×":
                            NumSeqStack.push(a*b);
                            MobelSeqStack.push(textChar[i]);
                            break;
                        case "÷":
                            NumSeqStack.push(b/a);
                            MobelSeqStack.push(textChar[i]);
                            break;
                        case "^":
                            NumSeqStack.push(Math.pow(b,a));
                            break;
                        case "n":
                            NumSeqStack.push(b*Math.sin(a));
                            break;
                        case "s":
                            NumSeqStack.push(b*Math.cos(a));
                            break;
                            default:
                    }
                    MobelSeqStack.push(textChar[i]);
                }
                else {
                    MobelSeqStack.push(textChar[i]);
                    total = number + decimal;
                    if(total!=0) {
                        NumSeqStack.push(total);
                    }
                    number=0;
                    decimal=0;
                    total=0;
                    NoD=0;
                    d=0;
                }
            }

            else if(textChar[i]==')'){
                total= number + decimal;
                NumSeqStack.push(total);
                number=0;
                decimal=0;
                total=0;
                NoD=0;
                d=0;
                do{
                    double a=0;
                    double b=0;
                    a=(double)NumSeqStack.pop();
                    b=(double)NumSeqStack.pop();
                    switch (MobelSeqStack.pop().toString()){
                        case "×":
                            NumSeqStack.push(a*b);
                            break;
                        case "÷":
                            NumSeqStack.push(b/a);
                            break;
                        case "+":
                            NumSeqStack.push(a+b);
                            break;
                        case "-":
                            NumSeqStack.push(b-a);
                            break;
                        case "^":
                            NumSeqStack.push(Math.pow(b,a));
                            break;
                        case "n":
                            NumSeqStack.push(b*Math.sin(a));
                            break;
                        case "s":
                            NumSeqStack.push(b*Math.cos(a));
                            break;
                        default:
                    }
                }while (MobelSeqStack.peek().toString().equals("(")==false);
                MobelSeqStack.pop();
            }

            else if(textChar[i]=='='){
                total = number + decimal;
                if(total!=0) {
                    NumSeqStack.push(total);
                }
                do {
                    double a=0;
                    double b=0;
                    a=(double)NumSeqStack.pop();
                    b=(double)NumSeqStack.pop();
                    switch (MobelSeqStack.pop().toString()){
                        case "×":
                            NumSeqStack.push(a*b);
                            break;
                        case "÷":
                            NumSeqStack.push(b/a);
                            break;
                        case "+":
                            NumSeqStack.push(a+b);
                            break;
                        case "-":
                            NumSeqStack.push(b-a);
                            break;
                        case "^":
                            NumSeqStack.push(Math.pow(b,a));
                            break;
                        case "n":
                            NumSeqStack.push(b*Math.sin(a));
                            break;
                        case "s":
                            NumSeqStack.push(b*Math.cos(a));
                            break;
                        default:
                    }
                }while (MobelSeqStack.isEmpty()==false);
                textView.setText(NumSeqStack.pop().toString());
            }
        }

    }
 }

