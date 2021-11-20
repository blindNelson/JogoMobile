package com.example.jogomobille.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setIdNome(int idUsuario, String nomeUsuario) {
        prefs.edit().putInt("idUsuario", idUsuario).commit();
        prefs.edit().putString("nomeUsuario", nomeUsuario).commit();
    }

    public String getNomeUsuario() {
        return prefs.getString("nomeUsuario","");
    }

    public int getIdUsuario() {
        return prefs.getInt("idUsuario",0);
    }

}