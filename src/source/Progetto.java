package source;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import utils.Classe_Progetto;
import utils.Linguaggi_Programmazione;
import utils.Tipo_Progetto;
import utils.UtilsClass;
import java.text.DecimalFormat;

public class Progetto extends UtilsClass {

	// static fields
	private static final double PERCENTUALE_ORE_ANALISI = 0.70;
	private static final double PERCENTUALE_ORE_PROGRAMMAZIONE = 1 - PERCENTUALE_ORE_ANALISI;
	private static final DecimalFormat formatoMonetario = new DecimalFormat("#,##0.00 €");
	private static final DecimalFormat formatoOrario = new DecimalFormat("# ore");

	// regular fields
	private String nomeProgetto;
	private CapoProgetto cp;
	public LocalDate dataInizio;
	public LocalDate dataFine;
	private double oreProgettoTotali;
	private double budgetTotale;
	private double budgetRimanente;
	private double percentualeOreLavoroCP;
	private double oreLavoroCP;
	private double oreAnalisi;
	private double oreProgrammazione;
	private List<LavoratoreInformatico> listaProgrammatori = new ArrayList<>();
	private List<LavoratoreInformatico> listaAnalisti = new ArrayList<>();
	private Classe_Progetto classeProgetto;
	private Tipo_Progetto tipoProgetto;
	private Linguaggi_Programmazione linguaggioProgetto;

	// Constructors

	public Progetto() {

	}

	public Progetto(String nomeProgetto) {
		this.setNomeProgetto(nomeProgetto);
	}

	public Progetto(String nome, String dataInizio, String dataFine) {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		this.nomeProgetto = nome;
		try {
			this.dataInizio = LocalDate.parse(dataInizio, format);
			this.dataFine = LocalDate.parse(dataFine, format);
		} catch (Exception exc) {
			System.err.print(exc.getMessage());
		}
	}

	public Progetto(String nomeProgetto, LocalDate dataInizio, double budget, Classe_Progetto classeProgetto,
			Tipo_Progetto tipoProgetto, Linguaggi_Programmazione linguaggio, double oreProgetto) {
		this.nomeProgetto = capitalizeFirstLetter(nomeProgetto);
		this.dataInizio = dataInizio;
		this.dataFine = calcolaDataFine(dataInizio, oreProgetto);
		this.budgetTotale = budget;
		this.budgetRimanente = this.budgetTotale;
		this.classeProgetto = classeProgetto;
		this.tipoProgetto = tipoProgetto;
		this.linguaggioProgetto = linguaggio;
		this.oreProgettoTotali = oreProgetto;
		this.oreAnalisi = oreProgetto * Progetto.PERCENTUALE_ORE_ANALISI;
		this.oreProgrammazione = oreProgetto * Progetto.PERCENTUALE_ORE_PROGRAMMAZIONE;
		calcolaOreLavoroCP();
	}

	// Public Utility Methods

	public void aggiungiAnalista(Analista ana) {
		this.listaAnalisti.add(ana);
		this.budgetRimanente = calcolaBudgetRimanente();
	}

	public Analista cercaAnalista(String nome, String cognome, String numTelefono) {
		for (LavoratoreInformatico x : this.listaAnalisti) {
			if (x.nome.equals(nome) && x.cognome.equals(cognome) && x.numTelefono.equals(numTelefono)) {
				return (Analista) x;
			}
		}
		return null;
	}

	public void eliminaAnalista(String nome, String cognome, String numTelefono) {
		Analista analistaToRemove = cercaAnalista(nome, cognome, numTelefono);
		this.listaAnalisti.removeIf(target -> target == analistaToRemove);
		this.budgetRimanente = calcolaBudgetRimanente();
	}

	public void aggiungiProgrammatore(Programmatore prog) {
		if (prog.competenze.contains(this.linguaggioProgetto)) {
			this.listaProgrammatori.add(prog);
			this.budgetRimanente = calcolaBudgetRimanente();
		} else {
			System.err.println("Programmatore " + prog.getNome() + " " + prog.getCognome()
					+ " privo di competenze per il Progetto: " + this.nomeProgetto);
		}
	}

