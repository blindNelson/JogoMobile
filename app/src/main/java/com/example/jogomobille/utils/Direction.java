package com.example.jogomobille.utils;

public class Direction {

    public enum Direcao{
        UP,DOWN,LEFT,RIGHT
    }

    public static Coordenada getVelocityVectorByDirection(Direcao direcao){
        switch (direcao){
            case UP:
                return new Coordenada(0,-1);
            case DOWN:
                return new Coordenada(0, 1);
            case LEFT:
                return new Coordenada(-1,0);
            case RIGHT:
                return new Coordenada(1, 0);
        }
        return new Coordenada(0,0);
    }

}
