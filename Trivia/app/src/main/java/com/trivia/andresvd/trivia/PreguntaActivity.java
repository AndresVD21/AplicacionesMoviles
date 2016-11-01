package com.trivia.andresvd.trivia;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreguntaActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int MAX_PREGUNTAS=4;



    private CheckAsyn check;
    private ArrayList<Pregunta> datos;
    private int pregunta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        datos = (ArrayList<Pregunta>) getIntent().getSerializableExtra("Questionario");
        setContentView(R.layout.activity_pregunta);
        Button b = (Button) findViewById(R.id.ingresar);
        b.setOnClickListener(this);



        pregunta =0;
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Game of Thrones.ttf");
        b.setTypeface(custom_font);
        TextView t = (TextView)findViewById(R.id.pregunta);
        t.setText(datos.get(0).getPregunta());
        RadioButton b1 = (RadioButton)findViewById(R.id.op1);
        b1.setText(datos.get(0).getCorrecta());
        RadioButton b2 = (RadioButton)findViewById(R.id.op2);
        b2.setText(datos.get(0).getSeleccionadas()[0]);
        RadioButton b3 = (RadioButton)findViewById(R.id.op3);
        b3.setText(datos.get(0).getSeleccionadas()[1]);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View view) {
        String valor="";
        int dato = pregunta+1;
        RadioGroup radio = (RadioGroup)findViewById(R.id.Opciones);
        int choosenOne = radio.getCheckedRadioButtonId();
        RadioButton but = (RadioButton)findViewById(choosenOne);
        String respuesta = (String) but.getText();
        Log.d("VALOR PREGUNTA",pregunta+"");
        if(pregunta<MAX_PREGUNTAS){
            if(respuesta.equals(datos.get(pregunta).getCorrecta())){
                valor="CORRECTA";
                Context context = getApplicationContext();
                CharSequence text = "Tu respuesta fue correcta!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                check=new CheckAsyn(valor,dato);
                check.execute();
            }else{
                valor="INCORRECTA";
                Context context = getApplicationContext();
                CharSequence text = "Tu respuesta fue incorrecta, la respuesta es: "+datos.get(pregunta).getCorrecta();
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                check=new CheckAsyn(valor,dato);
                check.execute();

            }
            pregunta++;
            if (pregunta==MAX_PREGUNTAS){
                cambiarACharEstadisticas();
            }else{
                RadioGroup ra = (RadioGroup)findViewById(R.id.Opciones);
                ra.clearCheck();
                ponerPregunta(pregunta);
            }

        }else{
            Intent i = new Intent(this,ChargingStats.class);
            startActivity(i);
            finish();
        }


    }

    public void cambiarACharEstadisticas(){
        Intent i = new Intent(this,ChargingStats.class);
        startActivity(i);
        finish();
    }

    public void ponerPregunta(int pregunta){
        TextView t = (TextView)findViewById(R.id.pregunta);
        t.setText(datos.get(pregunta).getPregunta());
        RadioButton b1 = (RadioButton)findViewById(R.id.op1);
        b1.setText(datos.get(pregunta).getCorrecta());
        RadioButton b2 = (RadioButton)findViewById(R.id.op2);
        b2.setText(datos.get(pregunta).getSeleccionadas()[0]);
        RadioButton b3 = (RadioButton)findViewById(R.id.op3);
        b3.setText(datos.get(pregunta).getSeleccionadas()[1]);
    }








}
