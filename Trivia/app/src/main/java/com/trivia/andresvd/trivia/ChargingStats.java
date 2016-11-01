package com.trivia.andresvd.trivia;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ChargingStats extends AppCompatActivity {

    private StatisticsAsyn statis;
    private ArrayList<long[]> stats;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charging_stats);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Game of Thrones.ttf");
        TextView tx = (TextView)findViewById(R.id.charging2);
        tx.setTypeface(custom_font);
        stats = new ArrayList<long[]>();
        statis = new StatisticsAsyn(this);
        statis.execute();
    }

    public void ponerStats(ArrayList<long[]> a){
        stats=a;
    }


    public void cambiarEstadisticas(){
        Intent i = new Intent(this,EstadisticasActivity.class);
        i.putExtra("Estadisticas",stats);
        startActivity(i);
        finish();
    }
}
