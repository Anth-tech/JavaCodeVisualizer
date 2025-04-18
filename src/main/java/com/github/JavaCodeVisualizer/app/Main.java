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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;


public class Main extends Application{
    //private static final String FILE_PATH2 = "C:\\Users\\antho\\Desktop\\Assignment 5\\Assignment 5\\Main.java";
    //private static final String FILE_PATH = "C:\\Users\\antho\\Desktop\\CSCI 4270\\Assignment 4\\Assignment4.java";
    private static String FILE_PATH;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // -----------------------------------------------------------------------
        // Fx elements and actions setup
        // -----------------------------------------------------------------------

        List<JavaFileObject> parsedJavaFiles = new ArrayList<>();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Choose a folder");
        File directory = directoryChooser.showDialog(primaryStage);

        List<File> files = new ArrayList<>();
        directorySearch(directory, ".java", files);


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
        if (!parsedJavaFiles.isEmpty()) {
            for (JavaFileObject file : parsedJavaFiles) {
                System.out.println(file.toString());
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void directorySearch(File directory, String pattern, List<File> files) {
        if (directory.isDirectory()) {
            File[] subFiles = directory.listFiles();
            if (subFiles != null) {
                for (File file : subFiles) {
                    directorySearch(file, pattern, files);
                }
            }
        } else if (directory.isFile()) {
            if (directory.getName().endsWith(pattern)) {
                files.add(directory);
            }
        }
    }
}