package ru.kpfu.itis.bagautdinov;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class Weather extends Application {

    private final WeatherService weatherService = new WeatherService();

    @Override
    public void start(Stage stage) {
        VBox root = new VBox();
        root.setId("root");
        HBox top = new HBox();
        top.setId("top");
        TextField textField = new TextField();
        Button search = new Button("Search");
        Label result = new Label();
        search.setOnAction(e -> {
            if (!textField.getText().isEmpty()) {
                try {
                    Map<String,String> map = weatherService.get(textField.getText());
                    result.setText("city: " + map.get("name") + "\n" +
                            "temp: " + map.get("temp") + "\n" +
                            "feels like: " + map.get("feels_like") + "\n" +
                            "temp max: " + map.get("temp_max") + "\n" +
                            "temp min: " + map.get("temp_min") + "\n" +
                            "wind speed: " + map.get("wind_speed") + "\n");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        HBox.setMargin(textField,new Insets(10, 0, 10, 15));
        HBox.setMargin(search, new Insets(10, 0, 10, 15));
        top.getChildren().addAll(textField,search);
        result.setPrefWidth(250);
        result.setPrefHeight(120);
        VBox.setMargin(result, new Insets(0, 0, 0, 15));
        root.getChildren().addAll(top,result);
        Scene scene = new Scene(root, 300, 300);
        stage.setScene(scene);
        stage.show();
    }
}
