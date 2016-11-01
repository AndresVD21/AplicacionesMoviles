package com.trivia.andresvd.trivia;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChargingActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private ArrayList<Pregunta> datos;
    private ChargeAsyn asyn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Game of Thrones.ttf");
        TextView tx = (TextView)findViewById(R.id.charging1);
        tx.setTypeface(custom_font);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        asyn=new ChargeAsyn(this);
        asyn.execute();

    }

    public void obtenerDatos(ArrayList<Pregunta> a){
        datos=a;
    }

    public void cambiarAPreguntas(){
        Intent i = new Intent(this,PreguntaActivity.class);
        i.putExtra("Questionario",datos);
        startActivity(i);
        finish();
    }
}