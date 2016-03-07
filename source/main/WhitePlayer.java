package main;

import player.AbstractPlayer;
import player.Players;
import player.Board;
import player.MiniMax;

//The White Player Class
public final class WhitePlayer extends AbstractPlayer {

    //The Constructor Method
    public WhitePlayer(String name, int boardSize, int maxTimePerMove) {

        //Define the white player
        super.player = Players.WHITE;

        //Define the other properties
        super.name = name;
        super.boarSize = boardSize;
        super.maxTimePerMove = maxTimePerMove;

        //Create the MiniMax Algorithm
        super.minimax = new MiniMax(this.player);

        //Create the internal board
        super.board = new Board(boarSize);
    }

}
