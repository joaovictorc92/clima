package com.example.victor.model;

import java.util.HashMap;

/**
 * Created by Victor on 15/05/2017.
 */

public class Previsao {
    private String data;
    private String tempo;
    private int maxima;
    private int minima;

    HashMap hash;

    public Previsao(){
        hash = new HashMap<String,String>();
        this.preencherMapaCondicao();
    }

    public void preencherMapaCondicao(){
        hash.put("ec","Encoberto com Chuvas Isoladas");
        hash.put("ci","Chuvas Isoladas");
        hash.put("c	","Chuva");
        hash.put("in","Instável");
        hash.put("pp","Poss. de Pancadas de Chuva");
        hash.put("cm","Chuva pela Manhã");
        hash.put("cn","Chuva a Noite");
        hash.put("pt","Pancadas de Chuva a Tarde");
        hash.put("pm","Pancadas de Chuva pela Manhã");
        hash.put("np","Nublado e Pancadas de Chuva");
        hash.put("pc","Pancadas de Chuva");
        hash.put("pn","Parcialmente Nublado");
        hash.put("cv","Chuvisco");
        hash.put("ch","Chuvoso");
        hash.put("t","Tempestade");
        hash.put("ps","Predomínio de Sol");
        hash.put("e","Encoberto ");
        hash.put("n","Nublado");
        hash.put("cl","Céu Claro");
        hash.put("nv","Nevoeiro");
        hash.put("g	","Geada");
        hash.put("ne","Neve");
        hash.put("nd","Não Definido");
        hash.put("pnt","Pancadas de Chuva a Noite");
        hash.put("psc","Possibilidade de Chuva");
        hash.put("pcm","Possibilidade de Chuva pela Manhã ");
        hash.put("pct","Possibilidade de Chuva a Tarde");
        hash.put("pcn","Possibilidade de Chuva a Noite");
        hash.put("npt","Nublado com Pancadas a Tarde");
        hash.put("npn","Nublado com Pancadas a Noite");
        hash.put("ncn","Nublado com Poss. de Chuva a Noite");
        hash.put("nct","Nublado com Poss. de Chuva a Tarde");
        hash.put("ncm","Nubl. c/ Poss. de Chuva pela Manhã");
        hash.put("npm","Nublado com Pancadas pela Manhã");
        hash.put("npp","Nublado com Possibilidade de Chuva");
        hash.put("vn","Variação de Nebulosidade");
        hash.put("ct","Chuva a Tarde");
        hash.put("ppn","Poss. de Panc. de Chuva a Noite");
        hash.put("ppt","Poss. de Panc. de Chuva a Tarde");
        hash.put("ppm","Poss. de Panc. de Chuva pela Manhã");
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTempo() {
        return (String) hash.get(tempo);
    }

    public void setTempo(String tempo) {
        this.tempo = tempo;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }
}
