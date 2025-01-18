package ru.mephi.abondarenko.financeapp.wallet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WalletTest {

	@Test
	void testAddIncome() {
		Wallet wallet = new Wallet();
		wallet.addIncome("Salary", 1000);
		assertEquals(1000, wallet.getBalance());
		assertEquals(1, wallet.getTransactions().size());
	}

	@Test
	void testAddExpense() {
		Wallet wallet = new Wallet();
		wallet.addIncome("Salary", 1000);
		wallet.addExpense("Food", 200);
		assertEquals(800, wallet.getBalance());
		assertEquals(2, wallet.getTransactions().size());
	}

	@Test
	void testSetBudget() {
		Wallet wallet = new Wallet();
		wallet.setBudget("Food", 500);
		assertNotNull(wallet.getBudgets().get("Food"));
		assertEquals(500, wallet.getBudgets().get("Food").getLimit());
	}
}