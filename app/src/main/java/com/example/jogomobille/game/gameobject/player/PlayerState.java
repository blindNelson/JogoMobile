package com.example.jogomobille.game.gameobject.player;

public class PlayerState {
    public enum State{
        NOT_MOVING,
        STARTED_MOVING,
        IS_MOVING
    }

    private Player player;
    private  State state;

    public PlayerState(Player player){
        this.player = player;
        this.state = State.NOT_MOVING;
    }

    public State getState(){
        return  state;
    }

    public void update(){
        switch (state){
            case NOT_MOVING:
                if(player.getVelocityX() != 0 || player.getVelocityY() != 0)
                    state = State.STARTED_MOVING;
                break;
            case STARTED_MOVING:
                if(player.getVelocityX() != 0 || player.getVelocityY() != 0)
                    state = State.IS_MOVING;
                break;
            case IS_MOVING:
                if(player.getVelocityX() == 0 && player.getVelocityY() == 0)
                    state = State.NOT_MOVING;
                break;
            default:
                break;
        }
    }
}
