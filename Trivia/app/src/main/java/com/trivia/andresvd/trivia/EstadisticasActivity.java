package com.trivia.andresvd.trivia;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EstadisticasActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<long[]> stats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        stats = (ArrayList<long[]>) getIntent().getSerializableExtra("Estadisticas");
        for(int i=0;i<4;i++){
            ponerBuenaEnLugar(i,stats.get(i)[0]);
            ponerMalaEnLugar(i,stats.get(i)[1]);
        }
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Game of Thrones.ttf");
        Button b = (Button) findViewById(R.id.volver);
        TextView tx = (TextView)findViewById(R.id.estadisticas);
        b.setTypeface(custom_font);
        tx.setTypeface(custom_font);
        b.setOnClickListener(this);

    }

    public void ponerBuenaEnLugar(int i,long t){
        switch (i){
            case 0:
                TextView b1 = (TextView)findViewById(R.id.b1);
                b1.setText(t+"");
            case 1:
                TextView b2 = (TextView)findViewById(R.id.b2);
                b2.setText(t+"");
            case 2:
                TextView b3 = (TextView)findViewById(R.id.b3);
                b3.setText(t+"");
            case 3:
                TextView b4 = (TextView)findViewById(R.id.b4);
                b4.setText(t+"");
        }
    }

    public void ponerMalaEnLugar(int i,long t){
        switch (i){
            case 0:
                TextView m1 = (TextView)findViewById(R.id.m1);
                m1.setText(t+"");
            case 1:
                TextView m2 = (TextView)findViewById(R.id.m2);
                m2.setText(t+"");
            case 2:
                TextView m3 = (TextView)findViewById(R.id.m3);
                m3.setText(t+"");
            case 3:
                TextView m4 = (TextView)findViewById(R.id.m4);
                m4.setText(t+"");
        }
    }


    @Override
    public void onClick(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
