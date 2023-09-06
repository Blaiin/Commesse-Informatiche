package source;

import java.time.LocalDate;
import utils.UtilsClass;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class LavoratoreInformatico extends Persona {

	public List<Progetto> listaProgetti = new ArrayList<>();
	protected static final double PAGA_ORARIA = 10.00;
	protected static final int ORE_MASSIME_GIORNALIERE = 8;
	protected HashMap<LocalDate, List<String>> impegni = new HashMap<>();

	// Constructors
	protected LavoratoreInformatico(String nome, String cognome) {
		super(nome, cognome);
	}

	protected LavoratoreInformatico(String nome, String cognome, String numTelefono) {
		super(nome, cognome, numTelefono);
	}

	protected LavoratoreInformatico(String nome, String cognome, String numTelefono, String email) {
		super(nome, cognome, numTelefono, email);
	}

	protected LavoratoreInformatico(String nome, String cognome, String numTelefono, String email, String codFiscale) {
		super(nome, cognome, numTelefono, email, codFiscale);
	}

	// Utility Methods

	protected double calcolaSalario(List<Progetto> y) {
		int giorniLavoroProgetto = 0;
		int periodoProgetto = 0;
		UtilsClass utils = new UtilsClass();
		if (!y.isEmpty()) {
			for (Progetto x : y) {
				periodoProgetto += ChronoUnit.DAYS.between(x.getDataInizio(), x.getDataFine());
				giorniLavoroProgetto = periodoProgetto - utils.getNonWorkingDays(x.getDataInizio(), x.getDataFine());
			}
		} else {
			System.out.println("La lista progetti è vuota.");
		}
		return (LavoratoreInformatico.ORE_MASSIME_GIORNALIERE * giorniLavoroProgetto) * PAGA_ORARIA;
	}

	public void stampaImpegni() {
		for (Map.Entry<LocalDate, List<String>> entry : this.impegni.entrySet()) {
			String data = entry.getKey().toString();
			String[] listaImpegniCorrispondenti = entry.getValue().toArray(new String[entry.getValue().size()]);
			System.out.printf("Giorno: %s, Impegni: %s", data, listaImpegniCorrispondenti);
		}
	}

	// Getters and Setters

	public double getPagaOraria() {
		return PAGA_ORARIA;
	}

	public static int getnOreMassimeGiornaliere() {
		return ORE_MASSIME_GIORNALIERE;
	}

	public Map<LocalDate, List<String>> getImpegni() {
		return impegni;
	}

	public void aggiungiImpegni(LocalDate date, String impegno) {

		if (this.impegni.containsKey(date)) {
			List<String> list = this.impegni.get(date);
			list.add(impegno);
		} else {
			List<String> list = new ArrayList<>();
			list.add(impegno);
			this.impegni.put(date, list);
		}
	}

	// toString

	@Override
	public String toString() {
		return "Programmatore -> \n\tNome= " + nome + ",\n\tCognome= " + cognome + ",\n\tNumero di telefono = "
				+ numTelefono + ",\n\tCodice Fiscale = " + codFiscale + ",\n\tE-mail= " + email + ",\n\tSalario= "
				+ calcolaSalario(this.listaProgetti);
	}

}
