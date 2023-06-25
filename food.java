public class food {
    static char[][] customers = {
            {'O','O'},
            {'O','O','O'},
            {'O','O','O','O','O'}
    };
    static  int burghers = 50;

    public static void print(char[][] customers) {
        System.out.println(" ********** \n * Cashier *\n **********");

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
                    System.out.print(customers[col][row] + "\t");
                } else {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
    public static void empty() {
        for (int i = 0; i < customers.length; i++) {
            char[] currentCashier = customers[i];
            for (int j = 0; j < currentCashier.length; j++) {
                if (currentCashier[j] == 'O') {
                    System.out.println("Cashier " + (i + 1) + " available");
                    break;
                }
            }
        }
    }


}
