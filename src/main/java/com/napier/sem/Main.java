package com.napier.sem;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        menuMain();

    }
// TO DO -- ADD COMMENTS

    public static void menuMain(){

        //Set up an integer to save the users choice from main menu
        int userChoice;
        Scanner userinput = new Scanner(System.in);
        while(true) {
            System.out.println("Choose What Menu You Would Like To See\n");
            System.out.println("1. View Country Reports");
            System.out.println("2. View City Reports");
            System.out.println("3. View Capital City Reports");
            System.out.println("4. View Population Statistics");
            System.out.println("5. View Language Speaker Reports");
            System.out.println("6. Exit");
            System.out.print("Pick a Number: ");

            userChoice = userinput.nextInt();

            switch (userChoice) {
                case 1:
                    System.out.println("YOU PICKED 1");
                    break;
                case 2:
                    System.out.println("YOU PICKED 2");
                    break;
                case 3:
                    System.out.println("YOU PICKED 3");
                    break;
                case 4:
                    System.out.println("YOU PICKED 4");
                    break;
                case 5:
                    System.out.println("YOU PICKED 5");
                    break;
                case 6:
                    System.out.println("YOU PICKED 6 To EXIT");
                    userinput.close();
                    return;
                default:
                    System.out.println("Invalid Choice Try Again");

            }
        }


    }


}

