package com.example.victor.service;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.victor.model.Previsao;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Victor on 03/05/2017.
 */

public class HttpRequestInpe extends AsyncTask<String,Void, String>{
    TextView textView;

    public void setTextView(TextView textView){
        this.textView =  textView;
    }

    private static String readStream(InputStream stream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder result = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null){
            result.append(line);
        }
        //reader.read
        return result.toString();
    }

    private void parsePrevisaoXml(String xmlInpe) throws IOException {
        Previsao previsao = null;
        Collection listaPrevisao = null;
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            xmlPullParserFactory.setNamespaceAware(true);
            XmlPullParser xpp = xmlPullParserFactory.newPullParser();
            xpp.setInput(new StringReader(xmlInpe));

            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){
                String name;
                if(eventType == XmlPullParser.END_DOCUMENT)
                    listaPrevisao = new ArrayList();
                if(eventType == XmlPullParser.START_TAG){
                    name = xpp.getName();
                    if(name.equalsIgnoreCase("previsao")){
                        previsao =  new Previsao();
                    }else if(previsao != null){
                        if(name.equalsIgnoreCase("dia"))
                            previsao.setData(xpp.nextText());
                        else if (name.equalsIgnoreCase("tempo"))
                            previsao.setTempo(xpp.nextText());
                        else if (name.equalsIgnoreCase("maxima"))
                            previsao.setMaxima(Integer.parseInt(xpp.nextText()));
                        else if (name.equalsIgnoreCase("minima"))
                            previsao.setMinima(Integer.parseInt(xpp.nextText()));

                    }
                }
                if(eventType == XmlPullParser.END_TAG) {
                    name = xpp.getName();
                    if(name.equalsIgnoreCase("previsao") && previsao != null)
                        listaPrevisao.add(previsao);
                }
                eventType = xpp.next();
            }

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        String codigoCidade = params[0];
        InputStream stream=null;
        HttpURLConnection httpURLConnection =null;
        String result = null;

        try {
            URL url = new URL("http://servicos.cptec.inpe.br/XML/cidade/" + codigoCidade + "/previsao.xml");
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            stream = httpURLConnection.getInputStream();
            if (stream != null) {
                // Converts Stream to String with max length of 500.
                result = readStream(stream);
            }
        }catch(Exception e){
            e.printStackTrace();
        } finally{
            // Close Stream and disconnect HTTPS connection.
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
        return result;
    }

    @Override
    protected void onPostExecute(String param){
        textView.setText(param);
    }


}
