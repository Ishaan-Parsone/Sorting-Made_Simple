package com.example.hehe;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ResourceBundle;

public class SortedWindow implements Initializable {
    @FXML
    private Pane titlePane, btnClose, btnMinimize;
    @FXML
    private TextArea iterationsTextArea;
    @FXML
    private Label sortedWindowTitle;


    private static String filename = "C:\\Sample\\Result.txt";
    private double x, y;
    private StringBuilder sb = new StringBuilder();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Path path = Paths.get(filename);

        try {
            BufferedReader br = Files.newBufferedReader(path);
            String input;

            while ((input = br.readLine()) != null) {
                sb.append(input + "\n");
            }

            if (br != null) {
                br.close();
            }
        } catch (IOException ignored) {
        }

        iterationsTextArea.setText(String.valueOf(sb));

        HelloController ok = new HelloController();

    }

    public void init(Stage primaryStage, String title) {

        sortedWindowTitle.setText(title);

        titlePane.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });
        titlePane.setOnMouseDragged(mouseEvent -> {
            primaryStage.setX(mouseEvent.getScreenX() - x);
            primaryStage.setY(mouseEvent.getScreenY() - y);
        });

        btnClose.setOnMouseClicked(mouseEvent -> primaryStage.close());
        btnMinimize.setOnMouseClicked(mouseEvent -> primaryStage.setIconified(true));
    }
}
