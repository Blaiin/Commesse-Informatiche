package application;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import source.Progetto;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import utils.*;

public class OverviewController implements Initializable {

	protected List<Progetto> progettiTotali;
	protected FileManager manager = new FileManager();
	private Image icon = new Image("C:\\Users\\Utente\\Desktop\\Scrivania\\GitHub\\Commesse-Informatiche\\src\\resources\\images\\its3.png");

	@FXML
	private ListView<Progetto> listaProgettiTotali;
	@FXML
	private Label listaProgettiLabel;
	@FXML
	private Button creaProgetto;
	@FXML
	private Button modificaButton;
	@FXML
	private Button salvaButton;
	@FXML
	private Button eliminaButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		manager.manageJsonFiles();
		progettiTotali = new ArrayList<>();
		modificaButton.setVisible(false);

		listaProgettiTotali.getSelectionModel().getSelectedItems()
				.addListener((ListChangeListener<Progetto>) x -> this.modificaButton
						.setVisible(!listaProgettiTotali.getSelectionModel().getSelectedItems().isEmpty()));
		caricaProgettiDaJson();
	}

	public void apriCreaProgettoTab() {

		Dialog<ButtonType> dialog = new Dialog<>();
		dialog.setTitle("Crea un progetto");

		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/CreaProgettoPage.fxml"));
			VBox dialogScene = fxmlLoader.load();
			CreaProgettoController controller = fxmlLoader.getController();
			dialog.getDialogPane().setContent(dialogScene);
			dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
			dialog.showAndWait().ifPresent(dialogButton -> {
				if (dialogButton == ButtonType.OK) {
					Progetto prog = controller.creaProgetto();
					progettiTotali.add(prog);
					listaProgettiTotali.getItems().add(prog);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void apriDettagliAvanzatiProgettoTab(ActionEvent event) {
		// Load the FXML file for the new scene
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/resources/DettagliAvanzatiProgettoPage.fxml"));
        Parent root;
		try {
			root = fxmlLoader.load();
			 // Create a new stage and set the scene
	        Stage newStage = new Stage();
	        newStage.setScene(new Scene(root));

	        // Show the new stage
	        newStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void caricaProgettiDaJson() {
		try (Reader reader = new FileReader(manager.getProgettoFilePath())){
			Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter()).create();
			Progetto[] progettiArray = gson.fromJson(reader, Progetto[].class);

			if (progettiArray != null) {
				listaProgettiTotali.getItems().addAll(progettiArray);
			}
			//reader.close();
		} catch (IOException ioExc) {
			ioExc.printStackTrace();
		} catch (NullPointerException nullPointer) {
			nullPointer.printStackTrace();
		}
	}

	public void salvaProgettiInJson() {
		List<Progetto> listaProgettiDaSalvare = new ArrayList<>(listaProgettiTotali.getItems());
		Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
				.setPrettyPrinting().create();
		try (Writer writer = new FileWriter(
				"C:\\Users\\Utente\\Desktop\\Scrivania\\Java\\Workspace\\Commesse_Informatiche\\output\\Progetti.json")) {
			//String json = gson.toJson(listaProgettiDaSalvare);
			gson.toJson(listaProgettiDaSalvare, writer);
			System.out.println("Progetti salvati.");
		} catch (IOException ioExc) {
			ioExc.printStackTrace();
		}
	}

	private static class LocalDateTypeAdapter extends TypeAdapter<LocalDate> {
		@Override
		public void write(JsonWriter out, LocalDate data) throws IOException {
			// String dataFineFormattata = formatDateToString(data);
			out.value(data.toString());
		}

		@Override
		public LocalDate read(JsonReader in) throws IOException {
			return LocalDate.parse(in.nextString());
		}
	}

	public List<Progetto> getProgettiTotali() {
		return progettiTotali;
	}
	public Image getIcon() {
		return icon;
	}
}

