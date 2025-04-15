package com.github.JavaCodeVisualizer.app;

import com.github.JavaCodeVisualizer.model.JavaFileObject;
import com.github.JavaCodeVisualizer.parser.JavaParserEngine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application{
    //private static final String FILE_PATH2 = "C:\\Users\\antho\\Desktop\\Assignment 5\\Assignment 5\\Main.java";
    //private static final String FILE_PATH = "C:\\Users\\antho\\Desktop\\CSCI 4270\\Assignment 4\\Assignment4.java";
    private static String FILE_PATH;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // -----------------------------------------------------------------------
        // Fx elements and actions setup
        // -----------------------------------------------------------------------

        final Text actionTarget = new Text();

        //Label pathLabel = new Label("Path:");
        //TextField pathText = new TextField();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a java file");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Java Files", "*.java")
        );
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        Button submitButton = new Button();
        submitButton.setText("Submit");
        submitButton.setOnAction( event -> {
            //FILE_PATH = pathText.getText();
        });

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(submitButton);

        GridPane fxRoot = new GridPane();
        fxRoot.setAlignment(Pos.CENTER);
        fxRoot.setHgap(10);
        fxRoot.setVgap(10);
        fxRoot.setPadding(new Insets(25, 25, 25, 25));
        //fxRoot.add(pathLabel, 0, 1);
        //fxRoot.add(pathText, 1, 1);
        fxRoot.add(hbBtn, 1, 3);
        fxRoot.add(actionTarget, 1, 4);

        Scene scene = new Scene(fxRoot, 300, 275);

        primaryStage.setTitle("Code Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();

        // -----------------------------------------------------------------------
        // Parsing logic and object creation
        // -----------------------------------------------------------------------

        //JavaFileObject fileObject = JavaParserEngine.parse(FILE_PATH);
    }

    public static void main(String[] args) {
        launch(args);
    }
}