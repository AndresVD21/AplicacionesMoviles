package com.trivia.andresvd.trivia;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by AndresVD on 29/10/2016.
 */

public class StatisticsAsyn extends AsyncTask<Object,Object,Object> {

    public static final int MAX_PREGUNTAS=4;


    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private ArrayList<long[]> statistics;
    private ChargingStats esAct;

    public StatisticsAsyn(ChargingStats e){
        esAct=e;
        statistics=new ArrayList<long[]>();
    }

    @Override
    protected Object doInBackground(Object... objects) {
        for(int i=1;i<=MAX_PREGUNTAS;i++){
            ponerEstadisticas(i);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        esAct.ponerStats(statistics);
        esAct.cambiarEstadisticas();

    }

    public void ponerEstadisticas(int i){
        ReqVal reqVal = new ReqVal();
        mDatabase.child("Partidas").child("P" + i).child("Buenas").addListenerForSingleValueEvent(reqVal);
        while(!reqVal.isReady()){

        }
        long buenas = (long)reqVal.getVal();
        //esAct.ponerBuenaEnLugar(i,buenas);

        reqVal=new ReqVal();
        mDatabase.child("Partidas").child("P" + i).child("Malas").addListenerForSingleValueEvent(reqVal);
        while(!reqVal.isReady()){

        }
        long malas = (long)reqVal.getVal();
        Log.d("MALAS P"+i,"PREGUNTA "+i);

        //esAct.ponerMalaEnLugar(i,malas);
        long[] stats={buenas,malas};
        statistics.add(stats);

    }
}
