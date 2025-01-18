package ru.mephi.abondarenko.financeapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinanceManagerTest {

	@Test
	void testRegisterUser() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		assertNotNull(manager.getUsers().get("testUser"));
	}

	@Test
	void testLoginUser() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		assertTrue(manager.loginUser("testUser", "password123"));
		assertFalse(manager.loginUser("testUser", "wrongPassword"));
	}

	@Test
	void testAddIncome() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");
		manager.addIncome("Salary", 1000);
		assertEquals(1000, manager.getCurrentUser().getWallet().getBalance());
	}

	@Test
	void testAddExpense() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");
		manager.addIncome("Salary", 1000);
		manager.addExpense("Food", 200);
		assertEquals(800, manager.getCurrentUser().getWallet().getBalance());
	}

	@Test
	void testSetBudget() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");
		manager.setBudget("Food", 500);
		assertNotNull(manager.getCurrentUser().getWallet().getBudgets().get("Food"));
	}

	@Test
	void testTransfer() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("sender", "password123");
		manager.registerUser("receiver", "password123");
		manager.loginUser("sender", "password123");
		manager.addIncome("Salary", 1000);
		manager.transfer("receiver", 500);
		assertEquals(500, manager.getCurrentUser().getWallet().getBalance());
		assertEquals(500, manager.getUsers().get("receiver").getWallet().getBalance());
	}
}