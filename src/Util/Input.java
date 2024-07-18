package Util;

import java.util.Scanner;

public class Input {
    public static int getInt(int min, int max) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (Exception e) {
                System.out.printf("Value must be an integer between %d and %d. Try again: ", min, max);
            }
        }
    }
}
