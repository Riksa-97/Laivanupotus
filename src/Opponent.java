import java.util.Random;

public class Opponent {
    Board oboard;
    int x;
    int y;
    int orientation;
    Random rng = new Random();


    public Opponent(){
        oboard = new Board();
        oboard.formatBoard();
        x = 0; y = 0; orientation = 0;
    }
    public Board getOboard(){
        return oboard;
    }

    //Not functional yet
    public void placeShips(){
        char[] ships = {'C','B','R','S','D'};
        x = rng.nextInt(10)+1;
        y = rng.nextInt(10)+1;
        orientation = rng.nextInt(2)+1;
        for(int i=0; i<5;i++){
            while(!oboard.fitShip(new Ship(5-i, ships[i]), x, y, orientation)) {
                x = rng.nextInt(10)+1;
                y = rng.nextInt(10)+1;
                orientation = rng.nextInt(2)+1;
            }
            oboard.placeShip(new Ship(5-i, ships[i]), x, y, orientation);
            System.out.println("done");
        }
    }
    public void shootAt(Board board){
        x = rng.nextInt(10)+1;
        y = rng.nextInt(10)+1;
        System.out.println(Integer.toString(x) + Integer.toString(y));
        board.shoot(x, y);
    }
}