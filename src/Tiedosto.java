import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Tiedosto {
    public static void teeTiedosto() {
        BufferedWriter bw = null;
        try {


            File file = new File("C:/highscores.txt");


            if (!file.exists()) {
                file.createNewFile();
            }


        }
        catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }



    public static void uusiEnnatys(String ennatys) {
        String k = ennatys;
        BufferedWriter writer = null;
        try {

            File tiedosto = new File("C:/highscores.txt");


            FileWriter kirjota = new FileWriter(tiedosto);


            writer = new BufferedWriter(kirjota);

            writer.write(k);
            writer.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }


    }

    public static String lueEnnatys(){
        File tiedosto = new File("C:\\highscores.txt");
        String ennatys = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(tiedosto));
            ennatys = reader.readLine();
        }

        catch (IOException ioe){
            ioe.printStackTrace();
        }

        finally{

            return ennatys;
        }
    }
}
