package com.example.jogomobille.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class LevelDifficulty {

    private SharedPreferences prefs;

    public LevelDifficulty(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setLevel(int level) {
        prefs.edit().putInt("level", level).commit();
    }

    public void setDifficulty(int difficulty) {
        prefs.edit().putInt("difficulty", difficulty).commit();
    }

    public int getLevel() {
        return prefs.getInt("level",0);
    }

    public int getDifficulty() {
        return prefs.getInt("difficulty",0);
    }

}