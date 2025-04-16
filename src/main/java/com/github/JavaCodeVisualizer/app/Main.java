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

import com.github.javaparser.ast.CompilationUnit;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.util.*;


public class Main extends Application{
    //private static final String FILE_PATH2 = "C:\\Users\\antho\\Desktop\\Assignment 5\\Assignment 5\\Main.java";
    //private static final String FILE_PATH = "C:\\Users\\antho\\Desktop\\CSCI 4270\\Assignment 4\\Assignment4.java";
    private static String FILE_PATH;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // -----------------------------------------------------------------------
        // Fx elements and actions setup
        // -----------------------------------------------------------------------

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a folder");
        File directory = directoryChooser.showDialog(primaryStage);
        if (directory.isFile()) {
            if (directory.getName().endsWith(".java")) {
                Map<Path, CompilationUnit> parsedCompilationUnits = JavaParserEngine.parseSingleFile(directory);
                List<JavaFileObject> parsedJavaFiles = JavaParserEngine.parse(parsedCompilationUnits);
            } else {
                throw new Exception("File is not a java file");
            }
        } else if (directory.isDirectory()) {
            File[] files = directory.listFiles(file -> file.getName().endsWith(".java"));
            if (files != null) {
                Map<Path, CompilationUnit> parsedCompilationUnits = JavaParserEngine.parseDirectory(files);
                List<JavaFileObject> parsedJavaFiles = JavaParserEngine.parse(parsedCompilationUnits);
            }
        }

        GridPane fxRoot = new GridPane();
        fxRoot.setAlignment(Pos.CENTER);
        fxRoot.setHgap(10);
        fxRoot.setVgap(10);
        fxRoot.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(fxRoot, 300, 275);

        primaryStage.setTitle("Code Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();

        // -----------------------------------------------------------------------
        // Parsing logic and object creation
        // -----------------------------------------------------------------------

    }

    public static void main(String[] args) {
        launch(args);
    }
}