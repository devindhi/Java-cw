package com.example.ok;

import java.util.Scanner;



public class main {
        public static void main(String[] args) {
            food myFood = new food();
            try {

                boolean isRunning = true;


                //looping the program till user exits
                while (isRunning) {
                    if (food.burghers == 10){
                        System.out.println("There are only 10 burghers left in stock");
                    }

                    //Printing the menu
                    System.out.println("\nFunctions" +
                            "\n100 or VFQ: View all Queues" +
                            "\n101 or VEQ: View all Empty Queues" +
                            "\n102 or ACQ: Add customer to a Queue" +
                            "\n103 or RCQ: Remove a customer from a Queue." +
                            "\n104 or PCQ: Remove a served customer" +
                            "\n105 or VCS: View Customers Sorted in alphabetical order" +
                            "\n106 or SPD: Stored Program Data from File" +
                            "\n107 or LPD: Load data" +
                            "\n108 or STK: View Remaining burgers Stock." +
                            "\n109 or AFS: Add burgers to Stock." +
                            "\n110 or IFQ: View sum of all the cashiers" +
                            "\n112:View GUI can only call it once"+
                            "\n999 or EXT: Exit the Program.");

                    Scanner input = new Scanner(System.in);

                    //displaying warning message if burgher stock reaches only 10 burghers
                    if (food.burghers == 10) {
                        System.out.println("Warning only 10 burghers left");
                    }

                    System.out.print("Enter your option: ");//getting the option
                    String function = input.nextLine();

                    //Switch case used to complete every option entered by the user
                    switch (function) {
                        case "100":
                        case "VFQ":
                            myFood.print(food.customers);
                            break;
                        case "101":
                        case "VFp":
                            myFood.empty();
                            break;

                        case "102":
                        case "ACQ":
                            Customers.add(food.customers);
                            break;

                        case "103":
                        case "RCQ":
                            Scanner in = new Scanner(System.in);
                            System.out.println("Enter name of customer");
                            String name_remove = in.nextLine();
                            System.out.println("Enter the cashier and position (cashier(0-2) - positions):\n0 - 0,1\n1 - 0,1,2\n2 - 0,1,2,3,4");
                            String place = in.nextLine();
                            Customers.remove(name_remove, place);

                            break;

                        case "104":
                        case "PCQ":
                            Scanner inp = new Scanner(System.in);
                            System.out.println("Enter cashier (1 , 2 or 3): ");
                            int name_serve = inp.nextInt();
                            Customers.served(name_serve,food.customers);

                            break;

                        case "105":
                        case "VCS":
                            Customers.alphabet();
                            break;
                        case "106":
                        case "SPD":
                            Customers.store();
                            System.out.println("Data is stored in a text file");
                            break;

                        case "107":
                        case "LPD":
                            Customers.load_data();
                            break;

                        case "108":
                        case "STK":
                            System.out.println(food.burghers + " burghers are there in stock");
                            break;
                        case "109":
                        case "AFS":
                            int add = 50 - food.burghers;
                            food.burghers = add + food.burghers;
                            System.out.println("Burghers are restocked.");
                            break;

                        case "110":
                        case "ifq":
                            Customers.sum();
                            break;

                        case "112":
                        case "GUI":
                            HelloApplication.launch(HelloApplication.class, args);
                            break;


                        case "999":
                        case "EXT":
                            isRunning = false;
                            System.out.println("Bye.");
                            break;

                        default:
                            System.out.println("Invalid option.");
                            break;
                    }
                }
            } catch (Exception e) {
                System.out.println("An error occurred retry.");

            }
        }
    }



