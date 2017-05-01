package it.polito.tdp.dizionario.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionario.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioController {

	private Model model;
	
	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextArea txtResult;
	@FXML
	private TextField inputNumeroLettere;
	@FXML
	private TextField inputParola;
	@FXML
	private Button btnGeneraGrafo;
	@FXML
	private Button btnTrovaVicini;
	@FXML
	private Button btnTrovaGradoMax;

	@FXML
	void doReset(ActionEvent event) {
		model.doReset();
		txtResult.clear();
		inputNumeroLettere.clear();
		inputParola.clear();
	}

	@FXML
	void doGeneraGrafo(ActionEvent event) {
		int numeroLettere=0;
		try{
			numeroLettere=Integer.parseInt(inputNumeroLettere.getText());
		}catch(NumberFormatException nfe){
			txtResult.setText("Inserire un numero intero!");
			return;
		}

		try {
			List <String> grafo= model.createGraph(numeroLettere);
			if(grafo.size()==0){
				txtResult.setText("Nessuna parola trovata");
				return;	
			}
			txtResult.setText(""+grafo);
		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaGradoMax(ActionEvent event) {
		
		try {
			String risultato=model.findMaxDegree();
			txtResult.setText(risultato);

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}

	@FXML
	void doTrovaVicini(ActionEvent event) {
		String parolaInserita= inputParola.getText();
		try {
			List <String> risultati=model.displayNeighbours(parolaInserita);
			if (risultati.size()==0){
				txtResult.setText("Nessun vicino trovato!");
				return;
			}
			txtResult.setText(""+risultati);
			

		} catch (RuntimeException re) {
			txtResult.setText(re.getMessage());
		}
	}


	public void setModel(Model model) {
		this.model = model;
	}

	@FXML
	void initialize() {
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputNumeroLettere != null : "fx:id=\"inputNumeroLettere\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert inputParola != null : "fx:id=\"inputParola\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'Dizionario.fxml'.";
		assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaTutti\" was not injected: check your FXML file 'Dizionario.fxml'.";
	}
}