package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    TextView subText, mainText;
    Button ce, c, bs, divide, multiply, add, minus, inverse, equals, dot;
    Button seven, eight, nine, one, two, three, four, five, six;
    String dis = "";
    Boolean positive = true;
    Stack<String> stack = new Stack<>();

    private float add(float a, float b){
        return a + b;
    }

    private float minus(float a, float b){
        return a - b;
    }

    private float divide(float a, float b){
        return a / b;
    }

    private float multiply(float a, float b){
        return a * b;
    }

    private String beauty(){
        String result = "";
        String[] value = dis.split("\\.");
        String head = value[0];
        int length = head.length();

        while(length > 3){
            result = "," + head.substring(length - 3, length) + result;
            length = length - 3;
        }
        result = head.substring(0, length) + result;

        if(!positive){
            result = "-" + result;
        }

        if(value.length > 1){
            result += "." + value[1];
        }else if(dis.indexOf('.') != -1){
            result += ".";
        }

        return result;
    }

    private void display2ndTextView(){
        String s = "";
        Object[] stackArr = stack.toArray();
        for(Object obj : stackArr){
            s += obj.toString() + " ";
        }
        subText.setText(s);
    }

    private void push(String c){
        if(!stack.empty()){
            String head = stack.pop();
            float value1 = Float.parseFloat(stack.pop());
            float value2;
            if(!dis.equals("")){
                if(positive){
                    value2 = Float.parseFloat(dis);
                }else{
                    value2 = Float.parseFloat("-" + dis);
                }
            }else{
                return;
            }

            switch (head){
                case "+":
                    stack.push(String.valueOf(value1 + value2));
                    break;
                case "-":
                    stack.push(String.valueOf(value1 - value2));
                    break;
                case "*":
                    stack.push(String.valueOf(value1 * value2));
                    break;
                case "/":
                    stack.push(String.valueOf(value1 / value2));
                    break;
                default:
                    break;
            }
            stack.push(c);
            dis = "";
            positive = true;
        }else{
            if(!dis.equals("")){
                if(positive){
                    stack.push(dis);
                }else{
                    stack.push("-" + dis);
                }
                stack.push(c);
                dis = "";
                positive = true;
            }
        }
    }

    private void equal(){
        if(!stack.empty()){
            String head = stack.pop();
            float value1 = Float.parseFloat(stack.pop());
            float value2;
            if(!dis.equals("")){
                if(positive){
                    value2 = Float.parseFloat(dis);
                }else{
                    value2 = Float.parseFloat("-" + dis);
                }
            }else{
                return;
            }

            switch (head){
                case "+":
                    dis = String.valueOf(value1 + value2);
                    break;
                case "-":
                    dis = String.valueOf(value1 - value2);
                    break;
                case "*":
                    dis = String.valueOf(value1 * value2);
                    break;
                case "/":
                    dis = String.valueOf(value1 / value2);
                    break;
                default:
                    break;
            }
            if(dis.charAt(0) == '-'){
                dis = dis.substring(1, dis.length());
                positive = false;
            }else{
                positive = true;
            }
            stack.clear();
            display2ndTextView();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subText = (TextView) findViewById(R.id.text1);
        mainText = (TextView) findViewById(R.id.text2);
        ce = (Button) findViewById(R.id.ce);
        c = (Button) findViewById(R.id.c);
        bs = (Button) findViewById(R.id.bs);
        divide = (Button) findViewById(R.id.divide);
        multiply = (Button) findViewById(R.id.multiply);
        add = (Button) findViewById(R.id.add);
        minus = (Button) findViewById(R.id.minus);
        inverse = (Button) findViewById(R.id.inverse);
        dot = (Button) findViewById(R.id.dot);
        equals = (Button) findViewById(R.id.equals);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis += "1";
                mainText.setText(beauty());
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis += "2";
                mainText.setText(beauty());
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis += "3";
                mainText.setText(beauty());
            }
        });
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis += "4";
                mainText.setText(beauty());
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis += "5";
                mainText.setText(beauty());
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis += "6";
                mainText.setText(beauty());
            }
        });
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis += "7";
                mainText.setText(beauty());
            }
        });
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis += "8";
                mainText.setText(beauty());
            }
        });
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis += "9";
                mainText.setText(beauty());
            }
        });
        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dis.indexOf('.') == -1) {
                    dis += ".";
                    mainText.setText(beauty());
                }
            }
        });
        bs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dis.length() > 0) {
                    dis = dis.substring(0, dis.length() - 1);
                }else{
                    positive = true;
                }
                mainText.setText(beauty());
            }
        });
        inverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(positive){
                    positive = false;
                }else
                    positive = true;

                mainText.setText(beauty());
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push("+");
                display2ndTextView();
                mainText.setText(beauty());
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push("-");
                display2ndTextView();
                mainText.setText(beauty());
            }
        });
        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push("*");
                display2ndTextView();
                mainText.setText(beauty());
            }
        });
        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                push("/");
                display2ndTextView();
                mainText.setText(beauty());
            }
        });
        equals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                equal();
                mainText.setText(beauty());
            }
        });
        ce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis = "";
                positive = true;
                stack.clear();
                display2ndTextView();
                mainText.setText(beauty());
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dis = "";
                positive = true;
                mainText.setText(beauty());
            }
        });






    }
}