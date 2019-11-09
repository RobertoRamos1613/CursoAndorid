package com.example.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public EditText num1,num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText)findViewById(R.id.Num1);
        num2 = (EditText)findViewById(R.id.Num2);
    }
    public void Sumas(View v){
        int n1 = Integer.parseInt(num1.getText().toString());
        int n2 = Integer.parseInt(num2.getText().toString());
        int suma = n1 + n2;

        mostrar(suma);
    }

    public void Restas(View v){
        int n1 = Integer.parseInt(num1.getText().toString());
        int n2 = Integer.parseInt(num2.getText().toString());
        int resta = n1 - n2;

        mostrar(resta);
    }

    public void Divisiones(View v){
        int n1 = Integer.parseInt(num1.getText().toString());
        int n2 = Integer.parseInt(num2.getText().toString());
        int division = n1 / n2;

        mostrar(division);
    }

    public void Multiplicaciones(View v){
        int n1 = Integer.parseInt(num1.getText().toString());
        int n2 = Integer.parseInt(num2.getText().toString());
        int mult = n1 * n2;

        mostrar(mult);
    }

    private void mostrar(int res){
        Toast.makeText(this, "El resultado es: " + res, Toast.LENGTH_SHORT).show();
    }
}
