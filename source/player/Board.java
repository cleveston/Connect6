package player;

import java.util.Arrays;
import main.*;

//The Game Class
public class Board {

    //The Board Size
    private final int BOARD_SIZE;

    //The board
    private int[][] connect6;

    //The Constructor Method
    public Board(int size) {
        BOARD_SIZE = size;

        //Create the board
        connect6 = new int[BOARD_SIZE][BOARD_SIZE];

        //Initialize each position with 0
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                connect6[i][j] = 0;
            }
        }

        //Put 1 in the board`s middle
        connect6[BOARD_SIZE / 2][BOARD_SIZE / 2] = 1;

    }

    //Print the board
    public void print() {
        System.out.println();

        System.out.print("           ");
        for (int j = 0; j < BOARD_SIZE; j++) {
            System.out.print(j + "   ");
        }
        System.out.println();
        System.out.println();
        for (int i = 0; i < BOARD_SIZE; i++) {
            System.out.print(i + ":         ");
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(connect6[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();

    }

    //Update the board
    public int update(Move m, Players player) {
        if ((connect6[m.getX1()][m.getY1()] != 0) || (connect6[m.getX2()][m.getY2()] != 0)) {
            return (-1);
        }

        //If black player
        if (player.equals(Players.BLACK)) {
            connect6[m.getX1()][m.getY1()] = 1;
            connect6[m.getX2()][m.getY2()] = 1;
        } else {
            connect6[m.getX1()][m.getY1()] = 2;
            connect6[m.getX2()][m.getY2()] = 2;
        }

        return 0;
    }

    //Check if the move is allowed
    public boolean isIllegalMove(Move m) {

        return !(this.validateMove(m.getX1(), m.getY1())
                && this.validateMove(m.getX2(), m.getY2())
                && connect6[m.getX1()][m.getY1()] == 0
                && connect6[m.getX2()][m.getY2()] == 0)
                || (m.getX1() == m.getX2() && m.getY1() == m.getY2());

    }

    //Validate the move
    private boolean validateMove(int x, int y) {

        try {
            if (connect6[x + 1][y] != 0) {
                return true;
            }
        } catch (Exception e) {
        }

        try {
            if (connect6[x - 1][y] != 0) {
                return true;
            }
        } catch (Exception e) {
        }

        try {
            if (connect6[x][y + 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (connect6[x][y - 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (connect6[x + 1][y + 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (connect6[x + 1][y - 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (connect6[x - 1][y + 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (connect6[x - 1][y - 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;

    }

    @Override
    public Object clone() {

        //Create new board
        Board newCopy = new Board(BOARD_SIZE);

        //Copy using streams
        int[][] dataCopy = Arrays.stream(this.connect6)
                .map((int[] row) -> row.clone())
                .toArray((int length) -> new int[length][]);

        //Set the new board
        newCopy.setBoard(dataCopy);

        //Return the copied board
        return newCopy;
    }

    //Set the new value to the board
    private void setBoard(int[][] board) {
        this.connect6 = board;
    }

    //Return the board size
    public int getSize() {
        return this.BOARD_SIZE;
    }

    //Return the board size
    public int[][] getBoard() {
        return connect6;
    }

}
