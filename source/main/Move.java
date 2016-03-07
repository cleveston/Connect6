package main;


// Move Class
public class Move {

    //The positions of the stones
    private int x1, y1;
    private int x2, y2;

    //The Constructor MEthod
    public Move(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    //Get the position x1
    public int getX1() {
        return x1;
    }

    //Get the position y1
    public int getY1() {
        return y1;
    }

    //Get the position x2
    public int getX2() {
        return x2;
    }

    //Get the position y2
    public int getY2() {
        return y2;
    }

}
