package source;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import utils.Linguaggi_Programmazione;
import utils.Status;

public class Programmatore extends LavoratoreInformatico {

	private Status status;
	private double pagaOrariaProgrammatore;
	public List<Progetto> progettiCompletati = new ArrayList<>();
	protected List<Linguaggi_Programmazione> competenze = new ArrayList<>();

	// Constructors

	protected Programmatore(String nome, String cognome) {
		super(nome, cognome);
	}

	protected Programmatore(String nome, String cognome, String codFiscale) {
		super(nome, cognome, codFiscale);
	}

	protected Programmatore(String nome, String cognome, String codFiscale, String email) {
		super(nome, cognome, codFiscale, email);
	}

	protected Programmatore(String nome, String cognome, String codFiscale, String email, String numTelefono) {
		super(nome, cognome, codFiscale, email, numTelefono);
	}

	// Utility Methods

	public void aggiungiProgetto(Progetto progetto) {
		if (this.competenze.contains(progetto.getLinguaggioProgetto())) {
			this.listaProgetti.add(progetto);
			calcolaProgettiCompletati();
			this.pagaOrariaProgrammatore = calcolaPagaOraria();
		}

	}

	private void calcolaProgettiCompletati() {
		LocalDate oggi = LocalDate.now();
		for (Progetto x : this.listaProgetti) {
			if (x.getDataFine().isBefore(oggi)) {
				this.progettiCompletati.add(x);
			}
		}

	}

	public void aggiungiLinguaggio(Linguaggi_Programmazione linguaggio) {
		if (!this.competenze.contains(linguaggio)) {
			this.competenze.add(linguaggio);
		} else {
			System.out.println("Linguaggio già presente nelle competenze del programmatore.");
		}
	}

	public void aggiungiLinguaggio(String linguaggio) {
		String s = capitalizeFirstLetter(linguaggio).replace(" ", "");
		this.competenze.add(Linguaggi_Programmazione.getLanguageByPresentation(s));
	}

	public void aggiungiLinguaggio(String l1, String l2) {
		aggiungiLinguaggio(l1);
		aggiungiLinguaggio(l2);
	}

	public void aggiungiLinguaggio(String l1, String l2, String l3) {
		aggiungiLinguaggio(l1);
		aggiungiLinguaggio(l2);
		aggiungiLinguaggio(l3);
	}

	public void aggiungiLinguaggio(String l1, String l2, String l3, String l4) {
		aggiungiLinguaggio(l1);
		aggiungiLinguaggio(l2);
		aggiungiLinguaggio(l3);
		aggiungiLinguaggio(l4);
	}

	private double calcolaPagaOraria() {
		setStatus();
		if (this.status == null) {
			this.status = Status.Junior;
		} else {
			switch (this.status) {
			case Intermediate:
				this.pagaOrariaProgrammatore = LavoratoreInformatico.PAGA_ORARIA * 1.5;
				return this.pagaOrariaProgrammatore;
			case Senior:
				this.pagaOrariaProgrammatore = LavoratoreInformatico.PAGA_ORARIA * 1.85;
				return this.pagaOrariaProgrammatore;
			default:
				this.pagaOrariaProgrammatore = LavoratoreInformatico.PAGA_ORARIA * 1;
				return this.pagaOrariaProgrammatore;
			}
		}
		return this.pagaOrariaProgrammatore;
	}

	// Getters and Setters

	public List<Linguaggi_Programmazione> getCompetenze() {
		return competenze;
	}

	public int getNumProgetti() {
		return listaProgetti.size();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus() {

		switch (this.progettiCompletati.size()) {
		case 0, 1, 2, 3, 4, 5:
			this.status = Status.Junior;
			break;
		case 6, 7, 8, 9, 10:
			this.status = Status.Intermediate;
			break;
		default:
			if (this.progettiCompletati.size() >= 10)
				this.status = Status.Senior;
		}
	}

	public int getProgettiCompletati() {
		calcolaProgettiCompletati();
		return this.progettiCompletati.size();
	}

	public double getPagaOrariaProgrammatore() {
		return pagaOrariaProgrammatore;
	}

	// toString

	@Override
	public String toString() {
		return "Programmatore -> \n\tNome= " + nome + ",\n\tCognome= " + cognome + ",\n\tNumero di telefono = "
				+ numTelefono + ",\n\tCodice Fiscale = " + codFiscale + ",\n\tE-mail= " + email + ",\n\tSalario= "
				+ calcolaSalario(this.listaProgetti);
	}

}
