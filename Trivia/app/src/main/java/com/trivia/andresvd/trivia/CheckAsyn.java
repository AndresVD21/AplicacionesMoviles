package com.trivia.andresvd.trivia;

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by AndresVD on 28/10/2016.
 */

public class CheckAsyn extends AsyncTask<Object,Object,Object> {

    private String valor;
    private int pre;
    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

    public CheckAsyn(String v, int d){
        valor=v;
        pre=d;
    }
    @Override
    protected Object doInBackground(Object... objects) {
        if(valor.equals("CORRECTA")){
            mDatabase.child("Partidas").child("P" + pre).child("Buenas").addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    long num = (long) dataSnapshot.getValue();
                    num = num+1;
                    mDatabase.child("Partidas").child("P" + pre).child("Buenas").setValue(num);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }else{
            Log.d("FUE INCORRECTA","FUE INCORRECTA");
            mDatabase.child("Partidas").child("P" + pre).child("Malas").addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    long num = (long) dataSnapshot.getValue();
                    num = num+1;
                    mDatabase.child("Partidas").child("P" + pre).child("Malas").setValue(num);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }
        return null;
    }
}