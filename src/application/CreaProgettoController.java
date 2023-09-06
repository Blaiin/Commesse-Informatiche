package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import source.Progetto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.Classe_Progetto;
import utils.Linguaggi_Programmazione;
import utils.Tipo_Progetto;
import utils.UtilsClass;

public class CreaProgettoController extends UtilsClass implements Initializable {

	@FXML
	VBox creaProgettoVBox;

	// TextFields

	@FXML
	TextField projectNameField;
	@FXML
	TextField projectHoursField;
	@FXML
	TextField projectBudgetField;
	
	//ComboBox
	@FXML
	ComboBox<Tipo_Progetto> projectTypeSelectionBox;
	@FXML
	ComboBox<Classe_Progetto> projectClassSelectionBox;
	@FXML
	ComboBox<Linguaggi_Programmazione> projectLanguageSelectionBox;
	

	// Labels

	@FXML
	Label projectNameLabel;
	@FXML
	Label projectTypeLabel;
	@FXML
	Label projectClassLabel;
	@FXML
	Label projectHoursLabel;
	@FXML
	Label projectBudgetLabel;
	@FXML
	Label projectLanguageLabel;
	
	//DatePickers

	@FXML
	DatePicker projectDatePicker;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		projectNameField.requestFocus();
		hideComboBoxEnumValue(Tipo_Progetto.class, Tipo_Progetto.NONE, projectTypeSelectionBox);
		hideComboBoxEnumValue(Classe_Progetto.class, Classe_Progetto.NONE, projectClassSelectionBox);
		hideComboBoxEnumValue(Linguaggi_Programmazione.class, Linguaggi_Programmazione.NONE, projectLanguageSelectionBox);
		//projectTypeSelectionBox.getItems().addAll(Tipo_Progetto.values());
		//projectClassSelectionBox.getItems().addAll(Classe_Progetto.values());
	}

	// Utility Methods
	
	public Progetto creaProgetto() {
		String nome = capitalizeFirstLetter(projectNameField.getText().toLowerCase());
		LocalDate dataInizio = projectDatePicker.getValue();
		double budget = Double.parseDouble(projectBudgetField.getText());
		double oreProgetto = Double.parseDouble(projectHoursField.getText());
		Tipo_Progetto tipo = (Tipo_Progetto) getEnumSelectionBoxValue(this.projectTypeSelectionBox);
		Classe_Progetto classe = (Classe_Progetto) getEnumSelectionBoxValue(this.projectClassSelectionBox);
		Linguaggi_Programmazione linguaggio = (Linguaggi_Programmazione) getEnumSelectionBoxValue(this.projectLanguageSelectionBox);
		return new Progetto(nome, dataInizio, budget, classe, tipo, linguaggio, oreProgetto);
	}

	private <E extends Enum<E>> Enum<E> getEnumSelectionBoxValue(ComboBox<E> comboBox) {
		E selectedValue = comboBox.getValue();

		if (selectedValue != null) {
			return selectedValue;
		}

		return null;

	}

	// Getters and Setters

	public TextField getProjectNameField() {
		return projectNameField;
	}

	public void setProjectNameField(TextField projectNameField) {
		this.projectNameField = projectNameField;
	}
}