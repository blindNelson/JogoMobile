package com.example.jogomobille.game;

import android.graphics.Rect;

import com.example.jogomobille.game.gameobject.GameObject;

public class GameDisplay {
    public final Rect DISPLAY_RECT;
    private final int heightPixels;
    private final int widthPixels;
    private double gameToDisplayCoordinatesOffsetX;
    private double gameToDisplayCoordinatesOffsetY;
    private double displayCenterX;
    private double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;
    private GameObject centerObject;


    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject) {

        this.centerObject = centerObject;

        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;

        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;

        DISPLAY_RECT = new Rect(0, 0, widthPixels, heightPixels);
    }

    public void update() {
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();

        gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY;
    }
    

    public double gameToDisplayCoordinatesX(double x) {
        return x + gameToDisplayCoordinatesOffsetX;
    }

    public double gameToDisplayCoordinatesY(double y) {
        return y + gameToDisplayCoordinatesOffsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) (gameCenterX - widthPixels/2)+(int)(centerObject.getWidth()/2),
                (int) (gameCenterY - heightPixels/2)+(int)(centerObject.getHeight()/2),
                (int) (gameCenterX + widthPixels/2)+(int)(centerObject.getWidth()/2),
                (int) (gameCenterY + heightPixels/2)+(int)(centerObject.getHeight()/2)
        );
    }
}
