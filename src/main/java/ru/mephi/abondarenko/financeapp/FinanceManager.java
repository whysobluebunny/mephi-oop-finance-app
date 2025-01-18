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
		if (!users.containsKey(username)) {
			users.put(username, new User(username, password));
			System.out.println("User registered successfully.");
		} else {
			System.out.println("Username already exists.");
		}
	}

	public boolean loginUser(String username, String password) {
		User user = users.get(username);
		if (user != null && user.checkPassword(password)) {
			currentUser = user;
			System.out.println("Login successful.");
			return true;
		} else {
			System.out.println("Invalid username or password.");
			return false;
		}
	}

	public void addIncome(String category, double amount) {
		if (currentUser != null) {
			currentUser.getWallet().addIncome(category, amount);
			System.out.println("Income added successfully.");
		} else {
			System.out.println("No user logged in.");
		}
	}

	public void addExpense(String category, double amount) {
		if (currentUser != null) {
			currentUser.getWallet().addExpense(category, amount);
			System.out.println("Expense added successfully.");
		} else {
			System.out.println("No user logged in.");
		}
	}

	public void setBudget(String category, double limit) {
		if (currentUser != null) {
			currentUser.getWallet().setBudget(category, limit);
			System.out.println("Budget set successfully.");
		} else {
			System.out.println("No user logged in.");
		}
	}


	public void showStatistics() {
		if (currentUser == null) {
			System.out.println("No user logged in.");
			return;
		}

		Wallet wallet = currentUser.getWallet();

		double totalIncome = wallet.getTransactions().stream()
				.filter(Transaction::isIncome)
				.mapToDouble(Transaction::getAmount)
				.sum();
		System.out.printf("Total income: %.1f%n", totalIncome);

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

		double totalExpenses = wallet.getTransactions().stream()
				.filter(t -> !t.isIncome())
				.mapToDouble(Transaction::getAmount)
				.sum();
		System.out.printf("Total expenses: %.1f%n", totalExpenses);

		System.out.println("Budget by category:");
		for (Map.Entry<String, Budget> entry : wallet.getBudgets().entrySet()) {
			String category = entry.getKey();
			Budget budget = entry.getValue();

			double expensesForCategory = wallet.getTransactions().stream()
					.filter(t -> !t.isIncome() && t.getCategory().equals(category))
					.mapToDouble(Transaction::getAmount)
					.sum();

			double remainingBudget = budget.getLimit() - expensesForCategory;

			System.out.printf("%s: %.1f, Remaining budget: %.1f%n",
					category, budget.getLimit(), remainingBudget);
		}

		System.out.println("\nTransactions:");
		for (Transaction transaction : wallet.getTransactions()) {
			System.out.println(transaction.getCategory() + ": " + transaction.getAmount() + " (" + (transaction.isIncome() ? "Income" : "Expense") + ")");
		}
	}

	public void transfer(String recipientUsername, double amount) {
		if (currentUser == null) {
			System.out.println("No user logged in.");
			return;
		}

		if (amount <= 0) {
			System.out.println("Invalid amount.");
			return;
		}

		User recipient = users.get(recipientUsername);
		if (recipient == null) {
			System.out.println("Recipient not found.");
			return;
		}

		if (currentUser.getWallet().getBalance() < amount) {
			System.out.println("Insufficient funds.");
			return;
		}

		currentUser.getWallet().addExpense("Transfer to " + recipientUsername, amount);
		recipient.getWallet().addIncome("Transfer from " + currentUser.getUsername(), amount);
		System.out.println("Transfer successful.");
	}
}