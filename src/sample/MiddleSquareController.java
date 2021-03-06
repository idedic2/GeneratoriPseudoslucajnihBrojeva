package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
public class MiddleSquareController {
    public RadioButton radioWith;
    public RadioButton radioWithout;
    public Label lblText;
    public TextField fieldSeed;
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

    public void radioChange(ActionEvent actionEvent) {
        if (radioWithout.isSelected()) {
            lblText.setDisable(true);
            fieldSeed.setDisable(true);
        } else {
            lblText.setDisable(false);
            fieldSeed.setDisable(false);
        }
    }

    public void closeMiddleSquare(ActionEvent actionEvent) {
        Stage stage = (Stage) lblText.getScene().getWindow();
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

    public String middleSquare(String sjeme) {
        String stringSjeme = sjeme.toString();
        int brojCifara = stringSjeme.length();
        Long kvadriranoSjeme = Long.parseLong(sjeme) * Long.parseLong(sjeme);
        String kvadriranoSjemeString = kvadriranoSjeme.toString();
        String novoSjeme = "";
        if (kvadriranoSjemeString.length() == (brojCifara * 2)) {
            novoSjeme = kvadriranoSjemeString.substring(brojCifara / 2, brojCifara + brojCifara / 2);
        } else {
            while(kvadriranoSjemeString.length() != (brojCifara * 2))
            kvadriranoSjemeString = "0" + kvadriranoSjemeString;
            novoSjeme = kvadriranoSjemeString.substring(brojCifara / 2, brojCifara + brojCifara / 2);
        }
        return novoSjeme;
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

    }
    private boolean doesExist(ArrayList<String>lista, String value){
        for(String element: lista){
            //System.out.println("Element: " + element + " value: " + value);
            if(element.equals(value))
                return true;
        }
        return false;
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

            thread = new Thread(() -> {
                while (true) {
                    boolean stopThread = false;
                    active=true;
                    ArrayList<String> list = new ArrayList<>();
                    String sjeme = fieldSeed.getText();
                    while (true) {
                        String rez = middleSquare(sjeme);
                        if (doesExist(list, rez)) {
                            stopThread = true;
                            break;
                        }
                    list.add(rez);
                    sjeme = rez;
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
                    ArrayList<String> list = new ArrayList<>();
                int time= LocalDateTime.now().getSecond();
                String sjeme = String.valueOf(675248+time);
                while (true) {
                    String rez = middleSquare(sjeme);
                    if(doesExist(list, rez))
                        break;
                    list.add(rez);
                    sjeme = rez;
                }
                textareaNumbers.setText(list.toString());

            }

        }
    }
