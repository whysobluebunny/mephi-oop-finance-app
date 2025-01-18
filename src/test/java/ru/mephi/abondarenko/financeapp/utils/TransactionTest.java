package ru.mephi.abondarenko.financeapp.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

	@Test
	void testTransactionCreation() {
		Transaction transaction = new Transaction("Food", 100, false);
		assertEquals("Food", transaction.getCategory());
		assertEquals(100, transaction.getAmount());
		assertFalse(transaction.isIncome());
		assertNotNull(transaction.getDate());
	}
}