package com.example.youzhedou.orderfood;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button buttonSignIn, buttonSignUp;
    TextView textSlogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSignIn = (Button) findViewById(R.id.buttonSignIn);
        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        textSlogan = (TextView) findViewById(R.id.textSlogan);

//        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Lobster-Regular.TTF");
//        textSlogan.setTypeface(tf);

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singUp = new Intent(MainActivity.this, SignUp.class);
                startActivity(singUp);
            }
        });



        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent singIn = new Intent(MainActivity.this, SignIn.class);
                startActivity(singIn);
            }
        });

    }
}
