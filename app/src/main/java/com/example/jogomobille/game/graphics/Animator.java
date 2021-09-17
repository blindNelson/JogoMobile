package com.example.jogomobille.game.graphics;

import android.graphics.Canvas;
import android.util.Log;

import com.example.jogomobille.game.GameDisplay;
import com.example.jogomobille.game.gameobject.player.Player;

public class Animator {
    private Sprite[] spriteArray;
    private int idxNotMovingFrame = 0;
    private int idxMovingFrame = 1;
    private int updatesBeforeNMF;
    private static final int MAX_UPD_BEFORE_NMF = 5;

    public Animator(Sprite[] spriteArray) {
        this.spriteArray = spriteArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        Log.d("Animator.java", "draw():state="+player.getPlayerState().getState());
        switch (player.getPlayerState().getState()){
            case NOT_MOVING:
                //Log.d("Animator.java", "draw():state=NOT_MOVING;idxNotMovingFrame="+idxNotMovingFrame);
                drawFrame(canvas, gameDisplay, player, spriteArray[idxNotMovingFrame]);
                break;
            case STARTED_MOVING:
                //Log.d("Animator.java", "draw():state=STARTED_MOVING;idxMovingFrame="+idxMovingFrame);
                updatesBeforeNMF = MAX_UPD_BEFORE_NMF;
                drawFrame(canvas, gameDisplay, player, spriteArray[idxMovingFrame]);
                break;
            case IS_MOVING:
                //Log.d("Animator.java", "draw():state=IS_MOVING;idxMovingFrame="+idxMovingFrame);
                updatesBeforeNMF--;
                if(updatesBeforeNMF == 0){
                    updatesBeforeNMF = MAX_UPD_BEFORE_NMF;
                    toggleIMF();
                }
                drawFrame(canvas, gameDisplay, player, spriteArray[idxMovingFrame]);
                break;
            default:
                break;
        }
    }

    private void toggleIMF() {
        if(idxMovingFrame == 1)
            idxMovingFrame = 2;
        else
            idxMovingFrame = 1;
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite){
        sprite.draw(
                canvas,
                (int)gameDisplay.gameToDisplayCoordinatesX(player.getPositionX()),
                (int)gameDisplay.gameToDisplayCoordinatesY(player.getPositionY())
        );
    }
}
