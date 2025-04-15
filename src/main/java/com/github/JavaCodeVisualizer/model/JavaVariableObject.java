package com.github.JavaCodeVisualizer.model;

/**
 * Represents a parsed Java variable expression and contains its metadata
 */
public class JavaVariableObject {
    private final String name;
    private final String type;
    private final String initializer;

    private JavaVariableObject(Builder builder) {
        this.name = builder.name;
        this.type = builder.type;
        this.initializer = builder.initializer;
    }

    /**
     * Getters
     */
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }
    public String getInitializer() {
        return this.initializer;
    }

    /**
     * toString for debugging
     */
    @Override
    public String toString() {
        return "VariableName=" + name
                + "\nType=" + type
                + "\nInitializer=" + initializer;
    }

    /**
     * Builder for JavaVariableObject
     */
    public static class Builder {
        private String name;
        private String type;
        private String initializer;

        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder type(String type) {
            this.type = type;
            return this;
        }
        public Builder initializer(String initializer) {
            this.initializer = initializer;
            return this;
        }

        public JavaVariableObject build() {
            return new JavaVariableObject(this);
        }
    }
}
