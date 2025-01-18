package ru.mephi.abondarenko.financeapp;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class FinanceManagerScenarioTest {

	@Test
	void testFinanceManagerScenario() {
		// Тест из примера в условии
		FinanceManager manager = new FinanceManager();

		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");

		manager.addIncome("Salary", 20000);
		manager.addIncome("Salary", 40000);
		manager.addIncome("Bonus", 3000);

		manager.addExpense("Food", 300);
		manager.addExpense("Food", 500);
		manager.addExpense("Entertainment", 3000);
		manager.addExpense("Utilities", 3000);
		manager.addExpense("Taxi", 1500);

		manager.setBudget("Food", 4000);
		manager.setBudget("Entertainment", 3000);
		manager.setBudget("Utilities", 2500);

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;
		System.setOut(new PrintStream(outputStream));

		manager.showStatistics();

		System.setOut(originalOut);

		String output = normalizeLineSeparators(outputStream.toString());

		String expectedOutput =
				normalizeLineSeparators("""
						Total income: 63000.0
						Income by category:
						Salary: 60000.0
						Bonus: 3000.0
						Total expenses: 8300.0
						Budget by category:
						Utilities: 2500.0, Remaining budget: -500.0
						Entertainment: 3000.0, Remaining budget: 0.0
						Food: 4000.0, Remaining budget: 3200.0

						Transactions:
						Salary: 20000.0 (Income)
						Salary: 40000.0 (Income)
						Bonus: 3000.0 (Income)
						Food: 300.0 (Expense)
						Food: 500.0 (Expense)
						Entertainment: 3000.0 (Expense)
						Utilities: 3000.0 (Expense)
						Taxi: 1500.0 (Expense)
						""");

		assertEquals(expectedOutput, output);
	}

	private String normalizeLineSeparators(String input) {
		return input.replace("\r\n", "\n").replace("\r", "\n");
	}
}