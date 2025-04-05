package com.example;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JavaFileObject {
    private String fileName;
    private Path filePath;
    private String filePackage;
    private List<String> imports;
    private List<JavaClassObject> classes;

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
        return "JavaFileObject [fileName=" + fileName + ", filePath=" + filePath + ", filePackage=" + filePackage
                + ", imports=" + ", classes=" + classes + "]";
    }

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
