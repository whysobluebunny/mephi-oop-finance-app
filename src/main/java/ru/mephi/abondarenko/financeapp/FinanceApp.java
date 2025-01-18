package ru.mephi.abondarenko.financeapp;

import java.util.Scanner;

public class FinanceApp {

	public static void main(String[] args) {
		FinanceManager manager = new FinanceManager();
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit):");
			String command = scanner.nextLine();

			switch (command) {
				case "register" -> {
					System.out.println("Enter username:");
					String username = scanner.nextLine();
					System.out.println("Enter password:");
					String password = scanner.nextLine();
					manager.registerUser(username, password);
				}
				case "login" -> {
					System.out.println("Enter username:");
					String loginUsername = scanner.nextLine();
					System.out.println("Enter password:");
					String loginPassword = scanner.nextLine();
					manager.loginUser(loginUsername, loginPassword);
				}
				case "addIncome" -> {
					System.out.println("Enter category:");
					String incomeCategory = scanner.nextLine();
					System.out.println("Enter amount:");
					double incomeAmount = Double.parseDouble(scanner.nextLine());
					manager.addIncome(incomeCategory, incomeAmount);
				}
				case "addExpense" -> {
					System.out.println("Enter category:");
					String expenseCategory = scanner.nextLine();
					System.out.println("Enter amount:");
					double expenseAmount = Double.parseDouble(scanner.nextLine());
					manager.addExpense(expenseCategory, expenseAmount);
				}
				case "setBudget" -> {
					System.out.println("Enter category:");
					String budgetCategory = scanner.nextLine();
					System.out.println("Enter limit:");
					double budgetLimit = Double.parseDouble(scanner.nextLine());
					manager.setBudget(budgetCategory, budgetLimit);
				}
				case "showStatistics" -> manager.showStatistics();
				case "transfer" -> {
					System.out.println("Enter recipient username:");
					String recipientUsername = scanner.nextLine();
					System.out.println("Enter amount:");
					double transferAmount = Double.parseDouble(scanner.nextLine());
					manager.transfer(recipientUsername, transferAmount);
				}
				case "exit" -> {
					manager.exit();
					running = false;
				}
				default -> System.out.println("Unknown command.");
			}
		}

		scanner.close();
	}
}
