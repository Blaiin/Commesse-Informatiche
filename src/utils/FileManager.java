package utils;

import java.io.File;
import java.io.IOException;

public class FileManager {

	private static final String absoluteFilePath = System.getProperty("user.dir") + File.separator + "output";
	private static final String PROGETTO_FILE = "Progetti.json";
	private static final String PROGRAMMATORI_FILE = "Programmatori.json";
	private static final String ANALISTI_FILE = "Analisti.json";
	
	//Constructors
	
	public FileManager() {	
	}
	
	//Utility Methods
	
	public void manageJsonFiles() {
		generateJsonFile(PROGETTO_FILE);
		generateJsonFile(PROGRAMMATORI_FILE);
		generateJsonFile(ANALISTI_FILE);
	}
	public void generateJsonFile(String name) {
		File file = new File(absoluteFilePath + name);
		try {
			if (file.createNewFile()) {
				switch (name) {
				case PROGETTO_FILE:
					System.out.println("File per il salvataggio dei progetti creato con successo.");
					break;
				case PROGRAMMATORI_FILE:
					System.out.println("File per il salvataggio lista programmatori creato con successo.");
					break;
				case ANALISTI_FILE:
					System.out.println("File per il salvataggio lista analisti creato con successo.");
					break;
				default:
					System.out.println("File " + name + "creato con successo");
				}
			} else {
				System.out.println("File " + name + " già esistente.");
			}
		} catch (IOException ioExc) {
			switch (name) {
			case PROGETTO_FILE:
				System.err.println("Errore con i file di salvataggio dei progetti.");
				ioExc.printStackTrace();
				break;
			case PROGRAMMATORI_FILE:
				System.err.println("Errore con i file di salvataggio dei programmatori.");
				ioExc.printStackTrace();
				break;
			case ANALISTI_FILE:
				System.err.println("Errore con i file di salvataggio degli analisti.");
				ioExc.printStackTrace();
				break;
			default:
				System.out.println("Errore file: " + name);
			}
		} catch (Exception genericExc) {
			System.out.println("Errore non dettagliato.");
			genericExc.printStackTrace();
		}
	}

	//Getters and Setters
	
	public String getAbsoluteFilePath() {
		return absoluteFilePath;
	}

	public String getProgettoFilePath() {
		return absoluteFilePath + File.separator + PROGETTO_FILE;
	}

	public String getProgrammatoriFilePath() {
		return absoluteFilePath + File.separator + PROGRAMMATORI_FILE;
	}

	public String getAnalistiFilePath() {
		return absoluteFilePath + File.separator + ANALISTI_FILE;
	}
}
