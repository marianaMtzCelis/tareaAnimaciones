/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package videogame;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author antoniomejorado
 */
public class ReadandWrite {
    public static void Save(String strFileName) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(strFileName));
            int vidas = 5;
            int score = 20;
            writer.println("" + vidas + "/" + score);
            writer.close();
        } catch (IOException ioe) {
            System.out.println("File Not found CALL 911");
        }

    }

    public static void Load(String strFileName) {
        try {
            FileReader file = new FileReader(strFileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            String datos[];
            line = reader.readLine();
            datos = line.split("/");
            int vidas = Integer.parseInt(datos[0]);
            int score = Integer.parseInt(datos[1]);
            System.out.println("Se leyo  vidas = "+ vidas + " y score = " + score);
            reader.close();
        } catch (IOException e) {
            System.out.println("File Not found CALL 911");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Save("Progress.txt");
        Load("Progress.txt");
    }

}