package utils;

public enum Classe_Progetto {
		NONE("Nulla"),
		PICCOLA_SCALA("Piccola-Scala"),
		LARGA_SCALA("Larga-Scala"),
		SCOPO_LUCRO("Scopo di lucro"),
		NO_PROFIT("No-Profit"),
		SPIRALE("Spirale"),
		CASCATA("Cascata"),
		ROUTINE("Routine"),
		INNOVAZIONE("Innovazione");
					
		private final String presentazione;
		
		Classe_Progetto(String classe) {
			this.presentazione = classe;	
		}
		
		public String getPresentazione() {
			return this.presentazione;
		}
}
