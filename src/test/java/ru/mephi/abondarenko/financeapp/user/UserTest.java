package ru.mephi.abondarenko.financeapp.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

	@Test
	void testUserCreation() {
		User user = new User("testUser", "password123");
		assertEquals("testUser", user.getUsername());
		assertTrue(user.checkPassword("password123"));
		assertFalse(user.checkPassword("wrongPassword"));
		assertNotNull(user.getWallet());
	}
}