package it.unisa.utils;

import java.sql.Date;
import java.util.Calendar;

public class UtilityFunction {
	
	private UtilityFunction() {}
	
	public static Date getToday() {
		// Ottieni la data corrente
		java.util.Date today = new java.util.Date();

		// Creazione di un oggetto Calendar e impostazione della data corrente
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(today);

		// Ottenere l'anno, il mese e il giorno dalla data corrente
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		// Creazione di un oggetto java.sql.Date dalla data corrente
		return new Date(year - 1900, month, day);
		
		
	}
}
