package player;

//The MiniMax Algorithm
public class MiniMax {

    //The player;
    private final Players player;

    //The Constructor Method
    public MiniMax(Players player) {

        //Set the player
        this.player = player;

    }

    //The MiniMax algorithm, with alpha-beta pruning
    public int calc(Node currentNode, int depth, int alpha, int beta) {

        //Check if the depth was reached
        if (depth <= 0) {

            return currentNode.getScore();
        }

        //If node player is equals to the current player, try to maximize
        if (currentNode.getCurrentPlayer().equals(this.player)) {

            //Set the beta equals to minus infinity
            int currentAlpha = Integer.MIN_VALUE;

            //Get the children
            for (Node child : currentNode.getChildren()) {

                //Get the max alpha recursively
                currentAlpha = Math.max(currentAlpha, this.calc(child, depth - 1, alpha, beta));

                //Get the max alpha
                alpha = Math.max(alpha, currentAlpha);

                //
                if (alpha >= beta) {
                    return alpha;
                }
            }

            //Return the current alpha
            return currentAlpha;
        }

        //Set the beta equals to infinity
        int currentBeta = Integer.MAX_VALUE;

        //For the opposite player, get the children
        for (Node child : currentNode.getChildren()) {

            //Get the min beta recursively
            currentBeta = Math.min(currentBeta, this.calc(child, depth - 1, alpha, beta));

            //Get the min beta
            beta = Math.min(beta, currentBeta);

            //
            if (beta <= alpha) {
                return beta;
            }
        }

        //Return the current beta
        return currentBeta;
    }

}