	public Programmatore cercaProgrammatore(String nome, String cognome, String numTelefono) {
		for (LavoratoreInformatico x : this.listaProgrammatori) {
			if (x.nome.equals(nome) && x.cognome.equals(cognome) && x.numTelefono.equals(numTelefono)) {
				return (Programmatore) x;
			}
		}
		return null;
	}

	public void eliminaProgrammatore(String nome, String cognome, String numTelefono) {
		Programmatore progToRemove = cercaProgrammatore(nome, cognome, numTelefono);
		this.listaAnalisti.removeIf(target -> target == progToRemove);
		this.budgetRimanente = calcolaBudgetRimanente();
	}

	// Private Utility Methods

	private double calcolaBudgetRimanente() {
		double budgetAttuale = this.budgetRimanente;
		List<LavoratoreInformatico> listaLavoratoriTot = new ArrayList<>();
		listaLavoratoriTot.addAll(listaAnalisti);
		listaLavoratoriTot.addAll(listaProgrammatori);

		for (LavoratoreInformatico x : listaLavoratoriTot) {
			budgetAttuale -= (x.getPagaOraria() * this.oreProgettoTotali);
		}
		return budgetAttuale;
	}

	private void calcolaOreLavoroCP() {
		switch (this.classeProgetto) {
		case PICCOLA_SCALA:
			this.percentualeOreLavoroCP = 0.17;
			break;
		case LARGA_SCALA:
			this.percentualeOreLavoroCP = 0.36;
			break;
		case SCOPO_LUCRO:
			this.percentualeOreLavoroCP = 0.29;
			break;
		case NO_PROFIT:
			this.percentualeOreLavoroCP = 0.14;
			break;
		case SPIRALE:
			this.percentualeOreLavoroCP = 0.22;
			break;
		case CASCATA:
			this.percentualeOreLavoroCP = 0.13;
			break;
		case ROUTINE:
			this.percentualeOreLavoroCP = 0.12;
			break;
		case INNOVAZIONE:
			this.percentualeOreLavoroCP = 0.20;
			break;
		default:
			System.out.println("Classe progetto non specificata; percentuale ore lavoro Capo Progetto standard.");
			this.percentualeOreLavoroCP = 0.10;
			break;
		}
		this.oreLavoroCP = this.oreProgettoTotali * this.percentualeOreLavoroCP;
	}

	private LocalDate calcolaDataFine(LocalDate dataInizioProgetto, double oreProgetto) {
		int giorniProgetto = roundDoubleNumber(oreProgetto) / 8;
		return dataInizioProgetto.plusDays(giorniProgetto);
	}

	// Getters and Setters

	public int getNumProgrammatori() {
		return this.listaProgrammatori.size();
	}

	public int getNumAnalisti() {
		return this.listaAnalisti.size();
	}

	public String getNomeProgetto() {
		return nomeProgetto;
	}

	public void setNomeProgetto(String nomeProgetto) {
		this.nomeProgetto = capitalizeFirstLetter(nomeProgetto);
	}

	public String getPresentazioneClasseProgetto() {
		return this.classeProgetto.getPresentazione();
	}

	public Classe_Progetto getClasseProgetto() {
		return this.classeProgetto;
	}

	public void setClasseProgetto(Classe_Progetto classeProgetto) {
		this.classeProgetto = classeProgetto;
	}

	public String getPresentazioneTipoProgetto() {
		return this.tipoProgetto.getPresentazione();
	}

	public Tipo_Progetto getTipoProgetto() {
		return this.tipoProgetto;
	}

	public void setTipoProgetto(String tipoProgetto) {
		this.tipoProgetto.getTypeByPresentation(tipoProgetto);
	}

