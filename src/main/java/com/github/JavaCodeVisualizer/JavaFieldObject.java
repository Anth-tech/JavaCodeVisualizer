package com.github.JavaCodeVisualizer;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JavaFieldObject {
    private final String fieldBody;
    private final List<JavaVariableObject> variables;
    private final List<String> modifiers;

    private JavaFieldObject(Builder builder) {
        this.fieldBody = builder.fieldBody;
        this.variables = builder.variables;
        this.modifiers = builder.modifiers;
    }

    public String getFieldBody() {
        return this.fieldBody;
    }
    public List<JavaVariableObject> getVariables() {
        return this.variables;
    }
    public List<String> getModifiers() {
        return this.modifiers;
    }

    @Override
    public String toString() {
        return "FieldBody=" + fieldBody
                + "\nVariables=" + variables.stream().map(JavaVariableObject::toString).toList()
                + "\nModifiers=" + modifiers;
    }

    public static class Builder {
        private String fieldBody;
        private List<JavaVariableObject> variables = new ArrayList<>();
        private List<String> modifiers = new ArrayList<>();

        public Builder fieldBody(String fieldBody) {
            this.fieldBody = fieldBody;
            return this;
        }
        public Builder variables(List<JavaVariableObject> variables) {
            this.variables = variables;
            return this;
        }
        public Builder modifiers(NodeList<Modifier> modifiers) {
            this.modifiers = modifiers.stream().map(Modifier::toString).collect(Collectors.toList());
            return this;
        }
        public JavaFieldObject build() {
            return new JavaFieldObject(this);
        }
    }
}
