package example2;

// class imports
import example2.listSimple;

// general imports
import java.util.Scanner;

import java.io.IOException;

public class Example2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        // generals

        // class
        printer printer = new printer();
        interfaceGame game = new interfaceGame();

        // variables
        // home presentation
        printer.home();

        // inizializate game
        game.interfaceMain();
        
        

    }

}
