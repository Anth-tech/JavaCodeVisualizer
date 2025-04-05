package com.example;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import com.github.javaparser.metamodel.NodeMetaModel;
import com.github.javaparser.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Main {

    private static final String FILE_PATH = "C:\\Users\\antho\\Desktop\\Assignment 5\\Assignment 5\\Main.java";

    public static void main(String[] args) throws Exception {
        // Create a TypeSolver to resolve symbols and references
        TypeSolver typeSolver = new CombinedTypeSolver();

        Path path = Paths.get(FILE_PATH);
        //  Create a CompilationUnit object, this is the root of the AST and represents a single file
        CompilationUnit root = StaticJavaParser.parse(Files.newInputStream(path));

        Map<String, List<ClassOrInterfaceDeclaration>> classesAndInterfaces = new HashMap<>();
        classesAndInterfaces.put("Classes", new ArrayList<>());
        classesAndInterfaces.put("Interfaces", new ArrayList<>());
        VoidVisitor<Map<String, List<ClassOrInterfaceDeclaration>>> classInterfaceCollector = new ClassCollector();
        classInterfaceCollector.visit(root, classesAndInterfaces);

//        JavaFileObject testObj = new JavaFileObject.Builder()
//                .fileName(path.getFileName().toString())
//                .filePath(path)
//                .filePackage(root.getPackageDeclaration())
//                .imports(root.getImports())
//                .classes()
//                .build();
//
//        NodeList<ImportDeclaration> imports = root.getImports();
//        imports.forEach(i -> System.out.println(i.getNameAsString()));
//        Optional<PackageDeclaration> packages = root.getPackageDeclaration();
//        System.out.println(packages.toString());
//
//        // Get File name
//        String fileName = path.getFileName().toString();
//        System.out.println("File: " + fileName);

        // Collect Packages
        //List<String> packages = new ArrayList<>();
        //VoidVisitor<List<String>> packageCollector = new PackageCollector();
        //packageCollector.visit(root, packages);
        //System.out.println("Packages: ");
        //packages.forEach(n -> System.out.println("Package: " + n));


        // Collect Imports
        //List<String> imports = new ArrayList<>();
        //VoidVisitor<List<String>> importCollector = new ImportCollector();
        //importCollector.visit(root, imports);
        //System.out.println("Imports: ");
        //imports.forEach(n -> System.out.println("Import: " + n));


        // Collect classes and interfaces
//        Map<String, List<String>> classesAndInterfaces = new HashMap<>();
//        classesAndInterfaces.put("Classes", new ArrayList<>());
//        classesAndInterfaces.put("Interfaces", new ArrayList<>());
//        VoidVisitor<Map<String, List<String>>> classInterfaceCollector = new ClassCollector();
//        classInterfaceCollector.visit(root, classesAndInterfaces);
//        System.out.println("Classes and interfaces: ");
//        classesAndInterfaces.get("Classes").forEach(n -> System.out.println("Class: " + n));
//        classesAndInterfaces.get("Interfaces").forEach(n -> System.out.println("Interface: " + n));



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

        //System.out.println(imports.toString());
        */
        //System.out.println(root.getTypes());
    }
}