package com.github.JavaCodeVisualizer.parser;

import com.github.JavaCodeVisualizer.model.*;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.visitor.VoidVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class JavaParserEngine {

    public static JavaFileObject parse(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        CompilationUnit root = StaticJavaParser.parse(Files.newInputStream(path));
        return getJavaFileObject(root, path);
    }

    private static JavaFileObject getJavaFileObject(CompilationUnit root, Path path) {
        Map<String, List<ClassOrInterfaceDeclaration>> classesAndInterfaces = extractClassesAndInterfaces(root);
        List<JavaClassObject> classesList = getJavaClassObjects(classesAndInterfaces);
        List<JavaClassObject> interfacesList = getJavaInterfaceObjects(classesAndInterfaces);
        Optional<PackageDeclaration> optionalPkg = root.getPackageDeclaration();
        String packageName = optionalPkg
                .map(PackageDeclaration::getNameAsString)
                .orElse("no package");
        return new JavaFileObject.Builder()
                .fileName(path.getFileName().toString())
                .filePath(path)
                .classes(classesList)
                .interfaces(interfacesList)
                .filePackage(packageName)
                .imports(root.getImports())
                .build();
    }

    private static Map<String, List<ClassOrInterfaceDeclaration>> extractClassesAndInterfaces(CompilationUnit root) {
        Map<String, List<ClassOrInterfaceDeclaration>> classesAndInterfaces = new HashMap<>();
        classesAndInterfaces.put("Classes", new ArrayList<>());
        classesAndInterfaces.put("Interfaces", new ArrayList<>());
        VoidVisitor<Map<String, List<ClassOrInterfaceDeclaration>>> classInterfaceCollector = new ClassCollector();
        classInterfaceCollector.visit(root, classesAndInterfaces);
        return classesAndInterfaces;
    }

    private static List<JavaClassObject> getJavaClassObjects(Map<String, List<ClassOrInterfaceDeclaration>> classesAndInterfaces) {
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
        return classesList;
    }

    private static List<JavaClassObject> getJavaInterfaceObjects(Map<String, List<ClassOrInterfaceDeclaration>> classesAndInterfaces) {
        List<JavaClassObject> interfacesList = new ArrayList<>();
        classesAndInterfaces.get("Interfaces").forEach(c ->  {
            List<JavaMethodObject> methods = getJavaMethodObjects(c);
            List<JavaFieldObject> fields = getJavaFieldObjects(c);

            interfacesList.add(new JavaClassObject.Builder()
                    .className(c.getNameAsString())
                    .modifiers(c.getModifiers())
                    .extendedTypes(c.getExtendedTypes())
                    .interfaceNames(c.getImplementedTypes())
                    .methods(methods)
                    .fields(fields)
                    .build()
            );
        });
        return interfacesList;
    }


    private static List<JavaMethodObject> getJavaMethodObjects(ClassOrInterfaceDeclaration c) {
        List<MethodDeclaration> methodDeclarations = c.getMethods();
        List<JavaMethodObject> methods = new ArrayList<>();
        methodDeclarations.forEach(md -> {
            Optional<BlockStmt> optionalBlk = md.getBody();
            String body = optionalBlk
                    .map(BlockStmt::toString)
                    .orElse("no body");
            methods.add(new JavaMethodObject.Builder()
                    .methodName(md.getNameAsString())
                    .returnType(md.getTypeAsString())
                    .modifiers(md.getModifiers())
                    .parameters(md.getParameters())
                    .exceptionTypes(md.getThrownExceptions())
                    .methodBody(body)
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
