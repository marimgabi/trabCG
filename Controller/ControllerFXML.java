package Controller;

import Model.Aresta;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerFXML implements Initializable {

    Parent root;

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

    @FXML
    private TextField vrpx;

    @FXML
    private TextField vrpy;

    @FXML
    private TextField vrpz;

    @FXML
    private TextField px;

    @FXML
    private TextField py;

    @FXML
    private TextField pz;

    Stage stage = new Stage();

    ControllerMalha controllerMalha;
    boolean isFileOpen;


    //Método que faz tds as inicializações necessárias no início da execução do aplicativo
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        canvas1.widthProperty().addListener(evt -> drawall());
        canvas1.heightProperty().addListener(evt -> drawall());
        gc1= canvas1.getGraphicsContext2D();
        controllerMalha= new ControllerMalha(gc1);
        isFileOpen=false;
        vrpx.setText("0");
        vrpy.setText("0");
        vrpz.setText("0");
        px.setText("0");
        py.setText("0");
        pz.setText("0");


    }

//    public ControllerFXML(Parent root) {
//        this.root = root;
//    }

    public void drawall(){
        drawWireframe();
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
        atualizaParametros();
        drawWireframe();
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
        atualizaParametros();
        drawall();
    }


    public void suavizaMalha(){
        if(isFileOpen){
            controllerMalha.suavizaAlturas();
            drawall();
        }else{
            JOptionPane.showMessageDialog(null,"Não há arquivo aberto!");
        }
    }

    public void changevrp(){
        if(isFileOpen){
            controllerMalha.setVrp(Double.valueOf(vrpx.getText()),Double.valueOf(vrpy.getText()),Double.valueOf(vrpz.getText()));
            vrpx.setText(String.valueOf(controllerMalha.vrp.getX()));
            vrpy.setText(String.valueOf(controllerMalha.vrp.getY()));
            vrpz.setText(String.valueOf(controllerMalha.vrp.getZ()));
            //gc1.setStroke(Color.RED);
            drawall();
        }else{
            JOptionPane.showMessageDialog(null,"Não há arquivo aberto!");
        }
    }

    public void changep(){
        if(isFileOpen){
            controllerMalha.setP(Double.valueOf(px.getText()),Double.valueOf(py.getText()),Double.valueOf(pz.getText()));
            px.setText(String.valueOf(controllerMalha.p.getX()));
            py.setText(String.valueOf(controllerMalha.p.getY()));
            pz.setText(String.valueOf(controllerMalha.p.getZ()));
            //gc1.setStroke(Color.BLUE);
            drawall();
        }else{
            JOptionPane.showMessageDialog(null,"Não há arquivo aberto!");
        }
    }

    public void atualizaParametros(){
        vrpx.setText(String.valueOf(controllerMalha.vrp.getX()));
        vrpy.setText(String.valueOf(controllerMalha.vrp.getY()));
        vrpz.setText(String.valueOf(controllerMalha.vrp.getZ()));

        px.setText(String.valueOf(controllerMalha.p.getX()));
        py.setText(String.valueOf(controllerMalha.p.getY()));
        pz.setText(String.valueOf(controllerMalha.p.getZ()));
    }

    public void drawWireframe(){
        gc1.clearRect(0, 0, canvas1.getWidth(), canvas1.getHeight());
        //gc1.setStroke(Color.BLACK);
        if(isFileOpen){
            controllerMalha.sru2srtParallel();
            //gc1.strokeLine(0,0,gc1.getCanvas().getWidth(),gc1.getCanvas().getHeight());
            for(Aresta a : controllerMalha.malhaVisualizacao.getArestas()){
                gc1.strokeLine(a.getIni().getX(),a.getIni().getY(),a.getFim().getX(),a.getFim().getY());
            }
        }
    }



}
