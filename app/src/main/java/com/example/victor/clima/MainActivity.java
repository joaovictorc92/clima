package com.example.victor.clima;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    public final static String EXTRA_MESSAGE = "com.example.victor.clima.MESSAGE";
    Spinner spinnerCities;
    HashMap cityMap;

    private void loadCities(){
        cityMap = new HashMap();
        cityMap.put(243,"São Luís");
        cityMap.put(244,"São Paulo");
        String[] arrayCities = (String[]) cityMap.values().toArray(new String[ cityMap.values().size()]);
        ArrayAdapter<String> arrayAdapterCities = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, arrayCities);
        arrayAdapterCities.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCities = (Spinner) findViewById(R.id.cities_spinner);
        spinnerCities.setAdapter(arrayAdapterCities);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadCities();
    }

    private Object getKey(HashMap map, String value){
        for( Object key : map.keySet()){
            if(map.get(key).equals(value)){
                return key;
            }
        }
        return -1;
    }

    public void enviarMensagem(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        Bundle b = new Bundle();
        spinnerCities = (Spinner) findViewById(R.id.cities_spinner);
        Integer idcidade = (Integer) getKey(cityMap,spinnerCities.getSelectedItem().toString());
        b.putInt("idcidade",idcidade);
        intent.putExtra(EXTRA_MESSAGE, b);
        startActivity(intent);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
