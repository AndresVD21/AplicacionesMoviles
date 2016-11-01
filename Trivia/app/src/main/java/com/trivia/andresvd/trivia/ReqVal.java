package com.trivia.andresvd.trivia;

/**
 * Created by AndresVD on 28/10/2016.
 */

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.net.PortUnreachableException;

/**
 * Created by SebastianD on 28/10/2016.
 */

public class ReqVal implements ValueEventListener {

    private Object val;
    private boolean a;

    public ReqVal(){
        val = null;
        a=false;
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        val = dataSnapshot.getValue();
        a=true;
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    public Object getVal(){
        return val;
    }




    public boolean isReady(){
        return a;
    }

}
