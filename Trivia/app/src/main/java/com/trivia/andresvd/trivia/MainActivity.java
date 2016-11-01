package com.trivia.andresvd.trivia;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = (Button)findViewById(R.id.comenzar);
        b.setOnClickListener(this);
        TextView tx = (TextView)findViewById(R.id.trivia);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Game of Thrones.ttf");
        tx.setTypeface(custom_font);
        b.setTypeface(custom_font);
    }

    private void lanzarPreguntas(){
        Intent i = new Intent(this,ChargingActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        lanzarPreguntas();
    }
}
