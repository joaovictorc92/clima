package com.example.victor.clima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.victor.service.HttpRequestInpe;

import java.io.IOException;

public class DisplayMessageActivity extends AppCompatActivity {
    private TextView tvNome, tvIdade;
    public void criarObjetos(){
        tvNome = (TextView) findViewById(R.id.tvNome);
        tvIdade = (TextView) findViewById(R.id.tvIdade);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(MainActivity.EXTRA_MESSAGE);
        criarObjetos();
        Integer cidade = bundle.getInt("idcidade");
        //tvIdade.setTextSize(40);
  
        HttpRequestInpe httpRequestInpe = new HttpRequestInpe();
        httpRequestInpe.setTextView(tvIdade);
        httpRequestInpe.execute(cidade.toString());

        //ViewGroup layout = (ViewGroup) findViewById(R.id.activity_display_message);
        //layout.addView(textView);
    }
}
