package it.unisa.utils;

import java.util.regex.Pattern;

public class Validator {
	
	private Validator() {}
	
	public static boolean isValidEmail(String email) {
		if(email==null)return false;
		String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	    Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        return pattern.matcher(email).matches();
    }
	
	public static boolean hasOnlyAlphanumeric(String input) {
	    if (input == null) {
	        return false;
	    }
	    String alphanumericPattern = "^[a-zA-Z0-9\\s]+$";
	    return input.matches(alphanumericPattern);
	}
	
	public static boolean hasOnlyAlphanumericWithoutSPace(String input) {
	    if (input == null) {
	        return false;
	    }
	    String alphanumericPattern = "^[a-zA-Z0-9]+$";
	    return input.matches(alphanumericPattern);
	}
	
	public static boolean isCvcValid(String cvc) {
		if(cvc==null) return false;
	    // Verifica la lunghezza del CVC (generalmente 3 o 4 cifre)
	    if (cvc.length() < 3 || cvc.length() > 4) {
	        return false;
	    }

	    // Verifica se il CVC contiene solo cifre
	    if (!cvc.matches("\\d+")) {
	        return false;
	    }

	    // Il CVC è valido
	    return true;
	}
	
	
	public static boolean isCardNumberValid(String cardNumber) {
		
		if(cardNumber==null) return false;
        cardNumber = cardNumber.replaceAll("\s+", "");

	    // Verifica se il numero di carta contiene solo cifre
	    if (!cardNumber.matches("\\d+")) {
	        return false;
	    }

	    // Calcola la somma dei numeri di carta secondo l'algoritmo di Luhn
	    

	    // Verifica se la somma totale è divisibile per 10
	    return true;
	}
	
	
}
