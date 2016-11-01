package com.trivia.andresvd.trivia;

import java.io.Serializable;

/**
 * Created by AndresVD on 27/10/2016.
 */

public class Pregunta implements Serializable{

    private String pregunta;
    private String correcta;
    private String[] seleccionadas;

    public Pregunta(String p,String c,String[] s){
        pregunta=p;
        correcta=c;
        seleccionadas=s;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }

    public String[] getSeleccionadas() {
        return seleccionadas;
    }

    public void setSeleccionadas(String[] seleccionadas) {
        this.seleccionadas = seleccionadas;
    }
}
