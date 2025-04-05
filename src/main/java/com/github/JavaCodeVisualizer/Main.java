package com.github.JavaCodeVisualizer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {

    private static final String FILE_PATH = "C:\\Users\\antho\\Desktop\\CSCI 4270\\Assignment 4\\Assignment4.java";

    public static void main(String[] args) throws Exception {
        // Create a TypeSolver to resolve symbols and references
        // TypeSolver typeSolver = new CombinedTypeSolver();

        //System.out.println("Enter the file path: ");
        //Scanner reader = new Scanner(System.in);
        //String filePath = reader.nextLine();
        Path path = Paths.get(FILE_PATH);
        //  Create a CompilationUnit object, this is the root of the AST and represents a single file
        CompilationUnit root = StaticJavaParser.parse(Files.newInputStream(path));

        Map<String, List<ClassOrInterfaceDeclaration>> classesAndInterfaces = new HashMap<>();
        classesAndInterfaces.put("Classes", new ArrayList<>());
        classesAndInterfaces.put("Interfaces", new ArrayList<>());
        VoidVisitor<Map<String, List<ClassOrInterfaceDeclaration>>> classInterfaceCollector = new ClassCollector();
        classInterfaceCollector.visit(root, classesAndInterfaces);

        List<JavaClassObject> classesList = new ArrayList<>();
        classesAndInterfaces.get("Classes").forEach(c ->  {
            classesList.add(new JavaClassObject.Builder()
                    .className(c.getNameAsString())
                    .modifiers(c.getModifiers())
                    .extendedTypes(c.getExtendedTypes())
                    .interfaceNames(c.getImplementedTypes())
                    .methods(null)
                    .fields(null)
                    .build()
            );
        });

        JavaFileObject fileObject = new JavaFileObject.Builder()
                .fileName(path.getFileName().toString())
                .filePath(path)
                .filePackage(root.getPackageDeclaration())
                .classes(classesList)
                .imports(root.getImports())
                .build();

        System.out.println(fileObject.toString());

        //reader.close();
    }
}