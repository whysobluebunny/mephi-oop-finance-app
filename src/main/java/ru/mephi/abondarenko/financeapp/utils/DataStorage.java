package ru.mephi.abondarenko.financeapp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ru.mephi.abondarenko.financeapp.user.User;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class DataStorage {
	private static final String DATA_FILE = "finance_data.json";

	public static void saveData(Map<String, User> users) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		try {
			mapper.writeValue(new File(DATA_FILE), users);
			System.out.println("Data saved successfully.");
		} catch (IOException e) {
			System.out.println("Failed to save data: " + e.getMessage());
		}
	}

	public static Map<String, User> loadData() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			File file = new File(DATA_FILE);
			if (file.exists()) {
				return mapper.readValue(file, mapper.getTypeFactory().constructMapType(Map.class, String.class, User.class));
			}
		} catch (IOException e) {
			System.out.println("Failed to load data: " + e.getMessage());
		}
		return null;
	}
}