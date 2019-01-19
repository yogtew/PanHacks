package graphics;

import com.opencsv.CSVReader;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Maze{

    public static ArrayList<Wall> generateGrid() {
        String csvFile = "main/src/graphics/maze4.csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            ArrayList<Wall> walls = new ArrayList<>();
            String[] line;
            for(int y = 0; y < 100; y++) {
                line = reader.readNext();
                for(int i = 0; i < 150; i++) {

                    int x = i;
                    Wall sqr;
                    if(line[i].equals(String.valueOf(2))) {
                        sqr = new Wall(x * 8, y * 8, 8, 8, Color.black);
                        walls.add(sqr);
                    }

                }
            }
            return walls;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
