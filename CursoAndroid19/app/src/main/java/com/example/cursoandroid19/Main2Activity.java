package com.example.cursoandroid19;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {


    String valorExtraido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView nombre = (TextView) findViewById(R.id.textView2);
        valorExtraido = getIntent().getExtras().getString("Valor");
        nombre.setText(valorExtraido);
    }
}