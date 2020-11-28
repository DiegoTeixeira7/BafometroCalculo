package com.example.diegoteixeira.bafmetrocalculo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void calcular(View view) {
        try {
            Intent it = getIntent();

            double peso = it.getDoubleExtra("peso", 1);
            int nCopos = it.getIntExtra("nCopos", 0);
            String sexo = it.getStringExtra("sexo");
            String isJejum = it.getStringExtra("isJejum");

            double taxaAlcoolemia = (nCopos * 4.8) / (peso * coeficiente(sexo, isJejum));
            String classificacao = classificacao(taxaAlcoolemia);

            Intent it2 = new Intent(this, MainActivity.class);

            it2.putExtra("taxaAlcoolemia", taxaAlcoolemia);
            it2.putExtra("classificacao", classificacao);

            setResult(10, it2);
            finish();
        } catch (Exception e) {
            Toast.makeText(this, "Para um perfeito funcionamento desse app, envie os dados primeiro através do outro app" , Toast.LENGTH_LONG).show();
        }
    }

    private double coeficiente(String sexo, String isJejum) {
        if(sexo.equals("m") && isJejum.equals("s")) {
            return 0.7;
        } else if(sexo.equals("f") && isJejum.equals("s")) {
            return 0.6;
        } else {
            return 1.1;
        }
    }

    private String classificacao(double taxaAlcoolemia) {
        if(taxaAlcoolemia > 0.2) {
            return "Pessoa Alcoolizada";
        } else {
            return "Pessoa NÃO Alcoolizada";
        }
    }
}