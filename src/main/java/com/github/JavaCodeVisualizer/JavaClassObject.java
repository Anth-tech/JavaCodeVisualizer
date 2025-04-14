package com.github.JavaCodeVisualizer;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a parsed Java Class and contains its metadata,
 * including class name, modifiers, methods, and fields
 */
public class JavaClassObject {
    private final String className;
    private final List<String> modifiers;
    private final List<String> interfaceNames;
    private final List<String> extendedTypes;
    private final List<JavaMethodObject> methods;
    private final List<JavaFieldObject> fields;

    /**
     * private constructor which uses a builder
     * @param builder object builder
     */
    private JavaClassObject(Builder builder) {
        this.className = builder.className;
        this.modifiers = builder.modifiers;
        this.interfaceNames = builder.interfaceNames;
        this.extendedTypes = builder.extendedTypes;
        this.methods = builder.methods;
        this.fields = builder.fields;
    }

    /**
     * Getters
     */
    public String getClassName() {
        return className;
    }
    public List<String> getModifiers() {
        return modifiers;
    }
    public List<String> getInterfaceNames() {
        return interfaceNames;
    }
    public List<String> getExtendedTypes() {
        return extendedTypes;
    }
    public List<JavaMethodObject> getMethods() {
        return methods;
    }
    public List<JavaFieldObject> getFields() {
        return fields;
    }

    /**
     * toString for debugging
     */
    @Override
    public String toString() {
        return "ClassName=" + className
                + "\nModifiers=" + modifiers
                + "\nInterfaceNames=" + interfaceNames
                + "\nExtendedTypes=" + extendedTypes
                + "\nMethods=" + methods.stream().map(JavaMethodObject::toString).toList()
                + "\nFields=" + fields.stream().map(JavaFieldObject::toString).toList();
    }

    /**
     * Builder for JavaClassObject
     */
    public static class Builder {
        private String className;
        private List<String> modifiers = new ArrayList<>();
        private List<String> interfaceNames = new ArrayList<>();
        private List<String> extendedTypes = new ArrayList<>();
        private List<JavaMethodObject> methods = new ArrayList<>();
        private List<JavaFieldObject> fields = new ArrayList<>();

        public Builder className(String className) {
            this.className = className;
            return this;
        }
        public Builder modifiers(NodeList<Modifier> modifiers) {
            this.modifiers = modifiers.stream().map(Modifier::toString).collect(Collectors.toList());
            return this;
        }
        public Builder interfaceNames(NodeList<ClassOrInterfaceType> interfaces) {
            this.interfaceNames = interfaces.stream().map(ClassOrInterfaceType::toString).collect(Collectors.toList());
            return this;
        }
        public Builder extendedTypes(NodeList<ClassOrInterfaceType> extendedTypes) {
            this.extendedTypes = extendedTypes.stream().map(ClassOrInterfaceType::toString).collect(Collectors.toList());
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
