import java.util.Scanner;

public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED =
            "Game not yet implemented.";
    /**
     * Construct a new instance of RPS with the given possible moves.
     *
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            System.arraycopy(args, 0, moves, 0, args.length);
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        String playerMove;
        while (true) {
            System.out.println(PROMPT_MOVE);
            playerMove = in.nextLine().trim();
    
            if (playerMove.equals("q")) {
                game.displayStats();
                break;
            }

        // TODO: Insert the code to play the game.
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written
        // to do most of the work! And don't forget Javadoc.

            String cpuMove = game.genCPUMove();

            if (!game.isValidMove(playerMove)) {
                System.out.println(INVALID_INPUT);
                continue;
            }
        
            game.playRPS(playerMove, cpuMove);
        }

        in.close();
    }
    /*
     The main method uses a loop to play the game. It asks the user for their input/move
     and generates a CPU move. It makes sure that the users move is valid and then plays a round.
     It plays until the player wants to quit and displays the last 10 rounds or the rounds played.
     */

    @Override
    public int determineWinner(String playerMove, String cpuMove) {
        if (!isValidMove(playerMove) || !isValidMove(cpuMove)) {
            return -1;
        }
    
        if (playerMove.equals(cpuMove)) {
            return 0;
        }
    
        int playerIndex = -1, cpuIndex = -1;
        for (int i = 0; i < possibleMoves.length; i++) {
            if (possibleMoves[i].equals(playerMove)) {
                playerIndex = i;
            }
            if (possibleMoves[i].equals(cpuMove)) {
                cpuIndex = i;
            }
        }
    
        int distance = (playerIndex - cpuIndex + possibleMoves.length) % possibleMoves.length;
    
        if (distance == 1) {
            return 1;
        }
        else if (distance == possibleMoves.length - 1) {
            return 2;
        }
        else {
            return 0;
        }
    }
}
