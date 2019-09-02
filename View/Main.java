package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

//        Classe main q carrega a visualização que é o arquivo fxml

        Parent p= FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene= new Scene(p);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Trabalho de CG");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
