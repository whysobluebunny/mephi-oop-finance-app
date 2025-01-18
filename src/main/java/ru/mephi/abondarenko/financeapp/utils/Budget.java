package ru.mephi.abondarenko.financeapp.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Budget {
	private String category;
	private double limit;

	@JsonCreator
	public Budget(
			@JsonProperty("category") String category,
			@JsonProperty("limit") double limit) {
		this.category = category;
		this.limit = limit;
	}
}