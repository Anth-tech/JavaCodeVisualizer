package com.example;

import java.util.ArrayList;
import java.util.List;

public class JavaClassObject {
    private String className;
    private String accessModifier;
    private List<String> nonAccessModifiers;
    private List<String> interfaceNames;
    private String superClassName;
    private List<JavaMethodObject> methods;
    private List<JavaFieldObject> fields;

    private JavaClassObject(Builder builder) {
        this.className = builder.className;
        this.accessModifier = builder.accessModifier;
        this.nonAccessModifiers = builder.nonAccessModifiers;
        this.interfaceNames = builder.interfaceNames;
        this.superClassName = builder.superClassName;
        this.methods = builder.methods;
        this.fields = builder.fields;
    }
    public String getClassName() {
        return className;
    }
    public String getAccessModifier() {
        return accessModifier;
    }
    public List<String> getNonAccessModifiers() {
        return nonAccessModifiers;
    }
    public List<String> getInterfaceNames() {
        return interfaceNames;
    }
    public String getSuperClassName() {
        return superClassName;
    }
    public List<JavaMethodObject> getMethods() {
        return methods;
    }

    public static class Builder {
        private String className;
        private String accessModifier;
        private List<String> nonAccessModifiers = new ArrayList<>();
        private List<String> interfaceNames = new ArrayList<>();
        private String superClassName;
        private List<JavaMethodObject> methods = new ArrayList<>();
        private List<JavaFieldObject> fields = new ArrayList<>();

        public Builder className(String className) {
            this.className = className;
            return this;
        }
        public Builder accessModifier(String accessModifier) {
            this.accessModifier = accessModifier;
            return this;
        }
        public Builder nonAccessModifiers(List<String> nonAccessModifiers) {
            this.nonAccessModifiers = nonAccessModifiers;
            return this;
        }
        public Builder interfaceNames(List<String> interfaceNames) {
            this.interfaceNames = interfaceNames;
            return this;
        }
        public Builder superClassName(String superClassName) {
            this.superClassName = superClassName;
            return this;
        }
        public Builder methods(List<JavaMethodObject> methods) {
            this.methods = methods;
            return this;
        }
        public Builder fields(List<JavaFieldObject> fields) {
            this.fields = fields;
            return this;
        }
        public JavaClassObject build() {
            return new JavaClassObject(this);
        }
    }
}
