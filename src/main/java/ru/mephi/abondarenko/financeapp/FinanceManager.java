package ru.mephi.abondarenko.financeapp;

import ru.mephi.abondarenko.financeapp.user.User;
import ru.mephi.abondarenko.financeapp.utils.Budget;
import ru.mephi.abondarenko.financeapp.utils.DataStorage;
import ru.mephi.abondarenko.financeapp.utils.Transaction;
import ru.mephi.abondarenko.financeapp.wallet.Wallet;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FinanceManager {
	private Map<String, User> users;
	private User currentUser;

	public FinanceManager() {
		this.users = DataStorage.loadData();
		if (this.users == null) {
			this.users = new HashMap<>();
		}
	}

	public Map<String, User> getUsers() {
		return users;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void exit() {
		DataStorage.saveData(users);
		System.out.println("Exiting application.");
	}

	public void registerUser(String username, String password) {
		if (username == null || username.trim().isEmpty()) {
			throw new IllegalArgumentException("Username cannot be empty.");
		}

		if (password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException("Password cannot be empty.");
		}

		if (users.containsKey(username)) {
			throw new IllegalArgumentException("Username already exists.");
		}

		users.put(username, new User(username, password));
		System.out.println("User registered successfully.");
	}

	public boolean loginUser(String username, String password) {
		if (username == null || username.trim().isEmpty()) {
			throw new IllegalArgumentException("Username cannot be empty.");
		}

		if (password == null || password.trim().isEmpty()) {
			throw new IllegalArgumentException("Password cannot be empty.");
		}

		User user = users.get(username);
		if (user == null || !user.checkPassword(password)) {
			throw new IllegalArgumentException("Invalid username or password.");
		}

		currentUser = user;
		System.out.println("Login successful.");
		return true;
	}

	public void addIncome(String category, double amount) {
		validateUserLoggedIn();
		validateCategory(category);
		validateAmount(amount);

		currentUser.getWallet().addIncome(category, amount);
		System.out.println("Income added successfully.");
	}

	public void addExpense(String category, double amount) {
		validateUserLoggedIn();
		validateCategory(category);
		validateAmount(amount);

		currentUser.getWallet().addExpense(category, amount);
		System.out.println("Expense added successfully.");
	}

	public void setBudget(String category, double limit) {
		validateUserLoggedIn();
		validateCategory(category);
		validateAmount(limit);

		currentUser.getWallet().setBudget(category, limit);
		System.out.println("Budget set successfully.");
	}

	public void showStatistics() {
		validateUserLoggedIn();

		Wallet wallet = currentUser.getWallet();

		double totalIncome = calculateTotalIncome(wallet);
		double totalExpenses = calculateTotalExpenses(wallet);

		System.out.printf("Total income: %.1f%n", totalIncome);
		printIncomeByCategory(wallet);
		System.out.printf("Total expenses: %.1f%n", totalExpenses);
		printBudgetByCategory(wallet);
		printTransactions(wallet);
	}

	public void transfer(String recipientUsername, double amount) {
		validateUserLoggedIn();
		validateAmount(amount);

		if (recipientUsername == null || recipientUsername.trim().isEmpty()) {
			throw new IllegalArgumentException("Recipient username cannot be empty.");
		}

		User recipient = users.get(recipientUsername);
		if (recipient == null) {
			throw new IllegalArgumentException("Recipient not found.");
		}

		if (currentUser.getWallet().getBalance() < amount) {
			throw new IllegalStateException("Insufficient funds.");
		}

		currentUser.getWallet().addExpense("Transfer to " + recipientUsername, amount);
		recipient.getWallet().addIncome("Transfer from " + currentUser.getUsername(), amount);
		System.out.println("Transfer successful.");
	}

	private void validateUserLoggedIn() {
		if (currentUser == null) {
			throw new IllegalStateException("No user logged in.");
		}
	}

	private void validateCategory(String category) {
		if (category == null || category.trim().isEmpty()) {
			throw new IllegalArgumentException("Category cannot be empty.");
		}
	}

	private void validateAmount(double amount) {
		if (Double.isNaN(amount) || amount <= 0) {
			throw new IllegalArgumentException("Amount must be a positive number.");
		}
	}

	private double calculateTotalIncome(Wallet wallet) {
		return wallet.getTransactions().stream()
				.filter(Transaction::isIncome)
				.mapToDouble(Transaction::getAmount)
				.sum();
	}

	private double calculateTotalExpenses(Wallet wallet) {
		return wallet.getTransactions().stream()
				.filter(t -> !t.isIncome())
				.mapToDouble(Transaction::getAmount)
				.sum();
	}

	private void printIncomeByCategory(Wallet wallet) {
		Map<String, Double> incomeByCategory = wallet.getTransactions().stream()
				.filter(Transaction::isIncome)
				.collect(Collectors.groupingBy(
						Transaction::getCategory,
						Collectors.summingDouble(Transaction::getAmount)
				));
		System.out.println("Income by category:");
		incomeByCategory.forEach((category, amount) ->
				System.out.printf("%s: %.1f%n", category, amount)
		);
	}

	private void printBudgetByCategory(Wallet wallet) {
		System.out.println("Budget by category:");
		wallet.getBudgets().forEach((category, budget) -> {
			double expensesForCategory = calculateExpensesForCategory(wallet, category);
			double remainingBudget = budget.getLimit() - expensesForCategory;
			System.out.printf("%s: %.1f, Remaining budget: %.1f%n", category, budget.getLimit(), remainingBudget);
		});
	}

	private double calculateExpensesForCategory(Wallet wallet, String category) {
		return wallet.getTransactions().stream()
				.filter(t -> !t.isIncome() && t.getCategory().equals(category))
				.mapToDouble(Transaction::getAmount)
				.sum();
	}

	private void printTransactions(Wallet wallet) {
		System.out.println("\nTransactions:");
		wallet.getTransactions().forEach(transaction ->
				System.out.println(transaction.getCategory() + ": " + transaction.getAmount() + " (" + (transaction.isIncome() ? "Income" : "Expense") + ")")
		);
	}
}