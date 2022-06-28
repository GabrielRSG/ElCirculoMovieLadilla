package example2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import static java.lang.Thread.sleep;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class interfaceGame {

    listSimple playersList = new listSimple();
    listSimple losersList = new listSimple();

    boolean finish = false;
    Scanner scanner = new Scanner(System.in);

    printer printer = new printer();

    Random rand = new Random();
    Integer min;
    Integer max;
    int randomNum;

// nextInt as provided by Random is exclusive of the top value so you need to add 1 
    public void interfaceMain() throws IOException, InterruptedException {

        // options menu
        printer.menu();

        // cap option
        System.out.print("/                    --> ");
        char opcMain = scanner.next().charAt(0);

        do {
            // BEGIN opcion panel game
            switch (opcMain) {
                case '1' -> {
                    // -> standar game
                    interfaceOpc1();
                }
                case '2' -> {
                    // -> data options
                    interfaceOpc2();
                }
                case '0' -> {
                    // -> exit
                    interfaceOpc0();
                }
                default -> {
                    // -> invalid opc
                    interfaceOpcInvalid();
                }
            }

            // END opcion panel game
        } while (finish == false);

    }

    public void interfaceOpc1() throws IOException, InterruptedException {
        playersList.deleteAllList();
        System.out.println("/                                                                   /");
        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                                                                   /");
        System.out.println("/                  Configuraciones de partida                       /");
        System.out.println("/                                                                   /");
        System.out.println("/                  1 --> Iniciar partida normal                     /");
        System.out.println("/                  2 --> Cargar data anterior                       /");
        System.out.println("/                  3 --> Volver                                     /");
        System.out.println("/                                                                   /");

        System.out.print("/                    --> ");
        char opc = scanner.next().charAt(0);

        switch (opc) {
            case '1' -> {
                configGame();
            }
            case '2' -> {
                loadData();
            }
            case '3' -> {
                interfaceMain();
            }
            default -> {
                interfaceOpcInvalid();
                interfaceOpc1();
            }
        }

    }

    public void configGame() throws IOException, InterruptedException {

        int playersSize = playersList.getDimesion();
        String circleSizeString = "";

        int circleSize = 0;
        String playersSizeString = "";

        System.out.println("/                                                                   /");

        if (playersList.isEmpty() == true) {

            while (playersSize < 50) {

                do {
                    System.out.print("/                  Cantidad de jugadores (min 50) --> ");
                    playersSizeString = scanner.next();
                    System.out.println("/                                                                   /");
                } while ("".equals(playersSizeString));

                try {
                    playersSize = Integer.parseInt(playersSizeString);

                    if (playersSize < 50) {
                        printer.opcMinNumber();
                    }

                } catch (NumberFormatException ex) {
                    playersSize = 0;

                    printer.opcNotNumber();

                }
                System.out.println("/                                                                   /");

            }

        }

        while (circleSize < 50) {

            do {
                System.out.print("/                  N. de Posiciones (min 50) --> ");
                circleSizeString = scanner.next();
                System.out.println("/                                                                   /");
            } while ("".equals(circleSizeString));

            try {
                circleSize = Integer.parseInt(circleSizeString);

                if (circleSize < 50) {
                    printer.opcMinNumber();
                }

            } catch (NumberFormatException ex) {
                circleSize = 0;

                printer.opcNotNumber();
            }

        }

        if (circleSize > playersSize) {
            System.out.println("/                                                                   /");
            System.out.println("/               EL NUMERO DE JUGADORES NO PUEDE SER                 /");
            System.out.println("/           MENOR QUE EL NUMERO DE PLAZAS EN EL CIRCULO             /");
            System.out.println("/                                                                   /");
            System.out.println("/                           (burocracia)                            /");
            System.out.println("/                                                                   /");
            System.out.println("/              Ingrese nuevamente los valores de juego              /");

            configGame();
        } else {

            if (playersList.isEmpty() == true) {
                for (int i = 0; i < playersSize; i++) {
                    player newPlayer = formatNewPlayer(i, circleSize);

                    playersList.push(newPlayer);
                }

                String file = "C:\\Users\\Eliezer Meza\\Desktop\\prueba.txt";

                playersList.saveAllList(file);
            } else {
                Integer count = 0;

                listSimple auxList = new listSimple();

                while (playersList.isEmpty() == false) {
                    player aux = playersList.pop();

                    player newPlayer = formatLoadedPlayer(count, circleSize, aux);

                    auxList.push(newPlayer);

                    count++;
                }

                while (auxList.isEmpty() == false) {
                    playersList.push(auxList.pop());
                }
            }

        }

        standarGame(1);

    }

    public player formatNewPlayer(Integer _i, Integer _circleSize) {
        String ID = "ID_" + _i;
        String NAME = "Jugador_" + _i;
        String LASTNAME = "lName_" + _i;

        min = 18;
        max = 65;
        randomNum = rand.nextInt((max - min) + 1) + min;

        String AGE = randomNum + "";

        String SEX;

        min = 1;
        max = 2;
        randomNum = rand.nextInt((max - min) + 1) + min;

        if (randomNum == 1) {
            SEX = "Male";
        } else {
            SEX = "Female";
        }

        Integer INDEX;

        if (_i <= _circleSize) {
            INDEX = _i;
        } else {
            min = 0;
            max = _circleSize;
            randomNum = rand.nextInt((max - min) + 1) + min;

            INDEX = randomNum;
        }

        player aux = new player(ID, INDEX, NAME, LASTNAME, AGE, SEX);

        return aux;
    }

    public player formatLoadedPlayer(Integer _i, Integer _circleSize, player _player) {
        String ID = _player.id;
        String NAME = _player.name;
        String LASTNAME = _player.lastName;
        String AGE = _player.age;
        String SEX = _player.sex;

        Integer INDEX;

        if (_i <= _circleSize) {
            INDEX = _i;
        } else {
            min = 0;
            max = _circleSize;
            randomNum = rand.nextInt((max - min) + 1) + min;

            INDEX = randomNum;
        }

        player aux = new player(ID, INDEX, NAME, LASTNAME, AGE, SEX);

        return aux;
    }

    public void standarGame(Integer _nTurn) throws FileNotFoundException, InterruptedException {

        if (playersList.getDimesion() == 1) {
            viewResultOfGame();
        }

        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                                                                   /");
        System.out.println("/                             TURNO N. " + _nTurn + "                            /");
        sleep(1500);
        System.out.println("/                                                                   /");

        listSimple auxList = new listSimple();

        while (playersList.isEmpty() == false) {
            player inTurn = playersList.pop();

            inTurn.play();

            String desition = inTurn.desition;

            switch (desition) {
                case "pass" -> {
                    player passPlayer = inTurn;
                    passPlayer.description = "El jugador " + passPlayer.name + " " + passPlayer.lastName + " | " + passPlayer.id + " ha pasado de turno";

                    auxList.push(passPlayer);

                    System.out.println("/ " + passPlayer.description);
                }
                case "exit" -> {

                    player exitPlayer = inTurn;
                    exitPlayer.description = "El jugador " + exitPlayer.name + " " + exitPlayer.lastName + " | " + exitPlayer.id + " ha abondonado por voluntad propia 'El Circulo' en el turno " + _nTurn;

                    losersList.push(exitPlayer);

                    System.out.println("/ " + exitPlayer.description);
                }
                case "vote" -> {

                    player votePlayer = inTurn;
                    votePlayer.description = "El jugador " + votePlayer.name + " " + votePlayer.lastName + " | " + votePlayer.id + " ha elegido votar para eliminar a otro jugador";

                    auxList.push(votePlayer);

                    //System.out.println("/ " + votePlayer.description);
                }
            }

        }

        playersList = auxList;

        if (playersList.getDimesion() == 1) {
            viewResultOfGame();
        }

        System.out.println("/                                                                   /");
        System.out.println("/                          Jugadores activos                        /");
        System.out.println("/                                                                   /");

        auxList.showAllList();

        System.out.println("/                                                                   /");

        String idPlayer;

        System.out.print("/ Ingrese el ID de un jugador que desee eliminar --> ");
        idPlayer = scanner.next();

        player deletedPlayer = auxList.deleteById(idPlayer);

        deletedPlayer.description = "El jugador " + deletedPlayer.name + " " + deletedPlayer.lastName + " | " + deletedPlayer.id + " ha sido eliminado por voto en el turno " + _nTurn;
        losersList.push(deletedPlayer);

        System.out.println("/                                                                   /");
        System.out.println("/ " + deletedPlayer.description);
        System.out.println("/                                                                   /");

        if (playersList.getDimesion() > 1) {
            standarGame(_nTurn + 1);
        } else {
            viewResultOfGame();
        }

    }

    public void loadData() throws IOException, InterruptedException {
        playersList.chargeList(999);

        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                                                                   /");
        System.out.println("/                  Recuperando data de jugadores                    /");
        System.out.println("/                                                                   /");
        System.out.println("/                            Cargando...                            /");
        System.out.println("/                                                                   /");
        sleep(1500);

        if (playersList.isEmpty() == false) {
            System.out.println("/                    Datos de jugadores cargados                    /");
            configGame();

        } else {
            System.out.println("/                    No hay jugadores guardados                     /");
        }

        sleep(1500);
        interfaceOpc1();

    }

    public void viewResultOfGame() throws FileNotFoundException, InterruptedException {

        String file = "C:\\Users\\Eliezer Meza\\Desktop\\perdedores.txt";

        losersList.saveAllList(file);

        //losersList.showResultsGame();
        player playerWinner = playersList.pop();

        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                                                                   /");
        System.out.println("/                         Enhorabuena a " + playerWinner.id + "                       /");
        System.out.println("/                  Salve el ganador del 'El Circulo'                /");
        System.out.println("/                                                                   /");
        System.out.println("/-------------------------------------------------------------------/");

        interfaceOpc0();

    }

    public void interfaceOpc2() throws IOException, InterruptedException {

        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                                                                   /");
        System.out.println("/                         Opciones de data                          /");
        System.out.println("/                                                                   /");
        System.out.println("/                   1 --> Ver ultimos jugadores                     /");
        System.out.println("/                   2 --> Borrar ultimos jugadores                  /");
        System.out.println("/                   3 --> Volver                                    /");
        System.out.println("/                                                                   /");
        sleep(1500);

        System.out.print("/                    --> ");
        char opc = scanner.next().charAt(0);

        switch (opc) {
            case '1' ->
                // --> show data players
                showAllData();
            case '2' ->
                // --> clear data players
                clearAllData();
            case '3' ->
                // --> back to main menu
                interfaceMain();

            default -> {
                interfaceOpcInvalid();
                interfaceOpc2();
            }
        }

    }

    public void showAllData() throws IOException, InterruptedException {
        listSimple listAux = new listSimple();
        listAux.chargeList(1000);

        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                                                                   /");
        System.out.println("/                  Recuperando data de jugadores                    /");
        System.out.println("/                                                                   /");
        System.out.println("/                            Cargando...                            /");
        System.out.println("/                                                                   /");
        sleep(1500);
        System.out.println("/                        Jugadores guardados                        /");
        System.out.println("/                                                                   /");
        listAux.showAllList();
        sleep(1500);
        System.out.println("/                                                                   /");

        interfaceOpc2();
    }

    public void clearAllData() throws IOException, InterruptedException {
        documentManager dManagerAux = new documentManager();

        String file = "C:\\Users\\Eliezer Meza\\Desktop\\prueba.txt";

        dManagerAux.clearFile(file);

        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                                                                   /");
        System.out.println("/                          Eliminando data...                       /");
        System.out.println("/                                                                   /");
        sleep(1500);
        System.out.println("/                           Data eliminada                          /");
        System.out.println("/                                                                   /");
        sleep(1500);

        interfaceOpc2();
    }

    public void interfaceOpc0() throws InterruptedException {
        // game over
        printer.gameOver();

        finish = true;
    }

    public void interfaceOpcInvalid() {
        System.out.println("/                                                                   /");
        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                         OPCION NO VALIDA                          /");
        System.out.println("/-------------------------------------------------------------------/");
        System.out.println("/                                                                   /");
    }

}
