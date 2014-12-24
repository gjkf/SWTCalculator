/**
 * Copyright (c) 23/12/14 Davide Cossu.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or (at your option) any
 * later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses>.
 */

package com.gjkf.calc.core;

import com.gjkf.calc.Main;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Scanner;

public class ColorHelper{

    /**
     * Returns a color array from the file
     */

    public static Color[] getColors(Display display){

        InputStream inputStream = Main.class.getResourceAsStream("/colors/Colors.txt");

        Scanner scanner = new Scanner(inputStream);

        Color[] colorArray = new Color[8];

        int lineCounter = 0;

        while(scanner.hasNextLine()){

            String line = scanner.nextLine();

            String[] splitArray;

//            System.out.println("Line: " + line);

            splitArray = line.split(",");

            for(int i = 0; i < splitArray.length; i++){

                if(i >= 0)
                    splitArray[i] = splitArray[i].substring(1);

                if(i == splitArray.length-1){
                    splitArray[i] = splitArray[i].substring(0, splitArray[i].length() - 1);
                }

//                System.out.println(splitArray[i]);

            }

            RGB rgb = new RGB(Integer.parseInt(splitArray[0]), Integer.parseInt(splitArray[1]), Integer.parseInt(splitArray[2]));
            colorArray[lineCounter] = new Color(display, rgb);

//            System.err.println(colorArray[lineCounter]);

            lineCounter++;

        }

        return colorArray;

    }

    /**
     * Writes on a file the colors, so that if the application is closed it won't lose the settings
     *
     * @param colors The array that needs to be written
     */

    public static void setColors(Color[] colors){

        File file = null;

        try{
            file = new File(Main.class.getResource("/colors/Colors.txt").toURI());
            System.out.println(file);
        }catch(URISyntaxException e){
            e.printStackTrace();
        }

        for(Color color : colors){

            BufferedWriter writer = null;
            try{
                if(file != null){
                    writer = new BufferedWriter(new FileWriter(file, true));
                    writer.append(color.toString().substring(6) + "\n");
                    writer.flush();
                    writer.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }

        }

    }

    /**
     * Clears the file so it won't write too many lines
     */

    public static void clearFile(){

        File file = null;

        try{
            file = new File(Main.class.getResource("/colors/Colors.txt").toURI());
        }catch(URISyntaxException e){
            e.printStackTrace();
        }

        BufferedWriter writer = null;
        try{
            writer = new BufferedWriter(new FileWriter(file, false));
            writer.write("");
            writer.flush();
            writer.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }

}
