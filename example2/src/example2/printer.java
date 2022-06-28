package example2;

import static java.lang.Thread.sleep;
import java.io.IOException;

import java.util.Scanner;

public class printer {

    Scanner scanner = new Scanner(System.in);
    String aux;

    // OF main.java
    public void home() throws IOException, InterruptedException {
        // BEGIN APP //
        System.out.println("/-------------------------------------------------------------------/");
        sleep(500);
        System.out.println("/                                                                   /");
        sleep(500);
        System.out.println("/                                                                   /");
        sleep(500);
        System.out.println("/                                                                   /");
        sleep(500);
        System.out.println("/                      BIENVENIDO A 'EL CIRCULO'                    /");
        sleep(2000);
        System.out.println("/                                                                   /");
        System.out.print("/                  Enter para continuar --> ");
        aux = scanner.next();
        System.out.println("/                                                                   /");
        System.out.println("/                                                                   /");
        System.out.println("/                                                                   /");
    }

    // OF interfaceGame.java
    public void menu() throws InterruptedException {
        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                                                                   /");
        System.out.println("/                           MENU PRINCIPAL                          /");
        System.out.println("/                                                                   /");
        System.out.println("/                        Seleccione un opcion                       /");
        System.out.println("/                                                                   /");
        System.out.println("/                  1 --> Inciar partida                             /");
        System.out.println("/                  2 --> Opciones de data                           /");
        System.out.println("/                  0 --> Salir                                      /");
        System.out.println("/                                                                   /");
        sleep(1000);
    }

    // OF interfaceGame.java
    public void opcNotNumber() throws InterruptedException {
        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                         OPCION NO VALIDA                          /");
        System.out.println("/            (asegurese de ingresar un numero (min 50))             /");
        System.out.println("/-------------------------------------------------------------------/");
        sleep(1500);
    }

    // OF interfaceGame.java
    public void opcMinNumber() throws InterruptedException {
        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                         OPCION NO VALIDA                          /");
        System.out.println("/       (Asegurese de ingresar un numero mayor o igual a 50)        /");
        System.out.println("/-------------------------------------------------------------------/");
        sleep(1500);
    }

    public void gameOver() throws InterruptedException {
        System.out.println("/                                                                   /");
        System.out.println("/-------------------------------------------------------------------/");
        sleep(500);
        System.out.println("/                                                                   /");
        sleep(500);
        System.out.println("/                              GAME OVER                            /");
        sleep(500);
        System.out.println("/                                                                   /");
        sleep(500);
        System.out.println("/                          Gracias por jugar                        /");
        sleep(1500);
        System.out.println("/                                                                   /");
        sleep(500);
        System.out.println("/-------------------------------------------------------------------/");
        
        System.exit(0);
    }

}
