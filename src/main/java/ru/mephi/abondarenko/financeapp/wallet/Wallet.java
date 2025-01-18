package ru.mephi.abondarenko.financeapp.wallet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mephi.abondarenko.financeapp.utils.Budget;
import ru.mephi.abondarenko.financeapp.utils.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Wallet {
	private double balance;
	private List<Transaction> transactions;
	private Map<String, Budget> budgets;

	public Wallet() {
		this.balance = 0;
		this.transactions = new ArrayList<>();
		this.budgets = new HashMap<>();
	}

	public void addIncome(String category, double amount) {
		balance += amount;
		transactions.add(new Transaction(category, amount, true));
	}

	public void addExpense(String category, double amount) {
		balance -= amount;
		transactions.add(new Transaction(category, amount, false));

		Budget budget = budgets.get(category);
		if (budget != null) {
			double totalExpenses = transactions.stream()
					.filter(t -> !t.isIncome() && t.getCategory().equals(category))
					.mapToDouble(Transaction::getAmount)
					.sum();
			if (totalExpenses > budget.getLimit()) {
				System.out.println("Warning: Budget exceeded for category " + category);
			}
		}
	}

	public void setBudget(String category, double limit) {
		budgets.put(category, new Budget(category, limit));
	}
}