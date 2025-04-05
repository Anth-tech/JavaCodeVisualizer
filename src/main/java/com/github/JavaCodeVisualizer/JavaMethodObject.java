package com.github.JavaCodeVisualizer;

import java.util.ArrayList;
import java.util.List;

public class JavaMethodObject {
    private String fullQualifiedName;
    private String returnType;
    private String accessModifier;
    private List<String> nonAccessModifiers;
    private List<String> parameterTypes;
    private List<String> parameterNames;
    private List<String> exceptionTypes;
    private String methodBody;

    private JavaMethodObject(Builder builder) {

    }
    public String getFullQualifiedName() {
        return fullQualifiedName;
    }
    public String getReturnType() {
        return returnType;
    }
    public String getAccessModifier() {
        return accessModifier;
    }
    public List<String> getNonAccessModifiers() {
        return nonAccessModifiers;
    }
    public List<String> getParameterTypes() {
        return parameterTypes;
    }
    public List<String> getParameterNames() {
        return parameterNames;
    }
    public List<String> getExceptionTypes() {
        return exceptionTypes;
    }
    public String getMethodBody() {
        return methodBody;
    }

    public static class Builder {
        private String fullQualifiedName;
        private String returnType;
        private String accessModifier;
        private List<String> nonAccessModifiers = new ArrayList<>();
        private List<String> parameterTypes = new ArrayList<>();
        private List<String> parameterNames = new ArrayList<>();
        private List<String> exceptionTypes = new ArrayList<>();
        private String methodBody;

        public Builder fullQualifiedName(String fullQualifiedName) {
            this.fullQualifiedName = fullQualifiedName;
            return this;
        }
        public Builder returnType(String returnType) {
            this.returnType = returnType;
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
        public Builder parameterTypes(List<String> parameterTypes) {
            this.parameterTypes = parameterTypes;
            return this;
        }
        public Builder parameterNames(List<String> parameterNames) {
            this.parameterNames = parameterNames;
            return this;
        }
        public Builder exceptionTypes(List<String> exceptionTypes) {
            this.exceptionTypes = exceptionTypes;
            return this;
        }
        public Builder methodBody(String methodBody) {
            this.methodBody = methodBody;
            return this;
        }

        public JavaMethodObject build() {
            return new JavaMethodObject(this);
        }
    }
}
