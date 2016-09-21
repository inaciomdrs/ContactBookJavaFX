package br.ufrn.imd.address.view;

import java.sql.Date;
import java.time.LocalDate;

import br.ufrn.imd.address.MainApp;
import br.ufrn.imd.address.model.Person;
import br.ufrn.imd.address.util.DateUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class PersonOverviewController {
	@FXML
	private TableView<Person> personTable;
	@FXML
	private TableColumn<Person, String> firstNameColumn;
	@FXML
	private TableColumn<Person, String> lastNameColumn;

	@FXML
	private Label firstNameLabel;
	@FXML
	private Label lastNameLabel;
	@FXML
	private Label streetLabel;
	@FXML
	private Label postalCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label birthdayLabel;

	// Reference to the main application.
	private MainApp mainApp;

	public PersonOverviewController() {
	}

	@FXML
	private void initialize() {
		// Inicializa a tablea de pessoa com duas colunas.
		firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
		lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

		// Limpa os detalhes da pessoa.
		showPersonDetails(null);

		// Detecta mudanças de seleção e mostra os detalhes da pessoa quando
		// houver mudança.
		personTable.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> showPersonDetails(newValue));
	}

	/**
	 * É chamado pela aplicação principal para dar uma referência de volta a si
	 * mesmo.
	 * 
	 * @param mainApp
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;

		// Adiciona os dados da observable list na tabela
		personTable.setItems(mainApp.getPersonData());
	}

	public void showPersonDetails(Person person) {
		if (person != null) {
			firstNameLabel.setText(person.getFirstName());
			lastNameLabel.setText(person.getLastName());
			streetLabel.setText(person.getStreet());
			postalCodeLabel.setText(String.valueOf(person.getPostalCode()));
			cityLabel.setText(person.getCity());
			birthdayLabel.setText(DateUtil.format(person.getBirthday()));
		} else {
			firstNameLabel.setText("");
			lastNameLabel.setText("");
			streetLabel.setText("");
			postalCodeLabel.setText("");
			cityLabel.setText("");
			birthdayLabel.setText("");
		}
	}
}
