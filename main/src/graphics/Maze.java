package graphics;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import graphics.Square.BlackSquare;

import javax.swing.*;

public class Maze{

    public static void main(String[] args) {
        String csvFile = "main/src/graphics/maze4.csv";

        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                for(int i = 0; i < 150; i++) {

                    int y = i / 150;
                    int x = i - (150 * y);

                    if(line[i] == String.valueOf(2)) {
                        Square sqr = new BlackSquare(x, y, 32, 32);
                        JFrame frame = new JFrame("Grid");
                        frame.add(sqr);
                        frame.setVisible(true);

                    } else {
                        Square sqr = new Square(x, y, 32, 32);
                        JFrame frame = new JFrame("Grid");
                        frame.add(sqr);
                        frame.setVisible(true);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
