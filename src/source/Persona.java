package source;

import utils.UtilsClass;

public abstract class Persona extends UtilsClass {
	protected String nome;
	protected String cognome;
	protected String codFiscale;
	protected String email;
	protected String numTelefono;

	// Constructors

	protected Persona(String nome, String cognome) {
		super();
		this.nome = nome;
		this.cognome = cognome;
	}

	protected Persona(String nome, String cognome, String numTelefono) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.setNumTelefono(numTelefono);
	}

	protected Persona(String nome, String cognome, String numTelefono, String email) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.setNumTelefono(numTelefono);
		this.setEmail(email);
	}

	protected Persona(String nome, String cognome, String numTelefono, String email, String codFiscale) {
		super();
		this.setNome(nome);
		this.setCognome(cognome);
		this.setNumTelefono(numTelefono);
		this.setEmail(email);
		this.setCodFiscale(codFiscale);
	}
	// Persona pippo = new Persona();
	// int intero = 10;
	
	// Getters and Setters

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = capitalizeFirstLetter(nome);
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = capitalizeFirstLetter(cognome);
	}

	public String getCodFiscale() {
		return codFiscale;
	}

	public void setCodFiscale(String inputCodFiscale) {
		// if (isValidUpperCaseAndNumberInput(inputCodFiscale)) this.codFiscale =
		// inputCodFiscale;
		this.codFiscale = isValidUpperCaseAndNumberInput(inputCodFiscale) ? this.codFiscale : "Errore, riprovare.";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		// if (isValidEmail(email)) this.email = email;
		// this.email = "Errore, riprovare";
		this.email = isValidEmail(email) ? this.email : "Errore, riprovare.";
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		// if (isPhoneNumber(numTelefono)) this.numTelefono = numTelefono;
		// this.numTelefono = "Errore, riprovare";
		this.numTelefono = isPhoneNumber(numTelefono) ? this.numTelefono : "Errore, riprovare.";
	}

}
