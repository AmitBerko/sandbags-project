import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int firstPlayerX;
    static int firstPlayerY;
    static int secondPlayerX;
    static int secondPlayerY;
    static int carpetX;
    static int carpetY;
    static int carpetSize;
    static String nextToPlay = "A"; // A is first by default
    static boolean hasFirstWon = false;
    static boolean hasSecondWon = false;

    private static void initializeValues() {
        System.out.println("enter A player X location");
        firstPlayerX = scanner.nextInt();
        System.out.println("enter A player Y location");
        firstPlayerY = scanner.nextInt();
        System.out.println("enter B player X location");
        secondPlayerX = scanner.nextInt();
        System.out.println("enter B player Y location");
        secondPlayerY = scanner.nextInt();
        System.out.println("enter WINNERS CARPET top left location");
        carpetX = scanner.nextInt();
        carpetY = scanner.nextInt();
        System.out.println("enter WINNERS CARPET size");
        carpetSize = scanner.nextInt();
    }

    private static void printBoard() {
        for (int row = 0; row < 12; row++) {
            for (int col = 0; col < 12; col++) {
                if (col == 0 || row == 0 || row == 11 || col == 11) {
                    System.out.print('#'); // Border
                } else if (col == firstPlayerY && row == firstPlayerX) {
                    System.out.print('A'); // Player A
                } else if (col == secondPlayerY && row == secondPlayerX) {
                    System.out.print('B'); // Player B
                } else if (col >= carpetY && col < carpetY + carpetSize
                        && row >= carpetX && row < carpetX + carpetSize) {
                    System.out.print('*'); // Carpet
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
    }

    private static boolean isMovingTowardsBorder(int playerXloc, int playerYloc, int playerMovement) {
        switch (playerMovement) {
            case 1:
                return (playerXloc - 1) == 0;
            case 2:
                return (playerXloc + 1) == 11;
            case 3:
                return (playerYloc + 1) == 11;
            case 4:
                return (playerYloc - 1) == 0;
        }
        return true; // Shouldn't get here
    }

    private static void movePlayer(String player, int playerMovement) {
        if (player.equals("A")) {
            if (isMovingTowardsBorder(firstPlayerX, firstPlayerY, playerMovement)) {
                System.out.println("you cant go outside the board");
            } else {
                switch (playerMovement) {
                    case 1:
                        firstPlayerX--;
                        break;
                    case 2:
                        firstPlayerX++;
                        break;
                    case 3:
                        firstPlayerY++;
                        break;
                    case 4:
                        firstPlayerY--;
                        break;
                }
                hasFirstWon = didPlayerWin(firstPlayerY, firstPlayerX);
            }
        } else if (player.equals("B")) {
            if (isMovingTowardsBorder(secondPlayerX, secondPlayerY, playerMovement)) {
                System.out.println("you cant go outside the board");
            } else {
                switch (playerMovement) {
                    case 1:
                        secondPlayerX--;
                        break;
                    case 2:
                        secondPlayerX++;
                        break;
                    case 3:
                        secondPlayerY++;
                        break;
                    case 4:
                        secondPlayerY--;
                        break;
                }
                hasSecondWon = didPlayerWin(secondPlayerY, secondPlayerX);
            }
        }
    }

    private static int getMoveDirection() {
        int playerMovement = scanner.nextInt();
        if (0 > playerMovement || playerMovement > 4) {
            System.out.println("invalid option, please enter a number between 1 and 4");
            return -1; // error
        }
        return playerMovement;
    }

    private static void makeTurn(String player) {
        System.out.println("player " + player + "'s move");
        System.out.println("1-up 2-down 3-right 4-left");
        int playerMovement = getMoveDirection();
        if (playerMovement != -1) {
            movePlayer(player, playerMovement);
        }

        // After the current player has moved, we can give the turn to the other player
        if (nextToPlay.equals("A")) {
            nextToPlay = "B";
        } else if (nextToPlay.equals("B")) {
            nextToPlay = "A";
        }
    }

    private static boolean didPlayerWin(int playerXloc, int playerYloc) {
        return (carpetY <= playerXloc && playerXloc < carpetY + carpetSize
                && carpetX <= playerYloc && playerYloc < carpetX + carpetSize);
    }

    public static void main(String[] args) {
	System.out.println("WELCOME");
        initializeValues();
        printBoard();
        while (!hasFirstWon && !hasSecondWon) {
            makeTurn(nextToPlay);
            printBoard();
            if (hasFirstWon) {
                System.out.println("player A won this round!");
            } else if (hasSecondWon) {
                System.out.println("player B won this round!");
            }
        }
    }
}
