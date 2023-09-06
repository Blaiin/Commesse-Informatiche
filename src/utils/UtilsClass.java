package utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import javafx.scene.control.ComboBox;

public class UtilsClass {

	/* Standard method
	 * 
	 * public <E extends Enum<E>> void hideComboBoxEnumValue(Class<E> enumClass, E toHide, ComboBox<E> comboBox) {
		for (E value : enumClass.getEnumConstants()) {
			if (value != toHide) {
				comboBox.getItems().add(value);
			}
		}
	}
	*/
	
	//Functional programming method
	
	public <E extends Enum<E>> void hideComboBoxEnumValue(Class<E> enumClass, E toHide, ComboBox<E> comboBox) {
	    Arrays.stream(enumClass.getEnumConstants())
	            .filter(value -> value != toHide)
	            .forEach(comboBox.getItems()::add);
	}

	public boolean isValidUpperCaseAndNumberInput(String input) {
		return input.matches("^[A-Z0-9]+$");
	}

	public boolean isPhoneNumber(String input) {
		return input.matches("\\d{10}+$");
	}

	public String capitalizeFirstLetter(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}
		String firstCharOfWord = input.substring(0, 1).toUpperCase();
		String restOfWord = input.substring(1).toLowerCase();
		return firstCharOfWord + restOfWord;
	}

	public int getNonWorkingDays(LocalDate d1, LocalDate d2) {
		List<LocalDate> nonWorkingDays = new ArrayList<>();
		LocalDate currentData = d1;
		while (!currentData.isAfter(d2)) {
			if (currentData.getDayOfWeek() == DayOfWeek.SUNDAY) {
				nonWorkingDays.add(currentData);
			}
			currentData = currentData.plus(1, ChronoUnit.DAYS);
		}

		return nonWorkingDays.size();

	}

	public boolean isValidEmail(String email) {
		return (email.contains("@") && (email.contains(".it") || email.contains(".com") || email.contains(".org")));
	}

	public String formatDateToString(LocalDate toFormat) {
		return toFormat.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")).replace("-", "/");
	}

	public int roundDoubleNumber(double i) {
		double decimal = i - Math.floor(i);
		if (decimal >= 0.5)
			return (int) Math.ceil(i);
		return (int) Math.floor(i);
	}
}
