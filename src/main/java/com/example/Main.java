package com.example;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.metamodel.NodeMetaModel;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    private static final String FILE_PATH = "C:/Users/antho/Desktop/Assignment 5/Assignment 5/Main.java";

    public static void main(String[] args) throws Exception {
        // First create a CompilationUnit object, this is the root of the AST
        CompilationUnit root = StaticJavaParser.parse(Files.newInputStream(Paths.get(FILE_PATH)));
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
        //System.out.println(root.toString());
    }
}