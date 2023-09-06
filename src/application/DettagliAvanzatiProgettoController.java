package application;

import java.net.URL;
import java.util.ResourceBundle;

import source.Analista;
import source.Progetto;
import source.Programmatore;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import utils.UtilsClass;

public class DettagliAvanzatiProgettoController extends UtilsClass implements Initializable{
	//List Views
	@FXML
	private ListView<Progetto> listaProgetti;
	@FXML
	private ListView<Programmatore> listaProgrammatori;
	@FXML
	private ListView<Analista> listaAnalisti;
	
	//Buttons
	@FXML
	private Button modificaProgButton;
	@FXML
	private Button aggiungiProgButton;
	@FXML
	private Button eliminaProgButton;
	@FXML
	private Button modificaAnalistaButton;
	@FXML
	private Button aggiungiAnalistaButton;
	@FXML
	private Button eliminaAnalistaButton;
	@FXML
	private Button modificaCPButton;
	@FXML
	private Button aggiungiCPButton;
	@FXML
	private Button eliminaCPButton;
	@FXML
	private Button cancellaButton;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
   // TODO document why this method is empty
 }
}
