package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
public class StartController {
    public ChoiceBox<String>choiceAlgorithm;

    public void confirmAlgorithmAction(ActionEvent actionEvent){
        if(choiceAlgorithm.getSelectionModel().getSelectedItem().equals("Middle Square")){
            Stage stage = (Stage) choiceAlgorithm.getScene().getWindow();
            Parent root = null;
            try {
                stage.close();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/middleSquare.fxml"));
                root = loader.load();
                primaryStage.setTitle("Middle Square algoritam");
                primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(choiceAlgorithm.getSelectionModel().getSelectedItem().equals("Blum Blum Shub")){
            Stage stage = (Stage) choiceAlgorithm.getScene().getWindow();
            Parent root = null;
            try {
                stage.close();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/blumBlumShub.fxml"));
                root = loader.load();
                primaryStage.setTitle("Blum Blum Shub algoritam");
                primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Stage stage = (Stage) choiceAlgorithm.getScene().getWindow();
            Parent root = null;
            try {
                stage.close();
                Stage primaryStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/linearCongruential.fxml"));
                root = loader.load();
                primaryStage.setTitle("Linear Congruential algoritam");
                primaryStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                primaryStage.initModality(Modality.APPLICATION_MODAL);
                primaryStage.setResizable(false);
                primaryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
