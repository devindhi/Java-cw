package com.example.ok;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<String> c1;
    @FXML
    private ListView<String> c2;
    @FXML
    private ListView<String> c3;

    private String[] q1;

    public void queue() {
        q1 = food.cashiers[0];
        ObservableList<String> listViewItems = c1.getItems();
        for (String customer : q1) {
            if (customer == null) {
                listViewItems.add("Empty");
            } else {
                listViewItems.add(customer);
            }
        }
    }

    public void queue1() {
        q1 = food.cashiers[1];
        ObservableList<String> listViewItems = c2.getItems();
        for (String customer : q1) {
            if (customer == null) {
                listViewItems.add("Empty");
            } else {
                listViewItems.add(customer);
            }
        }
    }



    public void queue2() {
        q1 = food.cashiers[2];

        ObservableList<String> listViewItems = c3.getItems();
        for (String customer : q1) {
            if (customer == null) {
                listViewItems.add("Empty");
            } else {
                listViewItems.add(customer);
            }
        }
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialization logic
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}