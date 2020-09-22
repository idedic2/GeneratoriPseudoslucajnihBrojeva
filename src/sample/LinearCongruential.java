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

public class LinearCongruential {
    public RadioButton radioWith;
    public RadioButton radioWithout;
    public Label lblSeed;
    public Label lblModul;
    public Label lblMultiplikator;
    public Label lblInkrement;
    public TextField fieldSeed;
    public TextField fieldModul;
    public TextField fieldMultiplikator;
    public TextField fieldInkrement;
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
    private boolean doesExist(ArrayList<Long>lista, Long value){
        for(Long element: lista){
            //System.out.println("Element: " + element + " value: " + value);
            if(element==value)
                return true;
        }
        return false;
    }
    public Thread getThread() {
        return thread;
    }

    public void radioChangeAction(ActionEvent actionEvent) {
        if (radioWithout.isSelected()) {
            lblSeed.setDisable(true);
            fieldSeed.setDisable(true);
            lblModul.setDisable(true);
            fieldModul.setDisable(true);
            lblMultiplikator.setDisable(true);
            fieldMultiplikator.setDisable(true);
            lblInkrement.setDisable(true);
            fieldInkrement.setDisable(true);

        } else {
            lblSeed.setDisable(false);
            fieldSeed.setDisable(false);
            lblModul.setDisable(false);
            fieldModul.setDisable(false);
            lblMultiplikator.setDisable(false);
            fieldMultiplikator.setDisable(false);
            lblInkrement.setDisable(false);
            fieldInkrement.setDisable(false);
        }
    }

