package ru.mephi.abondarenko.financeapp.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mephi.abondarenko.financeapp.wallet.Wallet;

@Getter
@Setter
@NoArgsConstructor
public class User {
	private String username;
	private String password;
	private Wallet wallet;

	@JsonCreator
	public User(
			@JsonProperty("username") String username,
			@JsonProperty("password") String password) {
		this.username = username;
		this.password = password;
		this.wallet = new Wallet();
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
}