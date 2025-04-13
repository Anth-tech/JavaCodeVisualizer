package com.github.JavaCodeVisualizer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.visitor.VoidVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Main {
    private static final String FILE_PATH2 = "C:\\Users\\antho\\Desktop\\Assignment 5\\Assignment 5\\Main.java";
    private static final String FILE_PATH = "C:\\Users\\antho\\Desktop\\CSCI 4270\\Assignment 4\\Assignment4.java";

    public static void main(String[] args) throws Exception {
        // Create a TypeSolver to resolve symbols and references
        // TypeSolver typeSolver = new CombinedTypeSolver();

        //System.out.println("Enter the file path: ");
        //Scanner reader = new Scanner(System.in);
        //String filePath = reader.nextLine();
        Path path = Paths.get(FILE_PATH2);
        //  Create a CompilationUnit object, this is the root of the AST and represents a single file
        CompilationUnit root = StaticJavaParser.parse(Files.newInputStream(path));

        Map<String, List<ClassOrInterfaceDeclaration>> classesAndInterfaces = new HashMap<>();
        classesAndInterfaces.put("Classes", new ArrayList<>());
        classesAndInterfaces.put("Interfaces", new ArrayList<>());
        VoidVisitor<Map<String, List<ClassOrInterfaceDeclaration>>> classInterfaceCollector = new ClassCollector();
        classInterfaceCollector.visit(root, classesAndInterfaces);


        List<JavaClassObject> classesList = new ArrayList<>();
        classesAndInterfaces.get("Classes").forEach(c ->  {
            List<JavaMethodObject> methods = getJavaMethodObjects(c);
            List<JavaFieldObject> fields = getJavaFieldObjects(c);

            classesList.add(new JavaClassObject.Builder()
                    .className(c.getNameAsString())
                    .modifiers(c.getModifiers())
                    .extendedTypes(c.getExtendedTypes())
                    .interfaceNames(c.getImplementedTypes())
                    .methods(methods)
                    .fields(fields)
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
        fileObject.getClasses().forEach(c -> {
            System.out.println(c.toString());
        });

        //reader.close();
    }

    private static List<JavaMethodObject> getJavaMethodObjects(ClassOrInterfaceDeclaration c) {
        List<MethodDeclaration> methodDeclarations = c.getMethods();
        List<JavaMethodObject> methods = new ArrayList<>();
        methodDeclarations.forEach(md -> {
           methods.add(new JavaMethodObject.Builder()
                   .methodName(md.getNameAsString())
                   .returnType(md.getTypeAsString())
                   .modifiers(md.getModifiers())
                   .parameters(md.getParameters())
                   .exceptionTypes(md.getThrownExceptions())
                   .methodBody(md.getBody())
                   .build()
           );
        });
        return methods;
    }

    private static List<JavaFieldObject> getJavaFieldObjects(ClassOrInterfaceDeclaration c) {
        List<FieldDeclaration> fieldDeclarations = c.getFields();
        List<JavaFieldObject> fields = new ArrayList<>();
        fieldDeclarations.forEach(f -> {
            List<JavaVariableObject> variables = getJavaVariableObjects(f);
            fields.add(new JavaFieldObject.Builder()
                    .fieldBody(f.toString())
                    .variables(variables)
                    .modifiers(f.getModifiers())
                    .build()
            );
        });
        return fields;
    }

    private static List<JavaVariableObject> getJavaVariableObjects(FieldDeclaration f) {
        List<VariableDeclarator> variableDeclarators = f.getVariables();
        List<JavaVariableObject> variables = new ArrayList<>();
        variableDeclarators.forEach(v -> {
            variables.add(new JavaVariableObject.Builder()
                    .name(v.getNameAsString())
                    .type(v.getType().asString())
                    .initializer(v.getInitializer().toString())
                    .build()
            );
        });
        return variables;
    }
}