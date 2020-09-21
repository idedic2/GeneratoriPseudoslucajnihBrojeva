package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LaggedFibonacci {
    public RadioButton radioWith;
    public RadioButton radioWithout;
    public Label lblJ;
    public Label lblK;
    public TextField fieldJ;
    public TextField fieldK;
    public TextArea textareaNumbers;
    private Thread thread;
    private boolean active=false;
    private boolean allDigits(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    private void showAlert(String title, String headerText, Alert.AlertType type) {
        Alert error = new Alert(type);
        error.setTitle(title);
        error.setHeaderText(headerText);
        error.show();
    }

    public Thread getThread() {
        return thread;
    }

    public void radioChangeAction(ActionEvent actionEvent) {
        if (radioWithout.isSelected()) {
            lblJ.setDisable(true);
            fieldJ.setDisable(true);
            lblK.setDisable(true);
            fieldK.setDisable(true);


        } else {
            lblJ.setDisable(false);
            fieldJ.setDisable(false);
            lblK.setDisable(false);
            fieldK.setDisable(false);
        }
    }

    public void closeMiddleSquare(ActionEvent actionEvent) {
        Stage stage = (Stage) lblK.getScene().getWindow();
        stage.close();
        Stage oldstage = new Stage();
        Parent root = null;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start.fxml"));
            StartController startController = new StartController();
            loader.setController(startController);
            root = loader.load();
            oldstage.setTitle("Dobrodošli");
            oldstage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            oldstage.setResizable(false);
            oldstage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @FXML
    public void initialize() {
        fieldJ.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (!fieldJ.getText().trim().isEmpty()) {
                    if (!allDigits(fieldJ.getText())) {
                        fieldJ.getStyleClass().removeAll("ispravnoPolje");
                        fieldJ.getStyleClass().add("neispravnoPolje");
                    } else {
                        fieldJ.getStyleClass().removeAll("neispravnoPolje");
                        fieldJ.getStyleClass().add("ispravnoPolje");
                    }
                } else {
                    fieldJ.getStyleClass().removeAll("ispravnoPolje");
                    fieldJ.getStyleClass().add("neispravnoPolje");
                }
            }
        });
        fieldK.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (!fieldK.getText().trim().isEmpty()) {
                    if (!allDigits(fieldK.getText())) {
                        fieldK.getStyleClass().removeAll("ispravnoPolje");
                        fieldK.getStyleClass().add("neispravnoPolje");
                    } else {
                        fieldK.getStyleClass().removeAll("neispravnoPolje");
                        fieldK.getStyleClass().add("ispravnoPolje");
                    }
                } else {
                    fieldK.getStyleClass().removeAll("ispravnoPolje");
                    fieldK.getStyleClass().add("neispravnoPolje");
                }
            }
        });

    }
    private boolean doesExist(ArrayList<Long>lista, Long value){
        for(Long element: lista){
            //System.out.println("Element: " + element + " value: " + value);
            if(element==value)
                return true;
        }
        return false;
    }



    public void generateAction(ActionEvent actionEvent) {
        //boolean radioSelected = false;

        /*if (radioWith.isSelected()) {
            //radioSelected = true;
            if (fieldJ.getText().trim().isEmpty()) {
                //sveOk = false;
                showAlert("Greška", "Unesite parametar j", Alert.AlertType.ERROR);
                return;
            }

            if (!allDigits(fieldJ.getText())) {
                //sveOk = false;
                showAlert("Greška", "Parametar j mora sadržavati isključivo cifre", Alert.AlertType.ERROR);
                return;
            }
            if (fieldK.getText().trim().isEmpty()) {
                //sveOk = false;
                showAlert("Greška", "Unesite parametar k", Alert.AlertType.ERROR);
                return;
            }

            if (!allDigits(fieldK.getText())) {
                //sveOk = false;
                showAlert("Greška", "Parametar k mora sadržavati isključivo cifre", Alert.AlertType.ERROR);
                return;
            }

            if(Long.parseLong(fieldJ.getText())<=0 || Long.parseLong(fieldJ.getText())>=Long.parseLong(fieldK.getText())){
                //sveOk = false;
                showAlert("Greška", "Mora vrijediti 0<j<k", Alert.AlertType.ERROR);
                return;
            }

        } else {

        }


        thread = new Thread(() -> {
            boolean stopThread = false;
            ArrayList<Long> list = new ArrayList<>();
            LFGModel fibonaci=null;

            if (radioWith.isSelected()) {
                fibonaci=new LFGModel(false);
                fibonaci.setJ(Long.parseLong(fieldJ.getText()));
                fibonaci.setK(Long.parseLong(fieldK.getText()));
            }
            else{
                fibonaci=new LFGModel(true);
            }

            while (true) {
                Long pom = 0L;
                while (true){
                    Long rez=fibonaci.GenerateNextRandomNumber();
                    if(list.size()>=500){
                        stopThread = true;
                        break;
                    }
                    if(doesExist(list, rez)){
                        System.out.println("IF IF IF");
                        stopThread = true;
                        break;
                    }
                    list.add(rez);
                    pom=rez;
                }
                textareaNumbers.setText(list.toString());

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    break;
                }
                if (stopThread) break;
            }
        });
        thread.start();
         */
        if (radioWith.isSelected()) {

            if (fieldJ.getText().trim().isEmpty()) {
                //sveOk = false;
                showAlert("Greška", "Unesite parametar j", Alert.AlertType.ERROR);
                return;
            }

            if (!allDigits(fieldJ.getText())) {
                //sveOk = false;
                showAlert("Greška", "Parametar j mora sadržavati isključivo cifre", Alert.AlertType.ERROR);
                return;
            }
            if (fieldK.getText().trim().isEmpty()) {
                //sveOk = false;
                showAlert("Greška", "Unesite parametar k", Alert.AlertType.ERROR);
                return;
            }

            if (!allDigits(fieldK.getText())) {
                //sveOk = false;
                showAlert("Greška", "Parametar k mora sadržavati isključivo cifre", Alert.AlertType.ERROR);
                return;
            }

            if(Long.parseLong(fieldJ.getText())<=0 || Long.parseLong(fieldJ.getText())>=Long.parseLong(fieldK.getText())){
                //sveOk = false;
                showAlert("Greška", "Mora vrijediti 0<j<k", Alert.AlertType.ERROR);
                return;
            }

            thread = new Thread(() -> {
                boolean stopThread = false;
                active=true;
                ArrayList<Long> list = new ArrayList<>();
                LFGModel fibonaci=new LFGModel(false);
                fibonaci.setJ(Long.parseLong(fieldJ.getText()));
                fibonaci.setK(Long.parseLong(fieldK.getText()));

                while (true) {

                    while (true) {
                        Long rez = fibonaci.GenerateNextRandomNumber();
                        System.out.println(rez);
                        if (doesExist(list, rez)) {
                            stopThread = true;
                            break;
                        }
                        if(list.size()>=500){
                            stopThread = true;
                            break;
                        }
                        list.add(rez);
                    }
                    textareaNumbers.setText(list.toString());

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                        break;
                    }
                    if (stopThread) break;
                }
            });
            thread.start();

        }
        else {

            if(active)
                thread.stop();
            ArrayList<Long> list = new ArrayList<>();
            LFGModel fibonaci=new LFGModel(true);
            while (true) {
                Long rez = fibonaci.GenerateNextRandomNumber();

                if (doesExist(list, rez))
                    break;
                if(list.size()>=500)break;
                list.add(rez);

            }
            textareaNumbers.setText(list.toString());

        }

    }
    }

