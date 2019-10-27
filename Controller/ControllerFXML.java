package Controller;

import Model.ResizableCanvas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFXML implements Initializable {

    @FXML
    private ResizableCanvas canvas1;
    GraphicsContext gc1;

    @FXML
    private MenuItem novo;

    @FXML
    private ControllerNewFXML childController;

    Stage stage = new Stage();

    ControllerMalha controllerMalha= new ControllerMalha();


    //Método que faz tds as inicializações necessárias no início da execução do aplicativo
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        canvas1.widthProperty().addListener(evt -> drawall());
        canvas1.heightProperty().addListener(evt -> drawall());
        gc1= canvas1.getGraphicsContext2D();

    }

    public void drawall(){

    }

    //Abre janela para gerar nova malha
    public void abreNew() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/new.fxml"));
        Parent rootl = (Parent) loader.load();
        childController = loader.getController();
        childController.setParentController(this);
        stage.setTitle("Gerar nova malha");
        stage.setScene(new Scene(rootl));
        stage.show();

    }

    //Cria uma nova malha
    public void novaMalha(int pontosX, int pontosZ, int espaco, int espaco1){
        controllerMalha.criaMalha(pontosX,pontosZ,espaco,espaco1);
    }

    //Fecha a janela da nova malha
    public void fechaNew(){
        stage.close();
    }


}
