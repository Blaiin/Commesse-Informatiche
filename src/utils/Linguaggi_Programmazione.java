package utils;

public enum Linguaggi_Programmazione {
			NONE("Nullo"),
			JAVA("Java"), 
			PYTHON("Python"), 
			CPP("C++"), 
			C_SHARP("C#"), 
			DOT_NET("Dot Net");
			
			private final String presentazione;
	
			Linguaggi_Programmazione(String linguaggio) {
				this.presentazione = linguaggio;	
			}
			//Getters  and Setters
			
			public String getPresentazione() {
				return this.presentazione;
			}
			
			public static Linguaggi_Programmazione getLanguageByPresentation(String setTypeByString) {
				UtilsClass utils = new UtilsClass();
				String s = utils.capitalizeFirstLetter(setTypeByString.replace(" ", ""));
				switch(s) {
				case "Java": 
					return Linguaggi_Programmazione.JAVA;
				case "Python": 
					return Linguaggi_Programmazione.PYTHON;
				case "C++": 
					return Linguaggi_Programmazione.CPP;
				case "C#": 
					return Linguaggi_Programmazione.C_SHARP;
				case "Dot Net": 
					return Linguaggi_Programmazione.DOT_NET;
				default: 
					System.err.println("Tipo progetto non supportato");
					return Linguaggi_Programmazione.NONE;
				}
			}
}


