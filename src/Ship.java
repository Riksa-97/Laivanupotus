public class Ship {
    private int length;
    private char sign;
    private int health;
    public char[] ships = {'C','B','R','S','D'};
    public String[] shipNames = {"Carrier - R", "Battleship - B", "Cruiser - R", "Submarine - S", "Destroyer - D"};


    public Ship(int length, char sign){
        this.length=length;
        this.sign=sign;
        this.health=length;
    }


    public int getLength(){
        return this.length;
    }


    public char getSign(){
        return this.sign;
    }

    public int getHealth(){ return this.health; }

    public void setHealth(int i){this.health=i;}

    //Osuma -> health -1
    //Kun health = 0 -> uppoaa
}