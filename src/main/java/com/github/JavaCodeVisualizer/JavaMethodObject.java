package com.github.JavaCodeVisualizer;

import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.type.ReferenceType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a parsed Java Method and contains its metadata
 */
public class JavaMethodObject {
    private final String methodName;
    private final String returnType;
    private final List<String> modifiers;
    private final List<String> parameters;
    private final List<String> exceptionTypes;
    private final String methodBody;

    private JavaMethodObject(Builder builder) {
        this.methodName = builder.methodName;
        this.returnType = builder.returnType;
        this.modifiers = builder.modifiers;
        this.parameters = builder.parameters;
        this.exceptionTypes = builder.exceptionTypes;
        this.methodBody = builder.methodBody;

    }

    /**
     * Getters
     */
    public String getMethodName() {
        return methodName;
    }
    public String getReturnType() {
        return returnType;
    }
    public List<String> getModifiers() {
        return modifiers;
    }
    public List<String> getParameters() {
        return parameters;
    }
    public List<String> getExceptionTypes() {
        return exceptionTypes;
    }
    public String getMethodBody() {
        return methodBody;
    }


    /**
     * toString for debugging
     */
    @Override
    public String toString() {
        return "MethodName=" + methodName
                + "\nReturnType=" + returnType
                + "\nModifiers=" + modifiers
                + "\nParameters=" + parameters
                + "\nExceptionTypes=" + exceptionTypes
                + "\nMethodBody=" + methodBody;
    }


    /**
     * Builder for JavaMethodObject
     */
    public static class Builder {
        private String methodName;
        private String returnType;
        private List<String> modifiers = new ArrayList<>();
        private List<String> parameters = new ArrayList<>();
        private List<String> exceptionTypes = new ArrayList<>();
        private String methodBody;

        public Builder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }
        public Builder returnType(String returnType) {
            this.returnType = returnType;
            return this;
        }
        public Builder modifiers(NodeList<Modifier> modifiers) {
            this.modifiers = modifiers.stream().map(Modifier::toString).collect(Collectors.toList());
            return this;
        }
        public Builder parameters(NodeList<Parameter> parameters) {
            this.parameters = parameters.stream().map(Parameter::toString).collect(Collectors.toList());
            return this;
        }
        public Builder exceptionTypes(NodeList<ReferenceType> exceptions) {
            this.exceptionTypes = exceptions.stream().map(ReferenceType::toString).collect(Collectors.toList());
            return this;
        }
        public Builder methodBody(BlockStmt body) {
            if (body != null) {
                this.methodBody = body.toString();
            }
            return this;
        }

        public JavaMethodObject build() {
            return new JavaMethodObject(this);
        }
    }
}
