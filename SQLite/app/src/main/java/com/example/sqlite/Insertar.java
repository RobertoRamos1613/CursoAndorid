package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insertar extends AppCompatActivity {

    BasedeDatos myBD;
    EditText txtNombre, txtApellido, txtContraseña;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        myBD = new BasedeDatos(this);
        txtNombre = (EditText) findViewById(R.id.idNombre);
        txtApellido = (EditText) findViewById(R.id.idApellido);
        txtContraseña = (EditText) findViewById(R.id.idContraseña);
        btnClick = (Button) findViewById(R.id.idBtn);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = txtNombre.getText().toString();
                String surname = txtApellido.getText().toString();
                String marks = txtContraseña.getText().toString();
                Boolean result = myBD.insertData(name, surname, marks);
                if(result == true){
                    Toast.makeText(getApplicationContext(), "Data Inserted Successfull", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getApplicationContext(), "Data Insertion Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
