package com.example.jogomobille.game.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.jogomobille.game.Gameloop;
import com.example.jogomobille.R;

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
        paint.setTextSize(25);
        canvas.drawText("UPS: " + Math.ceil(Double.parseDouble(averageUPS)), 50, 50, paint);
    }

    public void drawFPS(Canvas canvas) {
        String averageFPS = Double.toString(gameLoop.getAverageFPS());
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.White);
        paint.setColor(color);
        paint.setTextSize(25);
        canvas.drawText("FPS: " + Math.ceil(Double.parseDouble(averageFPS)), 50, 100, paint);
    }
}