package com.trivia.andresvd.trivia;

import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by AndresVD on 27/10/2016.
 */

public class ChargeAsyn extends AsyncTask<Object,Object,Object> {

    public static final int MAX_PREGUNTAS=4;

    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    private ArrayList<Pregunta> aux;
    private ChargingActivity chargingActivity;
    public ChargeAsyn(ChargingActivity act){
        Log.d("LO CREO","CREO");
        aux = new ArrayList<Pregunta>();
        chargingActivity = act;

    }

    @Override
    protected Object doInBackground(Object... objects) {
        for (int i=1;i<=MAX_PREGUNTAS;i++){
            Log.d("ENTRO AL BACK","ENTRO");
            obtenerQuestionario(i);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        chargingActivity.obtenerDatos(aux);
        chargingActivity.cambiarAPreguntas();
    }

    private void obtenerQuestionario(int i) {
        Log.d("OBTENIENDO Q ",i+"");
        ReqVal reqVal = new ReqVal();
        mDatabase.child("Preguntas").child("P"+i).addListenerForSingleValueEvent(reqVal);
        while(!reqVal.isReady()){

        }
        String pre = (String) reqVal.getVal();
        Log.d("PREGUNTA",pre);
        reqVal=new ReqVal();
        mDatabase.child("Respuestas").child("P"+i).child("Correcta").addListenerForSingleValueEvent(reqVal);
        while (!reqVal.isReady()){

        }
        String corr = (String) reqVal.getVal();
        Log.d("CORRECTA",corr);
        reqVal=new ReqVal();
        mDatabase.child("Respuestas").child("P"+i).child("Incorrectas").addListenerForSingleValueEvent(reqVal);
        while (!reqVal.isReady()){

        }
        String incorrec = (String) reqVal.getVal();
        Log.d("INCORRECTAS",incorrec);
        String[] incorrectas = incorrec.split(",");
        String[] seleccionadas = new String[2];
        ArrayList<String> listIncorrectas = convertirArray(incorrectas);
        for(int j=0;j<seleccionadas.length;j++){
            int aleatorio = (int) Math.floor(Math.random()*listIncorrectas.size());
            String rIncorrecta = listIncorrectas.get(aleatorio);
            seleccionadas[j]=rIncorrecta;
            listIncorrectas.remove(aleatorio);
        }

        ArrayList<Object> ayuda = new ArrayList<Object>();

        Pregunta n = new Pregunta(pre,corr,seleccionadas);
        aux.add(n);


    }

    public ArrayList<String> convertirArray(String[] arr){
        ArrayList<String> s = new ArrayList<String>();
        for(int i=0;i<arr.length;i++){
            s.add(arr[i]);
        }
        return s;
    }


}
