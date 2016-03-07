package player;

//The player available
public enum Players {
    BLACK,
    WHITE;

    //Change the current player
    public static Players switchPlayer(Players player) {

        //If black, white. If white, black
        if (player == Players.BLACK) {
            return Players.WHITE;
        } else {
            return Players.BLACK;
        }
    }

    //Return the value for each player
    public static int getValue(Players player) {

        //Black=2; White=1
        if (player == Players.BLACK) {
            return 1;
        } else {
            return 2;
        }
    }

}
