package ru.mephi.abondarenko.financeapp.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BudgetTest {

	@Test
	void testBudgetCreation() {
		Budget budget = new Budget("Food", 500);
		assertEquals("Food", budget.getCategory());
		assertEquals(500, budget.getLimit());
	}
}