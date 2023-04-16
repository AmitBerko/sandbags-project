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

    private static int calculateDistance(int playerXloc, int playerYloc, int carpetX, int carpetY) {
        return Math.abs(playerXloc - carpetX) + Math.abs(playerYloc - carpetY);
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

    public static void main(String[] args) {
        System.out.println("WELCOME");
        initializeValues();
        printBoard();

        // Initialized the min distance to a high number
        int minADistance = 100;
        int curADistance;
        int minBDistance = 100;
        int curBDistance;

        // Loop through the entire carpet and check for the minimum distance for each player
        for (int row = carpetX; row < carpetX + carpetSize; row++) {
            for (int col = carpetY; col < carpetY + carpetSize; col++) {
                curADistance = calculateDistance(firstPlayerX, firstPlayerY, row, col);
                curBDistance = calculateDistance(secondPlayerX, secondPlayerY, row, col);
                if (minADistance > curADistance) {
                    minADistance = curADistance;
                }
                if (minBDistance > curBDistance) {
                    minBDistance = curBDistance;
                }
            }
        }

        minADistance--; // Decrease by 1 because player A starts
        if (minADistance < minBDistance) {
            System.out.println("A player has the better chance");
        } else if (minADistance > minBDistance) {
            System.out.println("B player has the better chance");
        } else {
            System.out.println("Chances are equal for both players");
        }
    }
}
