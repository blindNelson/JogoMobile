package com.example.jogomobille.game.map.Mechanics;

import android.util.Log;

import com.example.jogomobille.game.map.TileMap;
import com.example.jogomobille.utils.Coordenada;
import com.example.jogomobille.utils.Utils;


public class MapConfig {
    private final TileMap tileMap;

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

    Node primeiro;Coordenada min;

    public MapConfig(TileMap tileMap){
        this.tileMap = tileMap;
        min = new Coordenada(0,0);
        primeiro = new Node(null, min);
    }

    public void stackPriority(int x, int y, Coordenada target){

        int distanceNewNode = (int)Utils.getDistanceBetweenPoints(x, y, target.getX(), target.getY());
        int distanceFNode = (int)Utils.getDistanceBetweenPoints(
                primeiro.getInfo().getX(), primeiro.getInfo().getY(), target.getX(), target.getY()
        );

        if(distanceNewNode<=distanceFNode){
            Log.d("MapConfig.java", "getCoordinates():distanceNewNode="+distanceNewNode+";");
            primeiro = new Node(primeiro, new Coordenada(x, y));
        }
        else{
            Log.d("MapConfig.java", "getCoordinates():distanceFNode="+distanceFNode+";");
            primeiro.setProximo(new Node(primeiro.getProximo(), new Coordenada(x, y)));
        }
    }

    public Coordenada getCoordinates(Coordenada gameObject, Coordenada target) {

        stackPriority(gameObject.getX()+32+64, gameObject.getY(), target);
        stackPriority(gameObject.getX()-32, gameObject.getY(), target);
        stackPriority(gameObject.getX(), gameObject.getY()+32+64, target);
        stackPriority(gameObject.getX(), gameObject.getY()-32, target);

        loop:while(tileMap.isPixelColiding( primeiro.getInfo().getX(), primeiro.getInfo().getY() )){
            if(primeiro.proximo==null)
                return target;
            primeiro = primeiro.getProximo();
        }
        if(primeiro.getInfo()==min) {
            Log.d("MapConfig.java", "getCoordinates():primeiro.info="+primeiro.info);
            return target;
        }
        Log.d("MapConfig.java", "getCoordinates():return primeiro.info");
        return primeiro.getInfo();
    }
}
