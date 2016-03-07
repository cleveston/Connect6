package main;

//The Game Class
public class Game {

    //The Board Size
    private final int BOARD_SIZE;

    //The board
    private int[][] board;

    //The Constructor Method
    public Game(int size) {
        BOARD_SIZE = size;

        //Create the board
        board = new int[BOARD_SIZE][BOARD_SIZE];

        //Initialize each position with 0
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }

        //Put 1 in the board`s middle
        board[BOARD_SIZE / 2][BOARD_SIZE / 2] = 1;

    }

    //Print the board
    public void printBoard() {
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
                System.out.print(board[i][j] + "   ");
            }
            System.out.println();
        }
        System.out.println();

    }

    //Update the board
    public int update(Move m, String player) {
        if ((board[m.getX1()][m.getY1()] != 0) || (board[m.getX2()][m.getY2()] != 0)) {
            return (-1);
        }
        if (player.equals("BLACK") || player.equals("black")) {
            board[m.getX1()][m.getY1()] = 1;
            board[m.getX2()][m.getY2()] = 1;
        } else {
            board[m.getX1()][m.getY1()] = 2;
            board[m.getX2()][m.getY2()] = 2;
        }

        return 0;
    }

    //Check if the move is allowed
    public boolean isIllegalMove(Move m) {

        return !(this.validateMove(m.getX1(), m.getY1())
                && this.validateMove(m.getX2(), m.getY2())
                && board[m.getX1()][m.getY1()] == 0
                && board[m.getX2()][m.getY2()] == 0)
                || (m.getX1() == m.getX2() && m.getY1() == m.getY2());

    }

    //Validate the move
    private boolean validateMove(int x, int y) {

        try {
            if (board[x + 1][y] != 0) {
                return true;
            }
        } catch (Exception e) {
        }

        try {
            if (board[x - 1][y] != 0) {
                return true;
            }
        } catch (Exception e) {
        }

        try {
            if (board[x][y + 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (board[x][y - 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (board[x + 1][y + 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (board[x + 1][y - 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (board[x - 1][y + 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }
        try {
            if (board[x - 1][y - 1] != 0) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;

    }

    //Get the game result so far
    public int getResult() {

        //Counters
        int blackScore = 0;
        int whiteScore = 0;

        //For each position in the board
        for (int i = this.BOARD_SIZE - 1; i >= 0; --i) {

            for (int j = 0; j <= this.BOARD_SIZE - 1; ++j) {

                //If the position is empty, just go on
                if (this.board[i][j] == 0) {
                    continue;
                }

                //Checking horizontaly
                if (j >= 5) {

                    for (int k = 0; k <= 5; k++) {

                        //If find a stone, count it, if find a blank space, abort
                        if (this.board[i][j - k] == 1) {
                            blackScore++;
                        } else if (this.board[i][j - k] == 2) {
                            whiteScore++;
                        } else {
                            break;
                        }
                    }

                    //Check if someone wins
                    if (blackScore == 6) {
                        return 1;
                    } else if (whiteScore == 6) {
                        return 2;
                    }

                    //Clear counters
                    blackScore = 0;
                    whiteScore = 0;
                }

                //Checking verticaly
                if (i >= 5) {
                    for (int k = 0; k <= 5; k++) {

                        //If find a stone, count it, if find a blank space, abort
                        if (this.board[i - k][j] == 1) {
                            blackScore++;
                        } else if (this.board[i - k][j] == 2) {
                            whiteScore++;
                        } else {
                            break;
                        }
                    }

                    //Check if someone wins
                    if (blackScore == 6) {
                        return 1;
                    } else if (whiteScore == 6) {
                        return 2;
                    }

                    //Clear counters
                    blackScore = 0;
                    whiteScore = 0;
                }

                //Checking diagonal up-right
                if (j <= BOARD_SIZE - 6 && i >= 5) {
                    for (int k = 0; k <= 5; k++) {
                        if (this.board[i - k][j + k] == 1) {
                            blackScore++;
                        } else if (this.board[i - k][j + k] == 2) {
                            whiteScore++;
                        } else {
                            break;
                        }
                    }

                    //Check if someone wins
                    if (blackScore == 6) {
                        return 1;
                    } else if (whiteScore == 6) {
                        return 2;
                    }

                    //Clear counters
                    blackScore = 0;
                    whiteScore = 0;
                }

                //Checking diagonal up-left
                if (j >= 5 && i >= 5) {

                    for (int k = 0; k <= 5; k++) {
                        if (this.board[i - k][j - k] == 1) {
                            blackScore++;
                        } else if (this.board[i - k][j - k] == 2) {
                            whiteScore++;
                        } else {
                            break;
                        }
                    }

                    //Check if someone wins
                    if (blackScore == 6) {
                        return 1;
                    } else if (whiteScore == 6) {
                        return 2;
                    }

                    //Clear counters
                    blackScore = 0;
                    whiteScore = 0;

                }
            }
        }

        //Check for draw
        for (int i = 0; i < this.BOARD_SIZE - 1; i++) {
            for (int j = 0; j < this.BOARD_SIZE - 1; j++) {
                //Game has not ended yet
                if (this.board[i][j] == 0) {
                    return -1;
                }
            }
        }

        //Game draw!
        return 0;
    }

}
