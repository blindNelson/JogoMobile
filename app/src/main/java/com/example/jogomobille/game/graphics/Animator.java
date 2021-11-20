package com.example.jogomobille.game.graphics;

import android.graphics.Canvas;
import android.util.Log;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.gameobject.player.Player;

public class Animator {
    private Sprite[] playerSpriteArray;
    private int idxNotMovingFrame = 0;
    private int idxMovingFrame = 1;
    private int updatesBeforeNMF;
    private static final int MAX_UPD_BEFORE_NMF = 5;

    public Animator(Sprite[] playerSpriteArray) {
        this.playerSpriteArray = playerSpriteArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        Log.d("Animator.java", "draw():state="+player.getPlayerState().getState());
        switch (player.getPlayerState().getState()){
            case STARTED_MOVING:
                //Log.d("Animator.java", "draw():state=STARTED_MOVING;idxMovingFrame="+idxMovingFrame);
                updatesBeforeNMF = MAX_UPD_BEFORE_NMF;
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                break;
            default:
                updatesBeforeNMF--;
                if(updatesBeforeNMF == 0){
                    updatesBeforeNMF = MAX_UPD_BEFORE_NMF;
                    toggleIMF();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                break;
        }
    }

    private void toggleIMF() {
        idxMovingFrame++;
        if (idxMovingFrame > 3) idxMovingFrame = 0;
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite){
        sprite.draw(
                canvas,
                (int)gameDisplay.gameToDisplayCoordinatesX(player.getPositionX() - 32),
                (int)gameDisplay.gameToDisplayCoordinatesY(player.getPositionY() - 32)
        );
    }

    public void setPlayerSpriteArray(Sprite[] playerSpriteArray) {
        this.playerSpriteArray = playerSpriteArray;
    }
}