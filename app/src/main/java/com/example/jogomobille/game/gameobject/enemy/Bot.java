package com.example.jogomobille.game.gameobject.enemy;

import static com.example.jogomobille.utils.Utils.getDistanceBetweenPoints;

import android.content.Context;
import android.graphics.Canvas;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.gameobject.Circle;
import com.example.jogomobille.game.map.Mechanics.Colision;
import com.example.jogomobille.game.map.TileMap;
import com.example.jogomobille.utils.Coordenada;

public abstract class Bot extends Circle
{
    TileMap    tileMap;
    Coordenada target;

    public Bot(Context context, int color, double positionX, double positionY, double radius, Colision colision, TileMap tileMap ) {
        super(context, color, positionX, positionY, radius, colision);
        target  = new Coordenada(0, 0);
        this.tileMap = tileMap;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        super.draw(canvas, gameDisplay);
    }

    public void update(Coordenada target){

        pathToTarget(target);

        super.update();
    }


    private void   faux(Coordenada target, Coordenada point, Coordenada actual){
        if(tileMap.isColiding(point.getX(), point.getY()))
            return;

        int distance       = (int)getDistanceBetweenPoints(target.getX(), target.getY(), point.getX(), point.getY());
        int actualDistance = (int)getDistanceBetweenPoints(target.getX(), target.getY(), point.getX(), point.getY());

        if(distance<actualDistance)
            actual = point;

        return;
    }

    private Coordenada pathToTarget(Coordenada target){

        Coordenada ret = new Coordenada(0, 0);

        faux(target, new Coordenada((int) this.positionX+32+64, (int) this.positionY),  ret);
        faux(target, new Coordenada((int) this.positionX, (int) this.positionY+32+64),  ret);
        faux(target, new Coordenada((int) this.positionX-32, (int) this.positionY),     ret);
        faux(target, new Coordenada((int) this.positionX, (int) this.positionY-32),     ret);

        return ret;
    }


}