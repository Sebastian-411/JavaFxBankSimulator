package com.example.labjavafx.model;

import com.example.labjavafx.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.ResourceBundle;

public class MovementController implements Initializable {

    @FXML
    private DatePicker date;

    @FXML
    private TextField description;

    @FXML
    private ChoiceBox<String> typeMovements;

    @FXML
    private TextField value;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> olist = FXCollections.observableArrayList();
        for (MovementType type : MovementType.values()) {
            olist.add(type.toString());
        }
        typeMovements.setItems(olist);
        typeMovements.setValue("Seleccione un tipo de movimiento");
    }

    @FXML
    public void registerMovement(ActionEvent event) {
        try {
            if(date.getValue() == null || description.getText().isEmpty() || typeMovements.getValue() == null || value.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error, campos vacios");
                alert.setContentText("Por favor ingrese todos los campos");
                alert.showAndWait();
            } else {
                Date dates = new Date();
                ZonedDateTime zonedDateTime = date.getValue().atStartOfDay(ZoneId.systemDefault());
                Date date = Date.from(zonedDateTime.toInstant());
                Movement movement = new Movement(description.getText(), Double.parseDouble(value.getText()), MovementType.valueOf(typeMovements.getValue()), dates);
                boolean status = MovementList.getInstance().addMovement(movement);
                if(!status){
                    throw new Exception();
                } else {
                    Stage stage = (Stage) this.date.getScene().getWindow();
                    stage.close();
                    HelloApplication.openWindow("Hello-view.fxml");
                }
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Por favor revisa los datos ingresados");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }

    }

    @FXML
    void turnBack(ActionEvent event) {
        Stage stage = (Stage) date.getScene().getWindow();
        stage.close();
        HelloApplication.openWindow("hello-view.fxml");
    }

}

