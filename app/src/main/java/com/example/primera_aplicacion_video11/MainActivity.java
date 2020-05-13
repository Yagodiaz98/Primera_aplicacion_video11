package com.example.primera_aplicacion_video11;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    public int contador;
    TextView textoResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textoResultado=(TextView)findViewById(R.id.contadorPulsaciones);
        contador=0;

        EventoTeclado teclado = new EventoTeclado();//Creamos objeto
        EditText numero_reseteo = (EditText) findViewById(R.id.numeroReseteo);
        numero_reseteo.setOnEditorActionListener(teclado);//Ponemos el EditText a la escucha de teclado, que era un objeto con la clase interior


    }

    public void incrementarContador(View vista){
        contador ++;
        textoResultado.setText(""+contador);
        //mostrarResultado();
    }

    public void resetearContador(View vista){
        EditText n_reseteo=(EditText)findViewById(R.id.numeroReseteo);
        contador=Integer.parseInt(n_reseteo.getText().toString());
        n_reseteo.setText("");
        textoResultado.setText(""+contador);
        //contador = 0;
        //mostrarResultado();
        InputMethodManager miTeclado = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);//ocultamos el teclado al resetear (EVENTO)
        miTeclado.hideSoftInputFromWindow(n_reseteo.getWindowToken(),0);
    }

    public void decrementarContador(View vista){
        contador--;
        if(contador<0){
            CheckBox negativos = (CheckBox)findViewById(R.id.checkBox);
            if(!negativos.isChecked()){
                contador=0;
            }
        }
        textoResultado.setText(""+contador);
        //mostrarResultado();
    }

    class EventoTeclado implements TextView.OnEditorActionListener{//Al presionar enter se ejecutara la aplicación. Esta clase siempre tiene que ser interna

        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {//Quiere decir que si se cierra el teclado se ejecutara el botón
                resetearContador(null);//null porque no necesitamos pasar un argumento
            }
            return false;
        }
    }



    /*public void mostrarResultado(){
        TextView textoResultado=(TextView)findViewById(R.id.contadorPulsaciones);

        textoResultado.setText("" + contador);
    }*/



}
