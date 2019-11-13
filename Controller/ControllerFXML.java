package Controller;

import Model.ResizableCanvas;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerFXML implements Initializable {

    @FXML
    private ResizableCanvas canvas1;
    GraphicsContext gc1;

    @FXML
    private MenuItem novo;

    @FXML
    private MenuItem save;

    @FXML
    private MenuItem open;

    @FXML
    private ControllerNewFXML childController;

    @FXML
    private Button suaviza;

    Stage stage = new Stage();

    ControllerMalha controllerMalha= new ControllerMalha();
    boolean isFileOpen;


    //Método que faz tds as inicializações necessárias no início da execução do aplicativo
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        canvas1.widthProperty().addListener(evt -> drawall());
        canvas1.heightProperty().addListener(evt -> drawall());
        gc1= canvas1.getGraphicsContext2D();
        isFileOpen=false;
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
        //controllerMalha.criaMalha(pontosX,pontosZ,espaco,espaco1);
        controllerMalha.criaMalha(pontosX,pontosZ,espaco,espaco1);
        isFileOpen=true;
    }

    //Fecha a janela da nova malha
    public void fechaNew(){
        stage.close();
    }

    public void salvaHeightmap() throws FileNotFoundException {
        if(isFileOpen){
            FileChooser chooser = new FileChooser();
            chooser.setTitle("Salvar Heightmap");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ADAIR files (*.adair)", "*.adair");
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            chooser.getExtensionFilters().add(extFilter);
            File file = chooser.showSaveDialog(stage);
            if(file!=null){
                PrintWriter writer;
                writer = new PrintWriter(file);
                writer.println(controllerMalha.criaHeightmap());
                writer.close();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Não há arquivo aberto!");
        }
    }

    public void carregaHeightmap() throws IOException {
        controllerMalha.malha=null;
        controllerMalha.malhaVisualizacao=null;
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Abrir Heightmap");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("ADAIR files (*.adair)", "*.adair");
        chooser.getExtensionFilters().add(extFilter);
        File file = chooser.showOpenDialog(stage);
        //BufferedReader arq = new BufferedReader(file);
        if(file!=null){
            StringBuilder stringBuffer = new StringBuilder();
            BufferedReader bufferedReader = null;

                bufferedReader = new BufferedReader(new FileReader(file));

                String text;
                while ((text = bufferedReader.readLine()) != null) {
                    stringBuffer.append(text+";");
                }
                controllerMalha.leHeightmap(stringBuffer.toString());
                isFileOpen=true;
        }
    }


    public void suavizaMalha(){
        if(isFileOpen){
            controllerMalha.suavizaAlturas();
        }else{
            JOptionPane.showMessageDialog(null,"Não há arquivo aberto!");
        }
    }

}
