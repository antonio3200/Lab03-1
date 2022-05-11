/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.Richword;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	private Dictionary dizionario;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbLingua"
    private ComboBox<String> cmbLingua; // Value injected by FXMLLoader

    @FXML // fx:id="txtDaCorreggere"
    private TextArea txtDaCorreggere; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorreggi"
    private Button btnCorreggi; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorretto"
    private TextArea txtCorretto; // Value injected by FXMLLoader

    @FXML // fx:id="lblErrori"
    private Label lblErrori; // Value injected by FXMLLoader

    @FXML // fx:id="btnPulisci"
    private Button btnPulisci; // Value injected by FXMLLoader

    @FXML // fx:id="lblTempo"
    private Label lblTempo; // Value injected by FXMLLoader
    
    

    @FXML
    void doActivation(ActionEvent event) {
if (cmbLingua.getValue() !=null) {
    		
    		txtDaCorreggere.setDisable(false);
			txtCorretto.setDisable(false);
			btnCorreggi.setDisable(false);
			btnPulisci.setDisable(false);
			txtDaCorreggere.clear();
			txtCorretto.clear();
    		
    	}else {
    		
			txtDaCorreggere.setDisable(true);
			txtCorretto.setDisable(true);
			btnCorreggi.setDisable(true);
			btnPulisci.setDisable(true);
			txtDaCorreggere.setText("Seleziona una lingua!");
    		
    	}
    }

    @FXML
    void doCorreggi(ActionEvent event) {
    	txtCorretto.clear();
    	LinkedList<String>inputTesto= new LinkedList<String>();
    	if(cmbLingua.getValue().equals(null)) {
    		txtCorretto.setText("Selezionare una lingua");
    		return;
    	}
    	if(!dizionario.loadDictionary(cmbLingua.getValue())) {
    		txtCorretto.setText("Problemi di caricamento del dizionario");
    		return;
    	}
    	String testoUtente= txtDaCorreggere.getText();
    	if(testoUtente.isEmpty()) {
    		txtCorretto.setText("Scrivi qualcosa caspiterina");
    		return;
    	}
    	testoUtente.replaceAll("\n", " ");
    	testoUtente.replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]]", "");
    	StringTokenizer st = new StringTokenizer(testoUtente," ");
    	while(st.hasMoreTokens()) {
    		inputTesto.add(st.nextToken());
    	}
    	long start= System.nanoTime();
    	List<Richword> testoOutput;
    	testoOutput= dizionario.spellCheckText(inputTesto);
    	long end= System.nanoTime();
    	int numErrori=0;
    	StringBuilder testoCorretto= new StringBuilder();
    	for(Richword r:testoOutput) {
    		if(!r.isCorretta()) {
    			numErrori++;
    		testoCorretto.append(r.getParola()+"\n");
    		}
    	}
    	txtCorretto.setText(testoCorretto.toString());
    	lblTempo.setText("Controllo ortografico completato in "+ (end-start)/1E9+ " secondi");
    	lblErrori.setText("Il testo trascritto presenta "+numErrori+" errori");
    }

    @FXML
    void doPulisci(ActionEvent event) {
    	txtCorretto.clear();
    	txtDaCorreggere.clear();
    	lblErrori.setText("Numero di Errori: ");
    	lblTempo.setText("Stato tempistiche: ");
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbLingua != null : "fx:id=\"cmbLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDaCorreggere != null : "fx:id=\"txtDaCorreggere\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorreggi != null : "fx:id=\"btnCorreggi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorretto != null : "fx:id=\"txtCorretto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnPulisci != null : "fx:id=\"btnPulisci\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Dictionary model) {
    	txtDaCorreggere.setDisable(true);
    	txtDaCorreggere.setText("Choose a Language");
    	txtCorretto.setDisable(true);
    	cmbLingua.getItems().addAll("Italian","English");
    	btnCorreggi.setDisable(true);
    	btnPulisci.setDisable(true);
    	this.dizionario=model; 
    	
    }
}


