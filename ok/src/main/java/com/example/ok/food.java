package com.example.ok;

public class food {
    public static char[][] customers = {
            {'X','X'},
            {'X','X','X'},
            {'X','X','X','X','X'}
    };
    public static  int burghers = 50;
    public static String [][] cashiers ={new String [2],new String [3],new String [5]};

    public static void print(char[][] customers) {
        System.out.println(" *****************\n * Cashier *\n *****************");

        int maxLength = 0;
        for (char[] subArray : customers) {
            if (subArray.length > maxLength) {
                maxLength = subArray.length;
            }
        }
        // Printing cashiers
        for (int row = 0; row < maxLength; row++) {
            for (int col = 0; col < customers.length; col++) {
                if (row < customers[col].length) {
                    System.out.print(customers[col][row] + "\t\t");
                } else {
                    System.out.print("\t\t");
                }
            }
            System.out.println();
        }
        System.out.println("X = empty    O = occupied");
    }
    public static void empty() { //displaying cashiers with empty spaces

        for (int i = 0; i < customers.length; i++) {
            char[] currentCashier = customers[i];
            for (int j = 0; j < currentCashier.length; j++) {
                if (currentCashier[j] == 'X') {
                    System.out.println("Cashier " + (i + 1) + " available");
                    break;
                }
            }
        }
    }


}


