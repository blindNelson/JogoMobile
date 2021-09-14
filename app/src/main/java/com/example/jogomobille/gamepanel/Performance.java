package com.example.jogomobille.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.jogomobille.Gameloop;
import com.example.jogomobille.R;
import com.example.jogomobille.gameobject.Player;

public class Performance {
    private Gameloop gameLoop;
    private Context context;

    public Performance(Gameloop gameLoop, Context context) {
        this.gameLoop = gameLoop;
        this.context = context;
    }

    public void draw(Canvas canvas) {
        drawUPS(canvas);
        drawFPS(canvas);
    }

    public void drawUPS(Canvas canvas) {
        String averageUPS = Double.toString(gameLoop.getAverageUPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.White);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS: " + Math.ceil(Double.parseDouble(averageUPS)), 100, 100, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.White);
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS: " + Math.ceil(Double.parseDouble(averageFPS)), 100, 200, paint);
    }
}