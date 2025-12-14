import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;

import java.io.PrintWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileNotFoundException;

//Java overview, JVM, OOP concepts
//Variables, types, input/output
//Control flow: if, switch, loops
//Exceptions (intro), debugging
//Methods, parameters, blocks, scope
//Arrays & ArrayLists
//Objects & classes
//Inheritance & polymorphism
//GUI basics with JavaFX
//Exceptions (advanced), robustness
//Files & multithreading
//Coding standards & style


public class MyBudgetTracker {

    // Arrays & ArrayLists
    static ArrayList<Transaction> transactions = new ArrayList<>();
    // Variables & types
    static double balance = 0.0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Input/Output
        int choice = 0;

        // Multithreading
        Thread clockThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(5000);
                    System.out.println("[clock thread] Tick: " + new Date());
                } catch (InterruptedException e) {
                    // simple debugging output
                    System.out.println("[clock thread] interrupted");
                }
            }
        });
        clockThread.setDaemon(true);
        clockThread.start();

        // Main loop
        while (choice != 5) {
            System.out.println("\n===== MyBudgetTracker =====");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. Save to file");
            System.out.println("4. Load from file");
            System.out.println("5. Exit");
            System.out.print("Choose: ");

            String line = in.nextLine();
            // Exceptions & debugging
            try {
                choice = Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number 1-5.");
                continue;
            }

            switch (choice) { // switch control flow
                case 1:
                    addTransaction(in);
                    break;
                case 2:
                    viewTransactions();
                    break;
                case 3:
                    saveToFile();
                    break;
                case 4:
                    loadFromFile();
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        in.close();
    }

    // Methods, parameters, blocks, scope
    public static void addTransaction(Scanner in) {
        try {
            System.out.print("Name: ");
            String name = in.nextLine();

            System.out.print("Amount (positive number): ");
            double amount = Double.parseDouble(in.nextLine());

            System.out.print("Type (income / spend): ");
            String type = in.nextLine().trim().toLowerCase();

            System.out.print("Category (e.g., Food, Bills, Work): ");
            String cat = in.nextLine();

            Transaction t;
            if (type.equals("income")) { // control flow: if
                t = new IncomeTransaction(name, amount, cat);
                balance += amount;
            } else {
                t = new SpendingTransaction(name, amount, cat);
                balance -= amount;
            }

            transactions.add(t);
            System.out.println("Added: " + t);
        } catch (NumberFormatException e) {
            // Exceptions
            System.out.println("Invalid number for amount. Try again.");
        } catch (Exception e) {
            // general debugging
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void viewTransactions() {
        System.out.println("\n--- Transactions ---");
        // Loop through arraylist
        if (transactions.isEmpty()) {
            System.out.println("(no transactions yet)");
        } else {
            for (int i = 0; i < transactions.size(); i++) {
                System.out.println((i + 1) + ". " + transactions.get(i));
            }
        }
        System.out.printf("Balance: $%.2f%n", balance);
    }

    // Files & Exceptions
    public static void saveToFile() {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter("budget.txt"));
            out.println(balance); // first line is the balance
            for (Transaction t : transactions) {

                String kind = (t instanceof IncomeTransaction) ? "INCOME" : "SPEND";
                out.println(kind + "," + t.name + "," + t.amount + "," + t.category);
            }
            System.out.println("Saved to budget.txt");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        } finally {

            if (out != null) {
                out.close();
            }
        }
    }

    // Files & Exceptions
    public static void loadFromFile() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("budget.txt"));
            String first = br.readLine();
            if (first == null) {
                System.out.println("File is empty.");
                return;
            }

            balance = Double.parseDouble(first);

            transactions.clear();
            String line;
            while ((line = br.readLine()) != null) {
                // Exceptions: possible malformed lines
                try {
                    String[] parts = line.split(",", -1); // keep empty strings if any
                    if (parts.length < 4) {
                        // skip bad line
                        System.out.println("Skipping bad line: " + line);
                        continue;
                    }
                    String kind = parts[0];
                    String name = parts[1];
                    double amt = Double.parseDouble(parts[2]);
                    String cat = parts[3];

                    Transaction t;
                    if (kind.equals("INCOME")) {
                        t = new IncomeTransaction(name, amt, cat);
                    } else {
                        t = new SpendingTransaction(name, amt, cat);
                    }
                    transactions.add(t);

                } catch (Exception ex) {
                    System.out.println("Error reading a line, skipping: " + ex.getMessage());
                }
            }
            System.out.println("Loaded from budget.txt");
        } catch (FileNotFoundException e) {
            System.out.println("No saved file found (budget.txt).");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Saved file had bad number format.");
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // ignore close error
                }
            }
        }
    }
}