package com.example.labjavafx.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Date;

public class MovementList {
    private ObservableList<Movement> movements = FXCollections.observableArrayList();
    private double balanced = 0;

    private static MovementList instance = null;

    public static MovementList getInstance() {
        if(instance == null){
            instance = new MovementList();
        }
        return instance;
    }
    public ObservableList<Movement> getMovements() {
        return movements;
    }

    public double getBalanced() {
        double balancedI = 0;
        ObservableList<Movement> movementsI = getMovementsByType(MovementType.INCOME);
        double balancedE = 0;
        ObservableList<Movement> movementsE = getMovementsByType(MovementType.EXPENSES);
        for (Movement movement : movementsI) {
            balancedI += movement.getAmount();
        }
        for (Movement movement : movementsE) {
            balancedE += movement.getAmount();
        }

        return balancedI - balancedE;
    }

    public void setMovements(ObservableList<Movement> movements) {
        this.movements = movements;
    }

    public static void setInstance(MovementList instance) {
        MovementList.instance = instance;
    }

    public boolean addMovement(Movement movement){
        return movements.add(movement);
    }

    public ObservableList<Movement> getMovementsByType(MovementType type){
        ObservableList<Movement> movementsByType = FXCollections.observableArrayList();
        for (Movement movement : movements) {
            if(movement.getType().equals(type)){
                movementsByType.add(movement);
            }
        }
        return movementsByType;
    }
}
