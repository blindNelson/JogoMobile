package com.example.jogomobille.game.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.jogomobille.R;
import com.example.jogomobille.game.gameobject.player.Player;

public class PlayerPosition {
    private final Player player;
    private final Context context;

    public PlayerPosition(Player player, Context context){
        this.player = player;
        this.context = context;
    }

    public void draw(Canvas canvas){
        drawXposition(canvas);
        drawYposition(canvas);
    }

    private void drawXposition(Canvas canvas) {
        String averageUPS = Double.toString(player.getPositionX());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.White);
        paint.setColor(color);
        paint.setTextSize(25);
        canvas.drawText("X: " + Math.ceil(Double.parseDouble(averageUPS)), 200, 50, paint);
    }

    private void drawYposition(Canvas canvas) {
        String averageUPS = Double.toString(player.getPositionY());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.White);
        paint.setColor(color);
        paint.setTextSize(25);
        canvas.drawText("Y: " + Math.ceil(Double.parseDouble(averageUPS)), 200, 100, paint);
    }
}
