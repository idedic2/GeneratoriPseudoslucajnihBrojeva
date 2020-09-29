package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class BlumBlumShubController {
    public RadioButton radioWith;
    public RadioButton radioWithout;
    public Label lblSeed;
    public Label lblP;
    public Label lblQ;
    public TextField fieldSeed;
    public TextField fieldP;
    public TextField fieldQ;
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
    public void btnIzrazAction(ActionEvent actionEvent){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Blum Blum Shub");
        alert.setHeaderText("BBS algoritam se može zapisati kao:");
        Image image = new Image(getClass().getResource("/images/BBS.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        alert.setGraphic(imageView);
        alert.setContentText("");
        alert.showAndWait();
    }

    public Thread getThread() {
        return thread;
    }

    public void radioChangeAction(ActionEvent actionEvent) {
        if (radioWithout.isSelected()) {
            lblSeed.setDisable(true);
            fieldSeed.setDisable(true);
            lblP.setDisable(true);
            fieldP.setDisable(true);
            lblQ.setDisable(true);
            fieldQ.setDisable(true);


        } else {
            lblSeed.setDisable(false);
            fieldSeed.setDisable(false);
            lblP.setDisable(false);
            fieldP.setDisable(false);
            lblQ.setDisable(false);
            fieldQ.setDisable(false);
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

    private boolean isPrime(Long number) {
        int i = 2;
        boolean flag = false;
        while (i <= number / 2) {
            // condition for nonprime number
            if (number % i == 0) {
                flag = true;
                break;
            }

            ++i;
        }

        if (!flag)
            return true;
        return false;
    }

    private Long gcd(Long a, Long b) {
        // Everything divides 0
        if (a == 0)
            return b;
        if (b == 0)
            return a;

        // base case
        if (a == b)
            return a;

        // a is greater
        if (a > b)
            return gcd((a - b), b);
        return gcd(a, (b - a));
    }
    private boolean doesExist(ArrayList<Long>lista, Long value){
        for(Long element: lista){
            //System.out.println("Element: " + element + " value: " + value);
            if(element==value)
                return true;
        }
        return false;
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
        fieldP.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (!fieldP.getText().trim().isEmpty()) {
                    if (!allDigits(fieldP.getText())) {
                        fieldP.getStyleClass().removeAll("ispravnoPolje");
                        fieldP.getStyleClass().add("neispravnoPolje");
                    } else {
                        fieldP.getStyleClass().removeAll("neispravnoPolje");
                        fieldP.getStyleClass().add("ispravnoPolje");
                    }
                } else {
                    fieldP.getStyleClass().removeAll("ispravnoPolje");
                    fieldP.getStyleClass().add("neispravnoPolje");
                }
            }
        });
        fieldQ.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String o, String n) {
                if (!fieldQ.getText().trim().isEmpty()) {
                    if (!allDigits(fieldQ.getText())) {
                        fieldQ.getStyleClass().removeAll("ispravnoPolje");
                        fieldQ.getStyleClass().add("neispravnoPolje");
                    } else {
                        fieldQ.getStyleClass().removeAll("neispravnoPolje");
                        fieldQ.getStyleClass().add("ispravnoPolje");
                    }
                } else {
                    fieldQ.getStyleClass().removeAll("ispravnoPolje");
                    fieldQ.getStyleClass().add("neispravnoPolje");
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
            if (fieldP.getText().trim().isEmpty()) {
                //sveOk = false;
                showAlert("Greška", "Unesite parametar p", Alert.AlertType.ERROR);
                return;
            }

            if (!allDigits(fieldP.getText())) {
                //sveOk = false;
                showAlert("Greška", "Parametar p mora sadržavati isključivo cifre", Alert.AlertType.ERROR);
                return;
            }
            if (fieldQ.getText().trim().isEmpty()) {
                //sveOk = false;
                showAlert("Greška", "Unesite parametar q", Alert.AlertType.ERROR);
                return;
            }

            if (!allDigits(fieldQ.getText())) {
                //sveOk = false;
                showAlert("Greška", "Parametar q mora sadržavati isključivo cifre", Alert.AlertType.ERROR);
                return;
            }
            if(Long.parseLong(fieldP.getText())==Long.parseLong(fieldQ.getText())){
                showAlert("Greška", "Parametri p i q moraju biti različiti", Alert.AlertType.ERROR);
                return;
            }
            if(!isPrime(Long.parseLong(fieldP.getText()))){
                showAlert("Greška", "Parametar p mora biti prost broj", Alert.AlertType.ERROR);
                return;
            }
            if(!isPrime(Long.parseLong(fieldQ.getText()))){
                showAlert("Greška", "Parametar q mora biti prost broj", Alert.AlertType.ERROR);
                return;
            }
            if(!(Long.parseLong(fieldP.getText())%4==3 && Long.parseLong(fieldQ.getText())%4==3)){
                showAlert("Greška", "Parametri p i q moraju biti kongruentni po modulu 4", Alert.AlertType.ERROR);
                return;
            }
            if(Long.parseLong(fieldSeed.getText())==0 || Long.parseLong(fieldSeed.getText())==1){
                showAlert("Greška", "Početna vrijednost (sjeme) ne smije biti 0 ili 1", Alert.AlertType.ERROR);
                return;
            }
            Long M=Long.parseLong(fieldP.getText())*Long.parseLong(fieldQ.getText());
            if(gcd(Long.parseLong(fieldSeed.getText()), M)!=1){
                showAlert("Greška", "NZD(početna vrijednost, p*q) mora biti 1", Alert.AlertType.ERROR);
                return;
            }

            thread = new Thread(() -> {
                while (true) {
                    boolean stopThread = false;
                    active=true;
            ArrayList<Long> list = new ArrayList<>();
            Long sjeme = Long.parseLong(fieldSeed.getText());
            while (true) {
                Long pom=sjeme*sjeme;
                Long iduca=pom%M;
                if(doesExist(list, iduca)){
                    stopThread = true;
                    break;
                }
                list.add(iduca);
                sjeme=iduca;
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
            int time= LocalDateTime.now().getSecond();
            Long sjeme = 5L+time;
            Long p=7L;
            Long q=11L;
            Long M=p*q;
            while (true) {
                Long pom=sjeme*sjeme;
                Long iduca=pom%M;
                if(doesExist(list, iduca))break;
                list.add(iduca);
                sjeme=iduca;
            }
            textareaNumbers.setText(list.toString());

        }

    }
}
