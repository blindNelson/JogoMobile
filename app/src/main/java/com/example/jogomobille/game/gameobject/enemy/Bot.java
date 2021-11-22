package com.example.jogomobille.game.gameobject.enemy;

import static com.example.jogomobille.game.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.jogomobille.game.map.MapLayout.TILE_WIDTH_PIXELS;
import static com.example.jogomobille.utils.Utils.getDistanceBetweenPoints;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.Gameloop;
import com.example.jogomobille.game.gameobject.Circle;
import com.example.jogomobille.game.map.TileMap;
import com.example.jogomobille.utils.Coordenada;

public abstract class Bot extends Circle
{

    private final double MAX_SPEED;

    public Bot(Context context, int color, double positionX, double positionY, double radius, TileMap tileMap, int speed ) {
        super(context, color, positionX, positionY, radius, tileMap);
        this.MAX_SPEED = speed / Gameloop.MAX_UPS;
        this.tileMap = tileMap;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        super.draw(canvas, gameDisplay);
    }

    public void update(Coordenada target){

        target = pathToTarget(target);

        Log.d("Bot.java", "update(): Tx="+target.getX()+", Ty="+target.getY()+";");

        int distance = (int)getDistanceBetweenPoints(target.getX(), target.getY(), positionX, positionY);

        velocityX = ((target.getX()-positionX)/distance)*MAX_SPEED;
        velocityY = ((target.getY()-positionY)/distance)*MAX_SPEED;

        super.update();
    }


    private void   faux(Coordenada target, Coordenada point, Coordenada actual){
        try {
            if (tileMap.isPixelColiding(point.getX(), point.getY()))
                return;
        }
        catch(ArrayIndexOutOfBoundsException e){
            return;
        }
        int distance       = (int)getDistanceBetweenPoints(target.getX(), target.getY(), point.getX(), point.getY());
        int actualDistance = (int)getDistanceBetweenPoints(target.getX(), target.getY(), actual.getX(), actual.getY());

        Log.d("Bot.java", "faux(): Px="+target.getX()+", Py="+target.getY()+";distance="+distance+", actualDistance"+actualDistance+";");

        if(distance<actualDistance) {
            Log.d("Bot.java", "faux(): distance>actualDistance");
            actual.setX(point.getX());
            actual.setY(point.getY());
        }

        return;
    }

    private Coordenada pathToTarget(Coordenada target){

        int halfW  = (int) width/2, halfH  = (int) height/2;
        int halfTW = (int) TILE_WIDTH_PIXELS/2, halfTH = (int) TILE_HEIGHT_PIXELS/2;

        int xTilePosition = (int) (positionX/TILE_WIDTH_PIXELS);
        int yTilePosition = (int) (positionY/TILE_HEIGHT_PIXELS);



        Coordenada ret = new Coordenada(0,0);

        faux(target, new Coordenada(((xTilePosition+1)*TILE_WIDTH_PIXELS)+halfTW-halfW, (int) this.positionY),  ret);
        faux(target, new Coordenada((int) this.positionX, ((yTilePosition+1)*TILE_HEIGHT_PIXELS)+halfTH-halfH),  ret);
        faux(target, new Coordenada(((xTilePosition-1)*TILE_WIDTH_PIXELS)+halfTW-halfW, (int) this.positionY),     ret);
        faux(target, new Coordenada((int) this.positionX, ((yTilePosition-1)*TILE_HEIGHT_PIXELS)+halfTH-halfH),     ret);

        return ret;
    }

}