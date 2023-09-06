package source;

import java.util.Objects;

public class Analista extends LavoratoreInformatico {

	// Constructors

	protected Analista(String nome, String cognome) {
		super(nome, cognome);
	}

	protected Analista(String nome, String cognome, String codFiscale) {
		super(nome, cognome, codFiscale);
	}

	protected Analista(String nome, String cognome, String codFiscale, String email) {
		super(nome, cognome, codFiscale, email);
	}

	protected Analista(String nome, String cognome, String codFiscale, String email, String numTelefono) {
		super(nome, cognome, codFiscale, email, numTelefono);
	}

	// Getters and Setters

	public Progetto[] getListaProgetti() {
		return this.listaProgetti.toArray(new Progetto[this.listaProgetti.size()]);
	}

	public void aggiungiProgetto(Progetto progetto) {
		this.listaProgetti.add(progetto);
	}

	// equals & hashcode --> di nessuna utilità per il momento, qui solo per esercitazione

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Analista analista = (Analista) obj;
		return Objects.equals(nome, analista.nome) && Objects.equals(cognome, analista.cognome)
				&& Objects.equals(numTelefono, analista.numTelefono);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, cognome, numTelefono);
	}

}
