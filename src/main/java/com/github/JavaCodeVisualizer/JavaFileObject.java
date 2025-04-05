package com.github.JavaCodeVisualizer;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Represents a parsed Java file and contains its metadata,
 * such as file name, file path, package, imports, and classes
 */
public class JavaFileObject {
    private final String fileName;
    private final Path filePath;
    private final String filePackage;
    private final List<String> imports;
    private final List<JavaClassObject> classes;

    /**
     * private constructor which uses a builder
     * @param builder object builder
     */
    private JavaFileObject(Builder builder) {
        this.fileName = builder.fileName;
        this.filePath = builder.filePath;
        this.filePackage = builder.filePackage;
        this.imports = builder.imports;
        this.classes = builder.classes;
    }

    public String getFileName() {
        return fileName;
    }
    public Path getFilePath() {
        return filePath;
    }
    public String getFilePackage() {
        return filePackage;
    }
    public List<String> getImports() {
        return imports;
    }
    public List<JavaClassObject> getClasses() {
        return classes;
    }

    @Override
    public String toString() {
        return "JavaFileObject [fileName=" + fileName + "\nfilePath=" + filePath + "\nfilePackage=" + filePackage
                + "\nimports=" + imports + "\nclasses=" + classes.stream().map(JavaClassObject::getClassName).toList() + "]";
    }

    /**
     * Builder for JavaFileObject
     */
    public static class Builder {
        private String fileName;
        private Path filePath;
        private String filePackage;
        private List<String> imports = new ArrayList<>();
        private List<JavaClassObject> classes = new ArrayList<>();

        public Builder fileName(String fileName) {
            this.fileName = fileName;
            return this;
        }
        public Builder filePath(Path filePath) {
            this.filePath = filePath;
            return this;
        }
        public Builder filePackage(Optional<PackageDeclaration> filePackage) {
            if (filePackage.isPresent()) {
                this.filePackage = filePackage.get().getName().toString();
            }
            return this;
        }
        public Builder imports(NodeList<ImportDeclaration> imports) {
            this.imports = imports.stream().map(ImportDeclaration::getNameAsString).collect(Collectors.toList());
            return this;
        }
        public Builder classes(List<JavaClassObject> classes) {
            this.classes = classes;
            return this;
        }

        public JavaFileObject build() {
            return new JavaFileObject(this);
        }
    }
}
