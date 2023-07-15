package com.example.ok;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Iterator;

    public class    Customers {
        //instance variables in the class
        private final String firstName;
        private final String lastName;
        private final int numberOfBurgers;
        private static final Map<String, String> dictionary = new HashMap<>();//store customer's name, position and amount of burgers.
        private static final ArrayList<String> waitingList = new ArrayList<>();
        public static int front = 0;
        private static final int[]total=new int[3]; //used to calculate total income



        //constructor
        public Customers(String firstName, String lastName, int numberOfBurgers) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.numberOfBurgers = numberOfBurgers;
            System.out.println("Added");
        }

        public String getFirstName() {

            return firstName;
        }

        public int getNumberOfBurgers() {

            return numberOfBurgers;
        }

        public static Customers obj() {   //Method to get the respective information needed for constructor to create an object
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Customer first name: ");
            String name = sc.nextLine();
            System.out.println("Enter Customer last name: ");
            String lastName = sc.nextLine();
            System.out.println("Enter number of burgers required: ");
            int burgers = sc.nextInt();
            return new Customers(name, lastName, burgers);
        }

        public static void add(char[][] customers) { //adding customers to the queue
            Customers person = obj();
            boolean personAdded = false;

            for (int i = 0; i < customers.length; i++) {
                char[] currentCashier = customers[i];
                for (int j = 0; j < currentCashier.length; j++) {
                    if (currentCashier[j] == 'X') {
                        currentCashier[j] = 'O'; //updating the customer cashier arrays
                        System.out.println(person.getFirstName() + " added to Cashier " + (i + 1) + "\n");
                        dictionary.put(person.getFirstName(), i + " " + j + " " + person.numberOfBurgers);
                        food.cashiers[i][j]= person.getFirstName();
                        personAdded = true;
                        break;  // Exit the inner loop
                    }
                }

                if (personAdded) {
                    break;  // Exit the outer loop if person is added
                }
            }

            if (!personAdded) { //Customers added to waiting list if queues are full

                waitingList.add(person.getFirstName());
                System.out.println(person.getFirstName() + " added to waiting list\n");
                String strNumber = Integer.toString(person.getNumberOfBurgers());
                dictionary.put(person.getFirstName(),strNumber);
            }
        }

        public static void remove(String name, String place) { //removing a customer from a specific place
            if (dictionary.containsKey(name)) {

                //Splits the string place by the "" and converts the parts of the place to integer.
                String[] parts = place.split(" ");
                int cashierIndex = Integer.parseInt(parts[0]);
                int position = Integer.parseInt(parts[1]);

                //Used the parts of place to find the location of customer and remove the customer.
                food.customers[cashierIndex][position] = 'X';
                System.out.println(name + " located at Cashier " + (cashierIndex + 1) + ", Position " + (position + 1) + " is now removed");
                dictionary.remove(name);

                enqueue(cashierIndex,position);//adding a customer from waiting list to the cashier


            } else {
                System.out.println(name + " not found in the dictionary.");
            }
        }

        public static void served(int cashier, char[][] customers) {
            String name = null;

            //getting the name of the first customer in the selected cashier and appointing the front variable to the customer next
            switch (cashier){
                case 1:
                    name = food.cashiers[0][front];
                    front = (front + 1) % food.cashiers[0].length;
                    break;
                case 2:
                    name = food.cashiers[1][front];
                    front = (front + 1) % food.cashiers[0].length;
                    break;
                case 3:
                    name = food.cashiers[2][front];
                    front = (front + 1) % food.cashiers[0].length;
                    break;

            }
            //Removing customer from queue
            if (dictionary.containsKey(name)) {
                String value = dictionary.get(name);
                String[] parts = value.split(" ");
                int cashierIndex = Integer.parseInt(parts[0]);
                int position = Integer.parseInt(parts[1]);
                int burgh = Integer.parseInt(parts[2]);

                customers[cashierIndex][position] = 'X';
                food.burghers = food.burghers - burgh; //updating burgher stock after served

                //initialize variables for getting income of each cashier
                int cashier1 = total[0];
                int cashier2 = total[1];
                int cashier3 = total[2];


                //calculating the income
                switch (cashierIndex) {
                    case 0:
                        cashier1 += 650*burgh;
                        total[0]=cashier1;
                        break;
                    case 1:
                        cashier2 += 650*burgh;
                        total[1]=cashier2;
                        break;
                    case 2:
                        cashier3 += 650*burgh;
                        total[2]=cashier3;
                        break;
                    default:
                        System.out.println("Error: Invalid cashier index");
                }


                System.out.println(name + " located at Cashier " + (cashierIndex + 1) + ", Position " + (position + 1) + " was served " + burgh + " burghers and is now removed");
                dictionary.remove(name);

                enqueue(cashierIndex,position);//adding customer from waiting list to cashier

            }
        }

        public static void enqueue(int cashierIndex,int position){ //method for adding customers from waiting queue
            Iterator<String> iterator = waitingList.iterator();
            while (iterator.hasNext()) {
                String newPerson = iterator.next();
                food.customers[cashierIndex][position] = 'O';
                System.out.println(newPerson + " added to " + (cashierIndex + 1) + "cashier");
                String currentValue = dictionary.get(newPerson);
                dictionary.put(newPerson, cashierIndex + " " + position + " " + currentValue);
                food.cashiers[cashierIndex][position] = newPerson;

                iterator.remove();
                break;
            }
        }
        public static void sum(){
            System.out.println("Total of cashier 1: "+total[0]);
            System.out.println("Total of cashier 2: "+total[1]);
            System.out.println("Total of cashier 3: "+total[2]);
        }
        public static void alphabet(){
            String[] customerNames = new String[dictionary.size()];
            int index = 0;
            for (String names : dictionary.keySet()) {
                customerNames[index] = names;
                index++;
            }

            for (int i = 0; i < customerNames.length; i++) {
                for (int j = i + 1; j < customerNames.length; j++) {
                    if (customerNames[i].compareTo(customerNames[j]) > 0) {
                        String temp = customerNames[i];
                        customerNames[i] = customerNames[j];
                        customerNames[j] = temp;
                    }
                }
            }

            for (String cn : customerNames) {
                System.out.println(cn);
            }


        }


        public static void store() {
            try {

                FileWriter fileWrite = new FileWriter("text.txt");
                fileWrite.write("Customers: their positions and lastly their amount of burghers ordered\n");

                //Iterating over the items in the dictionary converting them to strings and writing it in a text file.
                for (Map.Entry<String, String> entry : dictionary.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    fileWrite.write(key + ": " + value + "\n");

                }

                for (int i = 0; i < total.length; i++) {
                    String sum = Integer.toString(total[i]);
                    fileWrite.write("Sum of cashier"+i+": "+sum+"\n");

                }
                String burgh = Integer.toString(food.burghers);
                fileWrite.write("Total amount of burghers left in stock: " + burgh + "\nTotal number of customers in the cashiers: " + dictionary.size());
                fileWrite.close();


            } catch (IOException e) {
                System.out.println("Something went wrong "+e);
            }
        }
        public static void load_data() { //Displaying the information written in the text file to the user
            try {
                File file=new File("text.txt");
                Scanner rf = new Scanner(file);
                String fileLine;
                while (rf.hasNext()){
                    fileLine = rf.nextLine();
                    System.out.println( fileLine);
                }

            }
            catch (IOException e){
                System.out.println("error "+e);
            }
        }

    }


