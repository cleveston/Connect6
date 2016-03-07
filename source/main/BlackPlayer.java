package main;

import player.MiniMax;
import player.Players;
import player.AbstractPlayer;
import player.Board;

//The Black Player Class
public final class BlackPlayer extends AbstractPlayer {

    //The Constructor Method
    public BlackPlayer(String name, int boardSize, int maxTimePerMove) {

        //Define the black player
        super.player = Players.BLACK;

        //Define the other properties
        super.name = name;
        super.boarSize = boardSize;
        super.maxTimePerMove = maxTimePerMove;

        //Create the Min Max Algorithm
        super.minimax = new MiniMax(this.player);
        
        //Create the internal board
        super.board = new Board(this.boarSize);

    }

}
