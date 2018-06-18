package net.azurewebsites.luiscontrerasdev.edadcalculator;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Typeface forte;
    private Button btnCalcular;
    private EditText ingresarFecha;
    private TextView txtIngreseFecha;
    private TextView ultimoCalculo;
    private TextView diferenciaEdad;
    private int edad;
    private int size;
    private int anio;
    private int mes;
    private int dia;
    private Calculo calculo;
    private Calendar calendar;
    List<String> historial = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dia = 23;
        mes = 4;
        anio = 1999;
        edad = 0;
        size = 0;

        txtIngreseFecha = (TextView) findViewById(R.id.ingresar_fecha);
        btnCalcular = (Button) findViewById(R.id.btn_calcular);
        ingresarFecha = (EditText) findViewById(R.id.edit_fecha);
        ultimoCalculo = (TextView) findViewById(R.id.ultimo_calculo);
        diferenciaEdad = (TextView) findViewById(R.id.diferencia_edad);

        String fuente = "fuentes/forte.ttf";
        this.forte = Typeface.createFromAsset(getAssets(), fuente);

        txtIngreseFecha.setTypeface(forte);
        btnCalcular.setTypeface(forte);
        ingresarFecha.setTypeface(forte);
        ultimoCalculo.setTypeface(forte);
        diferenciaEdad.setTypeface(forte);
        ingresarFecha.setFocusable(false);

        btnCalcular.setOnClickListener(this);
        ingresarFecha.setOnClickListener(this);

        calendar = Calendar.getInstance();
        calculo = new Calculo();
    }


    @Override
    public void onClick(View v) {

        if (v == ingresarFecha) {
            anio = calendar.get(Calendar.YEAR);
            dia = calendar.get(Calendar.DAY_OF_MONTH);
            mes = calendar.get(Calendar.MONTH) + 1;

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    anio = year;
                    mes = month;
                    dia = dayOfMonth;
                    ingresarFecha.setText(dayOfMonth + "/" + (month + 1) + "/" + (year));
                }

            }, dia, mes, anio);

            datePickerDialog.show();
        }

        size = historial.size();

        if (v == btnCalcular) {

                calculo.setUserMoth(mes);
                calculo.setUserDay(dia);
                calculo.setUserYear(anio);
                edad = calculo.Calculo();

                if (edad < 0) {
                    mostrarMensaje("Advertencia", "La fecha insertada es mayor que la fecha actual");

                } else if (edad == 0) {
                    mostrarMensaje("Advertencia", "Aun no tiene un año de edad");

                } else{
                    mostrarMensaje("Calcula tu edad","Tu edad es: "+ edad);
                    historial.add(String.valueOf(edad));
                    DifereciaEdad();
                }
            }
        }

    public void mostrarMensaje(String titulo , String mensaje){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher).setTitle(titulo).setMessage(mensaje).setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, "Aceptado", Toast.LENGTH_LONG).show();
            }
        });

        builder.show();
    }


    public void DifereciaEdad() {

        if (historial.size() == 1) {

            String edadActual = historial.get(historial.size() - 1);
            ultimoCalculo.setText(getString(R.string.ultimo_calculo, edadActual));

        } else {

            int edadActual = Integer.parseInt(historial.get(historial.size() - 1));
            int ultimaEdad = Integer.parseInt(historial.get(historial.size() - 2));

            ultimoCalculo.setText(getString(R.string.ultimo_calculo, String.valueOf(edadActual)));

            if (edadActual == ultimaEdad) {

                String nDiferencia = getString(R.string.no_diferencias);
                diferenciaEdad.setText(getString(R.string.diferencia_edad, nDiferencia));

            } else {

                String difEdad = String.valueOf(Math.abs(edadActual - ultimaEdad));
                diferenciaEdad.setText(getString(R.string.diferencia_edad, difEdad));
            }
        }
    }
}



