package Controller;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerNewFXML implements Initializable {

    @FXML
    private TextField pontosX;

    @FXML
    private TextField pontosZ;

    @FXML
    private TextField espacoPontos;

    @FXML
    private TextField espacoPontosZ;

    @FXML
    private Button gerar;

    private ControllerFXML parentController;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pontosX.setText("0");
        pontosZ.setText("0");
        espacoPontos.setText("0");
    }

    public void setParentController(ControllerFXML parentController) {
        this.parentController = parentController;
    }

    public int getPontosX(){
        return Integer.parseInt(pontosX.getText());
    }

    public int getPontosZ(){
        return Integer.parseInt(pontosZ.getText());
    }

    public int getEspaco(){
        return Integer.parseInt(espacoPontos.getText());
    }

    public void passaNumeros(){
        parentController.novaMalha(Integer.parseInt(pontosX.getText()),Integer.parseInt(pontosZ.getText()),Integer.parseInt(espacoPontos.getText()),Integer.parseInt(espacoPontosZ.getText()));
        parentController.fechaNew();
    }

}
