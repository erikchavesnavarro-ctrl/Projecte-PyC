package com.mycompany.projectepyc.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
        boolean error = false;
        int n = 0;
        do {
            try {
                System.out.print(message);
                n = Integer.parseInt(br.readLine());
                error = false;
            } catch (NumberFormatException ex) {
                error = true;
                System.out.println("Debes poner un número entero.");
            }
        } while (error);
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
    
    public int askInt(String message, String errorMessage, int min, int max) throws IOException {
        int n;
        do {
            n = askInt(message);
            if (n < min || n > max) {
                System.out.println(errorMessage);
            }
        } while (n < min || n > max);
        return n;
    }
    
    public int askInt(String message, String errorMessage, ArrayList<Integer> numsAccepted) throws IOException {
        int n;
        do {
            n = askInt(message);
            if (!numsAccepted.contains(n)) {
                System.out.println(errorMessage);
            }
        } while (!numsAccepted.contains(n));
        return n;
    }
    
    public double askDouble(String message) throws IOException {
        boolean error = false;
        double n = 0;
        do {
            try {
            System.out.print(message);
            n = Double.parseDouble(br.readLine());
            error = false;
            } catch (NumberFormatException ex) {
                error = true;
                System.out.println("Debes poner un número (puede ser con decimales).");
            }
        } while (error);
        return n;
    }
    
    public double askDouble(String message, String errorMessage, double min) throws IOException {
        double n;
        do {
            n = askDouble(message);
            if (n < min) {
                System.out.print(errorMessage);
            }
        } while (n < min);
        return n;
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
    
    public String askString(String message, String errorMessage, String option1, String option2) throws IOException {
        String ask;
        do {
            ask = askString(message);
            if (!ask.equalsIgnoreCase(option1) && !ask.equalsIgnoreCase(option2)) {
                System.out.println(errorMessage);
            }
        } while (!ask.equalsIgnoreCase(option1) && !ask.equalsIgnoreCase(option2));
        return ask;
    }
    
    public String askString(String message, List<String> wordsAccepted) throws IOException {
        String ask;
        do {
            ask = askString(message).toUpperCase();
            if (!wordsAccepted.contains(ask)) {
                System.out.println("Wrong answer. Words Accepted: ");
                for (String w : wordsAccepted) {
                    System.out.print(w + " ");
                }
                System.out.println();
            }
        } while (!wordsAccepted.contains(ask));
        return ask;
    }
    
    public boolean askBoolean(String message, String errorMessage, String optionTrue, String optionFalse) throws IOException {
        String ask;
        do {
            ask = askString(message);
            if (!ask.equalsIgnoreCase(optionTrue) && !ask.equalsIgnoreCase(optionFalse)) {
                System.out.println(errorMessage);
            }
        } while (!ask.equalsIgnoreCase(optionTrue) && !ask.equalsIgnoreCase(optionFalse));
        return ask.equalsIgnoreCase(optionTrue);
    }

}