/*
Pelin pääluokka, suorittamalla tämä voidaan peliä pelata.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        //Defining attributes
        Tiedosto.teeTiedosto();
        Board board = new Board();
        Opponent ai = new Opponent();
        Scanner sc = new Scanner(System.in);
        char[] ships = {'C', 'B', 'R', 'S', 'D'};
        String[] shipNames = {"Carrier - R - 5", "Battleship - B - 4", "Cruiser - R - 3", "Submarine - S - 2", "Destroyer - D - 1"};
        int x = 0;
        int y = 0;
        int orientation;
        boolean checks = false;
        int turn = 0;
        boolean winner = false;


        //Entering anything else than enter creates InputMismatchException
        //Start screen
        System.out.println("Press enter for new game \n");
        System.out.println("At the start you'll place your ships one by one");
        System.out.println("To place a ship insert x-coord, y-coord and then orientation");
        System.out.println("Separate the values with enter");
        System.out.println("1 = orientation downwards from the coordination");
        System.out.println("2 = orientation to right from the coordination");
        System.out.println("Once you've placed your ships, you'll be able to shoot at the opponent's board");
        System.out.println("Shooting is done by entering x-coord and y-coord");
        System.out.println("M = Missed, O = Hit, X = The ship drowned \n ");
        System.out.println("Highscore: " + Tiedosto.lueEnnatys());
        if (sc.hasNextLine()){
            board.formatBoard();
            board.printBoard();
        }


        //Player inserts coordinates and orientation. Board is tested if it's a valid move.
        //If yes, places and prints. If not asks again until valid values are entered
        for (int i = 0; i < 5; i++) {
            System.out.println(shipNames[i] + ": choose coordinates(x,y) and orientation");
            System.out.print("x:  ");
            x = sc.nextInt();
            System.out.print("y:  ");
            y = sc.nextInt();
            System.out.print("orientation: ");
            orientation = sc.nextInt();
            while (!board.fitShip(new Ship(5 - i, ships[i]), x, y, orientation)) {
                System.out.print("The ship won't fit there!");
                System.out.print("x:  ");
                x = sc.nextInt();
                System.out.print("y:  ");
                y = sc.nextInt();
                System.out.print("orientation: ");
                orientation = sc.nextInt();
            }
            board.placeShip(new Ship(5 - i, ships[i]), x, y, orientation);
            board.printBoard();
        }



        ai.placeShips();


        //Lets the player shoot at the opponents board, then ai shoots randomly at your board, until one wins
        while(!board.gameWin()) {
            System.out.println("Turn " + ++turn + ": Select coordinates to shoot at");
            while (!checks) {
                System.out.print("x:  ");
                x = sc.nextInt();
                if ((x >= 1) && (x <= 10)) {
                    checks = true;
                }
                else{
                    System.out.println("Insert a coordinate between 0 and 10");
                }
            }
            checks = false;
            while (!checks) {
                System.out.print("y:  ");
                y = sc.nextInt();
                if ((y >= 1) && (y <= 10)) {
                    checks = true;
                }
                else{
                    System.out.println("Insert a coordinate between 0 and 10");
                }
            }
            checks = false;
            ai.getOboard().shoot(x, y);
            ai.shootAt(board);
            ai.getOboard().printShootBoard(board);
            if (ai.getOboard().gameWin()){
                winner = true;
                break;
            }
        }

        //Prints a message according to who won. If player won writes the record to the text file
        if (winner){
            System.out.println("You beat the game!");
            if(turn < Integer.parseInt(Tiedosto.lueEnnatys()) || Tiedosto.lueEnnatys() == null) {
                Tiedosto.uusiEnnatys(Integer.toString(turn));
            }
        }else{
            System.out.println("You lost to Java rng");
        }

    }
}