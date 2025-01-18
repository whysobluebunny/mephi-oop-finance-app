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
	void testRegisterUserWithEmptyUsername() {
		FinanceManager manager = new FinanceManager();
		assertThrows(IllegalArgumentException.class, () -> manager.registerUser("", "password123"));
	}

	@Test
	void testRegisterUserWithEmptyPassword() {
		FinanceManager manager = new FinanceManager();
		assertThrows(IllegalArgumentException.class, () -> manager.registerUser("testUser", ""));
	}

	@Test
	void testLoginUser() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		assertTrue(manager.loginUser("testUser", "password123"));
	}

	@Test
	void testLoginUserWithInvalidCredentials() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		assertThrows(IllegalArgumentException.class, () -> manager.loginUser("testUser", "wrongPassword"));
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
	void testAddIncomeWithEmptyCategory() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");
		assertThrows(IllegalArgumentException.class, () -> manager.addIncome("", 1000));
	}

	@Test
	void testAddIncomeWithNegativeAmount() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");
		assertThrows(IllegalArgumentException.class, () -> manager.addIncome("Salary", -1000));
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
	void testAddExpenseWithEmptyCategory() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");
		assertThrows(IllegalArgumentException.class, () -> manager.addExpense("", 200));
	}

	@Test
	void testAddExpenseWithNegativeAmount() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");
		assertThrows(IllegalArgumentException.class, () -> manager.addExpense("Food", -200));
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
	void testSetBudgetWithEmptyCategory() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");
		assertThrows(IllegalArgumentException.class, () -> manager.setBudget("", 500));
	}

	@Test
	void testSetBudgetWithNegativeLimit() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");
		assertThrows(IllegalArgumentException.class, () -> manager.setBudget("Food", -500));
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

	@Test
	void testTransferWithEmptyRecipient() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("sender", "password123");
		manager.loginUser("sender", "password123");
		manager.addIncome("Salary", 1000);
		assertThrows(IllegalArgumentException.class, () -> manager.transfer("", 500));
	}

	@Test
	void testTransferWithNegativeAmount() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("sender", "password123");
		manager.registerUser("receiver", "password123");
		manager.loginUser("sender", "password123");
		manager.addIncome("Salary", 1000);
		assertThrows(IllegalArgumentException.class, () -> manager.transfer("receiver", -500));
	}

	@Test
	void testTransferWithInsufficientFunds() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("sender", "password123");
		manager.registerUser("receiver", "password123");
		manager.loginUser("sender", "password123");
		manager.addIncome("Salary", 1000);
		assertThrows(IllegalStateException.class, () -> manager.transfer("receiver", 1500));
	}

	@Test
	void testShowStatistics() {
		FinanceManager manager = new FinanceManager();
		manager.registerUser("testUser", "password123");
		manager.loginUser("testUser", "password123");
		manager.addIncome("Salary", 1000);
		manager.addExpense("Food", 200);
		manager.setBudget("Food", 500);
		manager.showStatistics(); // Проверяем, что метод выполняется без ошибок
	}
}