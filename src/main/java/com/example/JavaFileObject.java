package com.example;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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
        public Builder filePackage(String filePackage) {
            this.filePackage = filePackage;
            return this;
        }
        public Builder imports(List<String> imports) {
            this.imports = imports;
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
