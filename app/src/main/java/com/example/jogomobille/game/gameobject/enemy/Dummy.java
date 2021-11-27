package com.example.jogomobille.game.gameobject.enemy;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.jogomobille.R;
import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.Gameloop;
import com.example.jogomobille.game.gameobject.Circle;
import com.example.jogomobille.game.gameobject.player.Player;
import com.example.jogomobille.game.map.Mechanics.Colision;
import com.example.jogomobille.game.map.Mechanics.MapConfig;
import com.example.jogomobille.game.map.TileMap;
import com.example.jogomobille.utils.Coordenada;
import com.example.jogomobille.utils.Direction;
import com.example.jogomobille.utils.Direction.Direcao;
import com.example.jogomobille.utils.Utils;

public class Dummy extends Circle {

    private static final double SPEED_PIXELS_PER_SECOND = 300;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND / Gameloop.MAX_UPS;

    //private final MapConfig mapConfig;
    private final Player player;
    private final TileMap tileMap;

    private Node primeiro;
    private Direcao direcao;

    public Dummy(Context context, double positionX, double positionY, Colision colision, TileMap tileMap, Player player) {
        super(context, ContextCompat.getColor(context, R.color.Aquamarine), positionX, positionY, 32, colision);

        this.tileMap = tileMap;
        this.player = player;
        primeiro = null;
        direcao = null;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay){
        super.draw(canvas, gameDisplay);
    }

    @Override
    public void update() {

        //joga o tarjeto no metodo e devolve a direção que o bot deve ir.
        Coordenada target = getCoordinates(new Coordenada((int)player.getPositionX(), (int)player.getPositionY()));

        //calcular o vetor do inimigo para o player(em x e y)
        double directionToPlayerX = (target.getX() - positionX);
        double directionToPlayerY = (target.getY() - positionY);
        double auxX = directionToPlayerX;
        double auxY = directionToPlayerY;


        if(getDistanceBetweenObjects(this, player)<15)
            return;

        if(auxX<0)
            auxX=-auxX;
        if(auxY<0)
            auxY=-auxY;

        if (auxX > auxY) {
            if(directionToPlayerX<0)
                direcao = Direcao.LEFT;
            else
                direcao = Direcao.RIGHT;
        }
        else {
            if(directionToPlayerY<0)
                direcao = Direcao.UP;
            else
                direcao = Direcao.DOWN;
        }

        updateVelocity( (int)(Direction.getVelocityVectorByDirection(direcao).getX()*MAX_SPEED), (int)(Direction.getVelocityVectorByDirection(direcao).getY() * MAX_SPEED));
        Log.d("Dummy.java", "update():\n" +
                "vx:   " + velocityX + ";\n" +
                "vy:   " + velocityY + ";\n");

    }

    private class Node{
        private Coordenada info;
        private Node proximo;

        public Node(Node Proximo, Coordenada info){
            this.proximo = proximo;
            this.info = info;
        }

        public void setProximo(Node proximo) {
            this.proximo = proximo;
        }

        public Coordenada getInfo() {
            return info;
        }

        public Node getProximo() {
            return proximo;
        }
    }

    public void stackPriority(int x, int y, Coordenada target){

        if(primeiro==null) {
            Log.d("Dummy.java", "primeiro eh null");
            return;
        }
        int distanceNewNode = (int) Utils.getDistanceBetweenPoints(x, y, target.getX(), target.getY());
        int distanceFNode = (int)Utils.getDistanceBetweenPoints(
                primeiro.getInfo().getX(), primeiro.getInfo().getY(), target.getX(), target.getY()
        );

        Log.d("MapConfig.java", "getCoordinates():\n" +
                "distanceNewNode="+distanceNewNode+";\n" +
                "distanceFNode="+distanceFNode+";");

        if(distanceNewNode<=distanceFNode){
            Log.d("MapConfig.java", "getCoordinates():distanceNewNode=true;");
            this.primeiro = new Node(primeiro, new Coordenada(x, y));
        }
        else{
            Log.d("MapConfig.java", "getCoordinates():distanceFNode=true;");
            primeiro.setProximo(new Node(primeiro.getProximo(), new Coordenada(x, y)));
        }
    }



    public Coordenada getCoordinates(Coordenada target) {
        Coordenada min = new Coordenada(0,0);
        primeiro = new Node(null,  min);

        if(direcao==null){
            stackPriority((int)positionX+32+64, (int)positionY,    target);//, primeiro);
            stackPriority((int)positionX-32, (int)positionY,    target);//, primeiro);
            stackPriority(   (int)positionX, (int)positionY+32+64, target);//, primeiro);
            stackPriority(   (int)positionX, (int)positionY-32, target);//, primeiro);
        }
        else {
            switch (direcao) {
                case UP:
                    stackPriority((int) positionX, (int) positionY - 32, target);//
                    stackPriority((int) positionX + 32+64, (int) positionY, target);
                    stackPriority((int) positionX - 32, (int) positionY, target);
                    break;
                case DOWN:
                    stackPriority((int) positionX, (int) positionY + 32+64, target);//, primeiro);
                    stackPriority((int) positionX + 32+64, (int) positionY, target);// primeiro);
                    stackPriority((int) positionX - 32, (int) positionY, target);//, primeiro);
                    break;
                case LEFT:
                    stackPriority((int) positionX - 32, (int) positionY, target);//, primeiro);
                    stackPriority((int) positionX, (int) positionY + 32+64, target);//, primeiro);
                    stackPriority((int) positionX, (int) positionY - 32, target);//, primeiro);
                    break;
                case RIGHT:
                    stackPriority((int) positionX + 32+64, (int) positionY, target);//, primeiro);
                    stackPriority((int) positionX, (int) positionY + 32+64, target);//, primeiro);
                    stackPriority((int) positionX, (int) positionY - 32, target);//, primeiro);
                    break;
            }
        }

        try{
            while (tileMap.isColiding(primeiro.getInfo().getX(), primeiro.getInfo().getY())){// && tileMap.isColiding(primeiro.getInfo().getX() + (int) width, primeiro.getInfo().getY() + (int) height)) {
                if (primeiro.getProximo() == null) {
                    Log.wtf("Dummy.java","wtf: primeiro.proximo = null \nprimeiro: "+primeiro.getInfo());
                    return target;
                }
                Log.d("Dummy.java","\nd: primeiro colide : "+primeiro.getInfo());
                primeiro = primeiro.getProximo();
            }
        }

        catch(ArrayIndexOutOfBoundsException e){
            Log.wtf("Dummy.java", e);
        }

        if(primeiro.getInfo()==min) {
            Log.d("MapConfig.java", "getCoordinates():primeiro.info="+primeiro.info+";\n" +
                    "return target");
            return target;
        }

        Log.d("MapConfig.java", "getCoordinates():return primeiro.info");
        return primeiro.getInfo();
    }
}