    public void closeMiddleSquare(ActionEvent actionEvent) {
        Stage stage = (Stage) lblSeed.getScene().getWindow();
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
        fieldSeed.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (!fieldSeed.getText().trim().isEmpty()) {
                    if (!allDigits(fieldSeed.getText())) {
                        fieldSeed.getStyleClass().removeAll("ispravnoPolje");
                        fieldSeed.getStyleClass().add("neispravnoPolje");
                    } else {
                        fieldSeed.getStyleClass().removeAll("neispravnoPolje");
                        fieldSeed.getStyleClass().add("ispravnoPolje");
                    }
                } else {
                    fieldSeed.getStyleClass().removeAll("ispravnoPolje");
                    fieldSeed.getStyleClass().add("neispravnoPolje");
                }
            }
        });
        fieldModul.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (!fieldModul.getText().trim().isEmpty()) {
                    if (!allDigits(fieldModul.getText())) {
                        fieldModul.getStyleClass().removeAll("ispravnoPolje");
                        fieldModul.getStyleClass().add("neispravnoPolje");
                    } else {
                        fieldModul.getStyleClass().removeAll("neispravnoPolje");
                        fieldModul.getStyleClass().add("ispravnoPolje");
                    }
                } else {
                    fieldModul.getStyleClass().removeAll("ispravnoPolje");
                    fieldModul.getStyleClass().add("neispravnoPolje");
                }
            }
        });
        fieldMultiplikator.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (!fieldMultiplikator.getText().trim().isEmpty()) {
                    if (!allDigits(fieldMultiplikator.getText())) {
                        fieldMultiplikator.getStyleClass().removeAll("ispravnoPolje");
                        fieldMultiplikator.getStyleClass().add("neispravnoPolje");
                    } else {
                        fieldMultiplikator.getStyleClass().removeAll("neispravnoPolje");
                        fieldMultiplikator.getStyleClass().add("ispravnoPolje");
                    }
                } else {
                    fieldMultiplikator.getStyleClass().removeAll("ispravnoPolje");
                    fieldMultiplikator.getStyleClass().add("neispravnoPolje");
                }
            }
        });
        fieldInkrement.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (!fieldInkrement.getText().trim().isEmpty()) {
                    if (!allDigits(fieldInkrement.getText())) {
                        fieldInkrement.getStyleClass().removeAll("ispravnoPolje");
                        fieldInkrement.getStyleClass().add("neispravnoPolje");
                    } else {
                        fieldInkrement.getStyleClass().removeAll("neispravnoPolje");
                        fieldInkrement.getStyleClass().add("ispravnoPolje");
                    }
                } else {
                    fieldInkrement.getStyleClass().removeAll("ispravnoPolje");
                    fieldInkrement.getStyleClass().add("neispravnoPolje");
                }
            }
        });
    }


    public void generateAction(ActionEvent actionEvent) {
        if (radioWith.isSelected()) {
            if (fieldSeed.getText().trim().isEmpty()) {
                //sveOk = false;
                showAlert("Greška", "Unesite početnu vrijednost", Alert.AlertType.ERROR);
                return;
            }

            if (!allDigits(fieldSeed.getText())) {
                //sveOk = false;
                showAlert("Greška", "Početna vrijednost mora sadržavati isključivo cifre", Alert.AlertType.ERROR);
                return;
            }
            if (fieldModul.getText().trim().isEmpty()) {
                //sveOk = false;
                showAlert("Greška", "Unesite modul m", Alert.AlertType.ERROR);
                return;
            }

            if (!allDigits(fieldModul.getText())) {
                //sveOk = false;
                showAlert("Greška", "Modul m mora sadržavati isključivo cifre", Alert.AlertType.ERROR);
                return;
            }
            if (Long.parseLong(fieldModul.getText()) <= 0) {
                showAlert("Greška", "Modul m mora biti veći od 0", Alert.AlertType.ERROR);
                return;
            }
            if (fieldMultiplikator.getText().trim().isEmpty()) {
                //sveOk = false;
                showAlert("Greška", "Unesite multiplikator a", Alert.AlertType.ERROR);
                return;
            }

            if (!allDigits(fieldMultiplikator.getText())) {
                //sveOk = false;
                showAlert("Greška", "Multiplikator a mora sadržavati isključivo cifre", Alert.AlertType.ERROR);
                return;
            }
            if (Long.parseLong(fieldMultiplikator.getText()) <= 0 || Long.parseLong(fieldMultiplikator.getText()) >= Long.parseLong(fieldModul.getText())) {
                showAlert("Greška", "Mora vrijediti 0<a<m", Alert.AlertType.ERROR);
                return;
            }
            if (fieldInkrement.getText().trim().isEmpty()) {
                //sveOk = false;
                showAlert("Greška", "Unesite inkrement c", Alert.AlertType.ERROR);
                return;
            }
            if (!allDigits(fieldInkrement.getText())) {
                //sveOk = false;
                showAlert("Greška", "Inkrement c mora sadržavati isključivo cifre", Alert.AlertType.ERROR);
                return;
            }
            if (Long.parseLong(fieldInkrement.getText()) < 0 || Long.parseLong(fieldInkrement.getText()) >= Long.parseLong(fieldModul.getText())) {
                showAlert("Greška", "Mora vrijediti 0<=c<m", Alert.AlertType.ERROR);
                return;
            }

            if (Long.parseLong(fieldSeed.getText()) < 0 || Long.parseLong(fieldSeed.getText()) >= Long.parseLong(fieldModul.getText())) {
                showAlert("Greška", "Mora vrijediti 0<=sjeme<m", Alert.AlertType.ERROR);
                return;
            }
            thread = new Thread(() -> {
                while (true) {
                    boolean stopThread = false;
                    active=true;
                    Long sjeme = Long.parseLong(fieldSeed.getText());
                    ArrayList<Long> list = new ArrayList<>();

                    while (true) {
                        Long pom = (sjeme * Long.parseLong(fieldMultiplikator.getText())) + Long.parseLong(fieldInkrement.getText());
                        Long iduca = pom % Long.parseLong(fieldModul.getText());
                        if (doesExist(list, iduca)){
                            stopThread = true;
                            break;
                        }
                        list.add(iduca);
                        sjeme = iduca;
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
        }else {
            if(active)
            thread.stop();
            Long sjeme = 0L;
            ArrayList<Long>list=new ArrayList<>();
            while(true){
                Long pom=(sjeme*4)+1;
                Long iduca=pom%9;
                if (doesExist(list, iduca))
                break;
                list.add(iduca);
                sjeme=iduca;
            }
            textareaNumbers.setText(list.toString());
        }

    }
}
