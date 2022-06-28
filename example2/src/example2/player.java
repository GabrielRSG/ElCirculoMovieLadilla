package example2;

import java.util.Random;

public class player {

    public player next;

    public String id = "";
    public Integer index = 0;

    public String name = "";
    public String lastName = "";
    public String age = "";
    public String sex = "";

    public String description = "";
    public String desition = "";

    public player(
            String _id,
            Integer _index,
            String _name,
            String _lastName,
            String _age,
            String _sex) {
        this.id = _id;
        this.index = _index;
        this.name = _name;
        this.lastName = _lastName;
        this.age = _age;
        this.sex = _sex;
    }

    public player(
            player _player,
            String _id,
            Integer _index,
            String _name,
            String _lastName,
            String _age,
            String _sex) {
        this.next = _player;
        this.id = _id;
        this.index = _index;
        this.name = _name;
        this.lastName = _lastName;
        this.age = _age;
        this.sex = _sex;
    }

    public void play() {
        Random rand = new Random();
        Integer min;
        Integer max;
        int opc;

        min = 1;
        max = 3;
        opc = rand.nextInt((max - min) + 1) + min;

        switch (opc) {
            case 1 -> {
                desition = "pass";
            }
            case 2 -> {
                desition = "exit";
            }
            case 3 -> {
                desition = "vote";
            }
        }
    }

    
    /*
    public String vote(listSimple _list, Integer _max) {
        player votedPlayer = _list.getRandom(0, _max);

        //System.out.println("/ El jugador " + name + " " + lastName + " | " + id + " ha votado por " + votedPlayer.name + " " + votedPlayer.lastName + " | " + votedPlayer.id);
        
        var auxId = votedPlayer.id;
        
        return auxId;
    }
    */

}
