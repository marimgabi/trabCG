package Controller;

import Model.ResizableCanvas;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerFXML implements Initializable {

    @FXML
    private ResizableCanvas canvas1;
    GraphicsContext gc1;

    //Método que faz tds as inicializações necessárias no início da execução do aplicativo
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        canvas1.widthProperty().addListener(evt -> drawall());
        canvas1.heightProperty().addListener(evt -> drawall());
        gc1= canvas1.getGraphicsContext2D();
    }

    public void drawall(){

    }
}