	public void setTipoProgetto(Tipo_Progetto tipoProgetto) {
		this.tipoProgetto = tipoProgetto;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFine() {
		return dataFine;
	}

	public void setDataFine(LocalDate dataFine) {
		this.dataFine = dataFine;
	}

	public double getBudgetTotale() {
		return budgetTotale;
	}

	public void setBudget(double budget) {
		this.budgetTotale = budget;
	}

	public double getBudgetRimanente() {
		return budgetRimanente;
	}

	public double getPercentualeOreAnalisi() {
		return PERCENTUALE_ORE_ANALISI;
	}

	public double getPercentualeOreProgrammazione() {
		return PERCENTUALE_ORE_PROGRAMMAZIONE;
	}

	public double getOreLavoroCP() {
		return oreLavoroCP;
	}

	public void setOreLavoroCP(int oreLavoroCP) {
		this.oreLavoroCP = oreLavoroCP;
	}

	public double getOreAnalisi() {
		return oreAnalisi;
	}
	
	public double getOreProgrammazione() {
		return oreProgrammazione;
	}

	public Linguaggi_Programmazione getLinguaggioProgetto() {
		return this.linguaggioProgetto;
	}

	public void setLinguaggioProgetto(String linguaggioProgetto) {
		this.linguaggioProgetto = Linguaggi_Programmazione.getLanguageByPresentation(linguaggioProgetto);
	}

	public void setLinguaggioProgetto(Linguaggi_Programmazione linguaggioProgetto) {
		this.linguaggioProgetto = linguaggioProgetto;
	}

	public CapoProgetto getCp() {
		return cp;
	}

	public void setCp(CapoProgetto cp) {
		this.cp = cp;
	}


	public double getPercentualeOreLavoroCP() {
		return percentualeOreLavoroCP;
	}

	// toString Methods
	
	@Override
	public String toString() {
		return "\n\t" + this.nomeProgetto + " -> \n\tClasse progetto: "
				+ classeProgetto.getPresentazione() + ",\n\tTipo progetto: " + tipoProgetto.getPresentazione()
				+ ",\n\tData inizio: " + this.dataInizio + ",\n\tData fine: " + this.dataFine + ",\n\tBudget totale: "
				+ formatoMonetario.format(this.budgetTotale) + ",\n\tOre di analisi: "
				+ formatoOrario.format(this.oreAnalisi) + ",\n\tOre di programmazione: "
				+ formatoOrario.format(this.oreProgrammazione) + ",\n\tOre lavorative C.P.: " + formatoOrario.format(oreLavoroCP)
				+ ",\n\tN. Analisti: " + getNumAnalisti() + ",\n\tN. Programmatori: " + getNumProgrammatori() + "\n";
	}

	public void stampaListeLavoratori() {
		String format = "\t\t%-12s%-1s%-18s%-1s%-18s%-1s%-28s%-1s%-20s%-1s%-8s%n";
		System.out.println(this.nomeProgetto);
		if (listaAnalisti.isEmpty() && listaProgrammatori.isEmpty()) {
			System.out.println("\tLe liste sono vuote!");
		} else if (!listaAnalisti.isEmpty()) {
			System.out.println("\tAnalisti: \n\t");
			System.out.printf(format, "Nome", "|", "Cognome", "|", "Num. Tel.", "|", "E-mail", "|", "Codice Fiscale",
					"|", "Salario");
			for (LavoratoreInformatico x : this.listaAnalisti) {
				System.out.printf(format, x.nome, x.cognome, x.numTelefono, x.email, x.codFiscale);
			}
		} else if (!listaProgrammatori.isEmpty()) {
			System.out.println("\tProgrammatori: \n\t");
			System.out.printf(format, "Nome", "|", "Cognome", "|", "Num. Tel.", "|", "E-mail", "|", "Codice Fiscale",
					"|", "Salario");
			for (LavoratoreInformatico x : this.listaProgrammatori) {
				System.out.printf(format, x.nome, "|", x.cognome, "|", x.numTelefono, "|", x.email, "|", x.codFiscale,
						"|", x.calcolaSalario(x.listaProgetti));
			}
		}
	}
}
