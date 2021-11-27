package com.example.jogomobille.game.gameobject;

import android.graphics.Canvas;
import android.util.Log;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.map.Mechanics.Colision;
import com.example.jogomobille.game.map.TileMap;

/**
 * GameObject is an abstract class which is the foundation of all world objects in the game.
 */
public abstract class GameObject {

    private final Colision tilemap;
    protected double width;
    protected double height;
    protected double positionX;
    protected double positionY;
    protected double velocityX = 0;
    protected double velocityY = 0;
    protected double directionX = 1;
    protected double directionY = 0;

    public GameObject(double positionX, double positionY, double width, double height, Colision tilemap) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
        this.tilemap = tilemap;
    }

    protected static double getDistanceBetweenObjects(GameObject obj1, GameObject obj2) {
        return Math.sqrt(
            Math.pow(obj2.getPositionX() - obj1.getPositionX(), 2) +
            Math.pow(obj2.getPositionY() - obj1.getPositionY(), 2)
        );
    }

    public abstract void draw(Canvas canvas, GameDisplay gameDisplay);

    public void update(){
        positionX+=tilemap.colisionX(this, (int)velocityX);
        positionY+=tilemap.colisionY(this, (int)velocityY);
    }

    protected void updateVelocity(int vX, int vY){
        velocityX = tilemap.colisionX(this, vX);
        velocityY = tilemap.colisionY(this, vY);

        Log.d("GameObject.java", "update():{\n" +
                "   positionX="+positionX+";velocityX="+velocityX+";\n" +
                "   positionY="+positionY+";velocityY="+velocityY+";\n" +
                "}");

        positionX+=velocityX;
        positionY+=velocityY;
    }

    public double getPositionX() { return positionX; }

    public double getPositionY() {
        return positionY;
    }

    protected double getDirectionX() {
        return directionX;
    }

    protected double getDirectionY() {
        return directionY;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

}
