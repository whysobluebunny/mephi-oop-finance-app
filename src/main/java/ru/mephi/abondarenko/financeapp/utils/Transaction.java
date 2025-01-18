package ru.mephi.abondarenko.financeapp.utils;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Transaction {
	private String category;
	private double amount;
	private boolean isIncome;
	private String date;

	@JsonCreator
	public Transaction(
			@JsonProperty("category") String category,
			@JsonProperty("amount") double amount,
			@JsonProperty("isIncome") boolean isIncome) {
		this.category = category;
		this.amount = amount;
		this.isIncome = isIncome;
		this.date = java.time.LocalDate.now().toString();
	}
}