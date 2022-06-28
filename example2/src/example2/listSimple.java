package example2;

import java.io.FileNotFoundException;
import java.io.IOException;

public class listSimple {

    private player begin;
    private player end;

    private documentManager dManager = new documentManager();

    public Integer dimesion = 0;

    public listSimple() {
        begin = null;
        end = null;
    }

    public boolean isEmpty() {
        return begin == null && end == null;
    }

    public void push(player _newPlayer) {

        if (_newPlayer.id != null) {

            _newPlayer.next = begin;
            begin = _newPlayer;

            if (end == null) {
                end = begin;
            }

            dimesion++;
        }

    }

    public player pop() {
        player aux = null;

        if (!isEmpty()) {
            if (begin == end) {
                aux = begin;
                begin = end = null;
            } else {
                aux = begin;
                if (begin.next == null) {
                    begin = null;
                } else {
                    begin = begin.next;

                }
            }

            dimesion--;
        }

        return aux;
    }

    public Integer getDimesion() {
        return dimesion;
    }

    public player deleteById(String _id) {
        player aux = begin;
        player before = begin;
        player cap = begin;

        while (aux != null) {

            if (aux.id == null ? _id == null : aux.id.equals(_id)) {
                cap = aux;

                if (aux == begin) {

                    if (begin == end) {
                        begin = end = null;
                    } else {
                        begin = begin.next;

                    }

                } else {
                    if (aux != end) {
                        before.next = aux.next;

                    }
                }

            } else {
            }

            before = aux;
            aux = aux.next;
        }

        dimesion--;
        return cap;

    }

    public void showAllList() {

        if (isEmpty() == false) {
            player aux = begin;

            while (aux != null) {
                System.out.println("/ Player " + aux.id + " | Nombre: " + aux.name + " " + aux.lastName + " | Edad: " + aux.age + " | Sexo: " + aux.sex + " | Index: " + aux.index + " /");

                aux = aux.next;
            }
        } else {
            System.out.println("/                    No hay jugadores guardados                     /");
        }

    }

    public void showResultsGame() {

        if (isEmpty() == false) {
            player aux = begin;

            while (aux != null) {
                System.out.println("/ Player " + aux.id + " | Nombre: " + aux.name + " " + aux.lastName + " | Edad: " + aux.age + " | Descripcion " + aux.description + " /");

                aux = aux.next;
            }
        }

    }

    public void saveAllList(String _file) throws FileNotFoundException {
        player aux = begin;
        String file = _file;

        dManager.clearFile(file);

        while (aux != null) {

            dManager.saveLine(aux, file);

            aux = aux.next;
        }
    }

    public void deleteAllList() {
        begin = null;
        end = null;
        this.dimesion = 0;

    }

    public void chargeList(int _circleSize) throws IOException {

        String file = "C:\\Users\\Eliezer Meza\\Desktop\\prueba.txt";

        listSimple chargePlayers = dManager.returnLines(file);

        while (chargePlayers.isEmpty() == false) {
            push(chargePlayers.pop());
        }

    }

    public player getRandom(Integer _min, Integer _max) {
        int randomNum;

        Integer min = _min;
        Integer max = _max;
        randomNum = (int) (Math.random() * (max - min)) + min;

        player aux = begin;

        for (int i = 0; i <= (randomNum - 1); i++) {
            aux = aux.next;
        }

        return aux;

    }
}
