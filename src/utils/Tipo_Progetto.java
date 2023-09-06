package utils;

public enum Tipo_Progetto {
		NONE("Nullo"),
		INDUSTRIALE("Industriale"), 
		INFORMATICO("Informatico"), 
		SOCIALE("Sociale"), 
		GOVERNATIVO("Governativo"), 
		OSPEDALIERO("Ospedaliero"), 
		COMUNALE("Comunale"), 
		PUBBLICO("Pubblico");
					
		private final String presentazione;
		
		Tipo_Progetto(String tipo) {
			this.presentazione = tipo;	
		}
		
		public String getPresentazione() {
			return this.presentazione;
		}		
		
		public Tipo_Progetto getTypeByPresentation(String setTypeByString) {
			UtilsClass utils = new UtilsClass();
			String s = utils.capitalizeFirstLetter(setTypeByString.replace(" ", ""));
			switch(s) {
			case "Industriale": 
				return Tipo_Progetto.INDUSTRIALE;
			case "Informatico": 
				return Tipo_Progetto.INFORMATICO;
			case "Sociale": 
				return Tipo_Progetto.SOCIALE;
			case "Governativo": 
				return Tipo_Progetto.GOVERNATIVO;
			case "Ospedaliero": 
				return Tipo_Progetto.OSPEDALIERO;
			case "Comunale": 
				return Tipo_Progetto.COMUNALE;
			case "Pubblico": 
				return Tipo_Progetto.PUBBLICO;
			default: 
				System.out.println("Tipo progetto non supportato");
				return Tipo_Progetto.NONE;
			}
		}
}
