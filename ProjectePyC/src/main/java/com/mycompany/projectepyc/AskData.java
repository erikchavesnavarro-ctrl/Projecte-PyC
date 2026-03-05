package com.mycompany.pyc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author mfontana
 */
public class AskData {
    private BufferedReader br;

    public AskData() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }
    
    public int askInt(String message) throws IOException {
        System.out.print(message);
        int n = Integer.parseInt(br.readLine());
        return n;
    }
    
    public int askInt(String message, String errorMessage, int min) throws IOException {
        int n;
        do {
            n = askInt(message);
            if (n < min) {
                System.out.println(errorMessage);
            }
        } while (n < min);
        return n;
    }
    
    public double askDouble(String message) throws IOException {
        System.out.print(message);
        double n = Double.parseDouble(br.readLine());
        return n;
    }
    
    public double askDouble(String message, String errorMessage, int min, int max) throws IOException {
        double n;
        do {
            n = askDouble(message);
            if (n < min || n > max) {
                System.out.print(errorMessage);
            }
        } while (n < min || n > max);
        return n;
    }
    
    public String askString(String message) throws IOException {
        String ask = "";
        do {
            System.out.print(message);
            ask = br.readLine();
            if (ask.isBlank()) {
                System.out.println("No es pot deixar en blanc.");
            }
        } while (ask.isBlank());
        return ask;
    }
    public double askDouble(String message, String errorMessage, double min, double max) throws IOException {
        double n;
        do {
            n = askDouble(message);
            if (n < min || n > max) {
                System.out.print(errorMessage);
            }
        } while (n < min || n > max);
        return n;
    }
}
