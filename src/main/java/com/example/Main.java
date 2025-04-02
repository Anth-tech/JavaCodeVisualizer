package com.example;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.NodeMetaModel;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    private static final String FILE_PATH = "C:/Users/antho/Desktop/CSCI 4270/Assignment 4/Assignment4.java";

    public static void main(String[] args) throws Exception {
        // First create a CompilationUnit object, this is the root of the AST
        Path path = Paths.get(FILE_PATH);
        CompilationUnit root = StaticJavaParser.parse(Files.newInputStream(path));

        // Get File name
        String fileName = path.getFileName().toString();
        System.out.println("File: " + fileName);

        // Collect Packages
        List<String> packages = new ArrayList<>();
        VoidVisitor<List<String>> packageCollector = new PackageCollector();
        packageCollector.visit(root, packages);
        System.out.println("Packages: ");
        packages.forEach(n -> System.out.println("Package: " + n));


        // Collect Imports
        List<String> imports = new ArrayList<>();
        VoidVisitor<List<String>> importCollector = new ImportCollector();
        importCollector.visit(root, imports);
        System.out.println("Imports: ");
        imports.forEach(n -> System.out.println("Import: " + n));


        // Collect classes and interfaces
        Map<String, List<String>> classesAndInterfaces = new HashMap<>();
        classesAndInterfaces.put("Classes", new ArrayList<>());
        classesAndInterfaces.put("Interfaces", new ArrayList<>());
        VoidVisitor<Map<String, List<String>>> classInterfaceCollector = new ClassCollector();
        classInterfaceCollector.visit(root, classesAndInterfaces);
        System.out.println("Classes and interfaces: ");
        classesAndInterfaces.get("Classes").forEach(n -> System.out.println("Class: " + n));
        classesAndInterfaces.get("Interfaces").forEach(n -> System.out.println("Interface: " + n));



        /*
        AtomicInteger count = new AtomicInteger();
        root.walk(n -> {
            NodeMetaModel ptype = null;
            if (n.getParentNode().isPresent()) {
                ptype = n.getParentNode().get().getMetaModel();
            }
            System.out.println("node " + count + ": | node type: " + n.getMetaModel() + " | parent node: " + ptype + "\n" + n.toString());
            count.getAndIncrement();
        });
        //List<Node> childNodes = root.getChildNodes();
        //System.out.println(childNodes.size());
        //NodeList<ImportDeclaration> imports = root.getImports();
        //System.out.println(imports.toString());
        */
        //System.out.println(root.getTypes());
    }
}