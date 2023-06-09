package com.example.labjavafx.model;

import com.example.labjavafx.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    private boolean showIncomes = true;
    private boolean showExpenses = true;
    @FXML
    private Label balance;
    @FXML
    private TableColumn<Movement, Date> date;

    @FXML
    private TableView<Movement> movementsTable;


    @FXML
    private TableColumn<Movement, String> description;

    @FXML
    private Button expenses;

    @FXML
    private Button income;

    @FXML
    private TableColumn<Movement, MovementType> movement;

    @FXML
    private TableColumn<Movement, Double> value;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        movement.setCellValueFactory(new PropertyValueFactory<>("type"));
        value.setCellValueFactory(new PropertyValueFactory<>("amount"));
        ObservableList<Movement> movements = MovementList.getInstance().getMovements();
        movements.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));
        movementsTable.setItems(movements);
        movementsTable.setRowFactory(tv -> new javafx.scene.control.TableRow<Movement>() {
            @Override
            protected void updateItem(Movement item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null) {
                    setStyle("");
                } else if (item.getType() == MovementType.INCOME) {
                    setStyle("-fx-background-color: #9AAB64FF;");
                } else {
                    setStyle("-fx-background-color: #F3B98CFF;");
                }
            }
        });
        income.setBackground(Background.fill(Color.rgb(154, 171, 100)));
        expenses.setBackground(Background.fill(Color.rgb(154, 171, 100)));
        balance.setText(String.valueOf(MovementList.getInstance().getBalanced()));
    }

    @FXML
    void exit(ActionEvent event) {
        System.exit(0);
    }
    @FXML
    void registerMovement(ActionEvent event) {
        Stage stage = (Stage) expenses.getScene().getWindow();
        stage.close();
        HelloApplication.openWindow("registerBankMovement.fxml");
    }
    @FXML
    void showExpenses(ActionEvent event) {
        showExpenses = !showExpenses;
        if(showExpenses && showIncomes){
            income.setBackground(Background.fill(Color.rgb(154, 171, 100)));
            expenses.setBackground(Background.fill(Color.rgb(154, 171, 100)));
            movementsTable.setItems(MovementList.getInstance().getMovements());
        } else if (showIncomes){
            income.setBackground(Background.fill(Color.rgb(154, 171, 100)));
            expenses.setBackground(Background.fill(Color.rgb(243, 185, 140)));
            movementsTable.setItems(MovementList.getInstance().getMovementsByType(MovementType.INCOME));
        } else if(showExpenses){
            income.setBackground(Background.fill(Color.rgb(243, 185, 140)));
            expenses.setBackground(Background.fill(Color.rgb(154, 171, 100)));
            movementsTable.setItems(MovementList.getInstance().getMovementsByType(MovementType.EXPENSES));

        } else{
            income.setBackground(Background.fill(Color.rgb(243, 185, 140)));
            expenses.setBackground(Background.fill(Color.rgb(243, 185, 140)));
            movementsTable.setItems(FXCollections.observableArrayList());
        }
    }

    @FXML
    void showIncome(ActionEvent event) {
        showIncomes = !showIncomes;
        if(showExpenses && showIncomes){
            income.setBackground(Background.fill(Color.rgb(154, 171, 100)));
            expenses.setBackground(Background.fill(Color.rgb(154, 171, 100)));
            movementsTable.setItems(MovementList.getInstance().getMovements());
        } else if (showIncomes){
            income.setBackground(Background.fill(Color.rgb(154, 171, 100)));
            expenses.setBackground(Background.fill(Color.rgb(243, 185, 140)));
            movementsTable.setItems(MovementList.getInstance().getMovementsByType(MovementType.INCOME));
        } else if(showExpenses){
            income.setBackground(Background.fill(Color.rgb(243, 185, 140)));
            expenses.setBackground(Background.fill(Color.rgb(154, 171, 100)));
            movementsTable.setItems(MovementList.getInstance().getMovementsByType(MovementType.EXPENSES));

        } else{
            income.setBackground(Background.fill(Color.rgb(243, 185, 140)));
            expenses.setBackground(Background.fill(Color.rgb(243, 185, 140)));
            movementsTable.setItems(FXCollections.observableArrayList());
        }
    }

}
