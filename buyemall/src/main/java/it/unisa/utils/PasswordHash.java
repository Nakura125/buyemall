package it.unisa.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.unisa.DAO.AccountDAO;

public class PasswordHash {
	private String sha512;
    private static final Logger LOGGER = Logger.getLogger(PasswordHash.class.getName());
	
	public PasswordHash(String string) {
		try {
			// Creazione dell'istanza del digest SHA-512
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            
            // Calcolo dell'hash SHA-512 della password
            byte[] hashBytes = sha512.digest(string.getBytes());
            
            // Conversione dell'array di byte in una rappresentazione esadecimale
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hashBuilder.append(String.format("%02x", b));
            }
            
            // Restituzione dell'hash come stringa
            this.sha512= hashBuilder.toString();
		} catch (NoSuchAlgorithmException e) {

			LOGGER.log(Level.INFO,"Error:",e);
		}
	}
	
	
	
	public static String hashPassword(String password) {
        try {
            // Creazione dell'istanza del digest SHA-512
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
            
            // Calcolo dell'hash SHA-512 della password
            byte[] hashBytes = sha512.digest(password.getBytes());
            
            // Conversione dell'array di byte in una rappresentazione esadecimale
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hashBuilder.append(String.format("%02x", b));
            }
            
            // Restituzione dell'hash come stringa
            return hashBuilder.toString();
            
        } catch (NoSuchAlgorithmException e) {
        	LOGGER.log(Level.INFO,"Error:",e);
        }
        
        return null;
    }

	public String getSha512() {
		return sha512;
	}

	public void setSha512(String sha512) {
		this.sha512 = sha512;
	}



	
}
