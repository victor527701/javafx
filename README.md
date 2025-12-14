# Overview of my project
I made a budget/money tracker that finances your money. It starts off by giving you 5 options:

<img width="235" height="149" alt="image" src="https://github.com/user-attachments/assets/917aa82d-82b5-422c-8d8e-e52547523132" />

Option 1 is adding transactions. When you add a transaction, it asks the purpose of the transaction, how much is being transacted, and weather it is for income or spending. Then, it saves that information. You can then view the transaction when you press 2, and should look like this.

<img width="384" height="317" alt="image" src="https://github.com/user-attachments/assets/1d03f6d9-94fc-441f-90af-e2abdaf3cb1c" />


Option 3 gives you the choice to save the information to a file named budget.txt. When you do this, all the information of your banking/tansactions will be saved to budget.txt. Option 4 loads the information from the file. Option 5 simply exits the program. All of this is ran through the console.

# Criteria in code


# 1. Java overview, JVM, OOP concepts:
The program is ran inside of the Java Virtual Machine or JVM when main() starts.
The program also utilizes classes and objects, which is a core concept of OOP.

# 2. Variables, types, input/output:
Here are some of instinces of variables being used

double balance = 0.0;
int choice;
String name;
Scanner in = new Scanner(System.in);

# 3. Control flow: if, switch, loops:
The program uses multiple control flows. An example of this was when an if statement was used to check weather the user wants to add or take money from their bank.

if (type.equals("income")) {
    balance += amount;
} else {
    balance -= amount;
}

The program is also ran on a while loop, the condition being choice != 5

while (choice != 5) { }
for (Transaction t : transactions) { }

# 4. Exceptions (intro), debugging:
There was multiple instances of debugging.
