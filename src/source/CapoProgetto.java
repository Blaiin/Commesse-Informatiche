package source;

public class CapoProgetto extends LavoratoreInformatico {

	// Constructors

	protected CapoProgetto(String nome, String cognome) {
		super(nome, cognome);
	}

	protected CapoProgetto(String nome, String cognome, String numTelefono) {
		super(nome, cognome, numTelefono);
	}

	protected CapoProgetto(String nome, String cognome, String numTelefono, String email) {
		super(nome, cognome, numTelefono, email);
	}

	protected CapoProgetto(String nome, String cognome, String numTelefono, String email, String codFiscale) {
		super(nome, cognome, numTelefono, email, codFiscale);
	}

}
