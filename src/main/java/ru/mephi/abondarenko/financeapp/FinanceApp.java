package ru.mephi.abondarenko.financeapp;

import java.util.Scanner;

public class FinanceApp {
	private static final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		FinanceManager manager = new FinanceManager();
		boolean running = true;

		while (running) {
			System.out.println("Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit):");
			String command = scanner.nextLine();

			try {
				switch (command) {
					case "register" -> handleRegister(manager);
					case "login" -> handleLogin(manager);
					case "addIncome" -> handleAddIncome(manager);
					case "addExpense" -> handleAddExpense(manager);
					case "setBudget" -> handleSetBudget(manager);
					case "showStatistics" -> manager.showStatistics();
					case "transfer" -> handleTransfer(manager);
					case "exit" -> {
						manager.exit();
						running = false;
					}
					default -> System.out.println("Unknown command.");
				}
			} catch (IllegalArgumentException | IllegalStateException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

		scanner.close();
	}

	private static void handleRegister(FinanceManager manager) {
		String username = readString("Enter username: ");
		String password = readString("Enter password: ");
		manager.registerUser(username, password);
	}

	private static void handleLogin(FinanceManager manager) {
		String username = readString("Enter username: ");
		String password = readString("Enter password: ");
		manager.loginUser(username, password);
	}

	private static void handleAddIncome(FinanceManager manager) {
		String category = readString("Enter category: ");
		double amount = readDouble("Enter amount: ");
		manager.addIncome(category, amount);
	}

	private static void handleAddExpense(FinanceManager manager) {
		String category = readString("Enter category: ");
		double amount = readDouble("Enter amount: ");
		manager.addExpense(category, amount);
	}

	private static void handleSetBudget(FinanceManager manager) {
		String category = readString("Enter category: ");
		double limit = readDouble("Enter limit: ");
		manager.setBudget(category, limit);
	}

	private static void handleTransfer(FinanceManager manager) {
		String recipientUsername = readString("Enter recipient username: ");
		double amount = readDouble("Enter amount: ");
		manager.transfer(recipientUsername, amount);
	}

	private static String readString(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println("Input cannot be empty.");
				continue;
			}
			return input;
		}
	}

	private static double readDouble(String prompt) {
		while (true) {
			System.out.print(prompt);
			String input = scanner.nextLine().trim();
			try {
				double value = Double.parseDouble(input);
				if (value <= 0) {
					System.out.println("Amount must be a positive number.");
					continue;
				}
				return value;
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}
	}
}