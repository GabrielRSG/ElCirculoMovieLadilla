package example2;

import java.io.*;
import example2.player;

public class documentManager {

    File file = null;
    BufferedReader br = null;
    FileReader fr = null;


    public void saveLine(player _player, String _fileName) throws FileNotFoundException {

        String id = _player.id;
        String name = _player.name;
        String lName = _player.lastName;
        String age = _player.age;
        String sex = _player.sex;

        String line
                = id + '|'
                + name + '|'
                + lName + '|'
                + age + '|'
                + sex + '|';

        FileWriter file = null;
        PrintWriter pw = null;

        try {
            file = new FileWriter(_fileName, true);
            pw = new PrintWriter(file);

            pw.println(line);

        } catch (IOException e) {
        } finally {
            try {
                if (null != file) {
                    file.close();
                }
            } catch (IOException e2) {
            }
        }
    }

    public listSimple returnLines(String _fileName) throws IOException {

        listSimple aux = new listSimple();

        try {
            file = new File(_fileName);
            fr = new FileReader(file);
            br = new BufferedReader(fr);

            String line;
            while ((line = br.readLine()) != null) {
                aux.push(formatPlayers(line));
            }

        } catch (FileNotFoundException e) {
        }

        return aux;
    }
    
    private player formatPlayers(String _line) {

        String data[] = new String[100];

        int aSubsString = 0;
        int bSubsString = 0;

        int index = 0;

        for (char c : _line.toCharArray()) {
            if (c == '|') {

                data[index] = _line.substring(aSubsString, bSubsString);
                aSubsString = bSubsString + 1;

                index++;
            }

            bSubsString++;
        }

        player aux = new player(data[0], 0, data[1], data[2], data[3], data[4]);

        return aux;
    }

    public void clearFile(String _fileName){
        FileWriter file = null;
        PrintWriter pw = null;

        try {
            file = new FileWriter(_fileName);
            pw = new PrintWriter(file);

            pw.println("");

        } catch (IOException e) {
        } finally {
            try {
                if (null != file) {
                    file.close();
                }
            } catch (IOException e2) {
            }
        }
        
    }

}
