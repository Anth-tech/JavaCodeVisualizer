package com.github.JavaCodeVisualizer;

import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class ImportCollector extends VoidVisitorAdapter<List<String>> {
    /**
     * Visits and collects all ImportDeclaration Nodes
     * @param id ImportDeclaration Node
     * @param collector List that collects names
     */
    @Override
    public void visit(ImportDeclaration id, List<String> collector) {
        // always call super.visit
        super.visit(id, collector);
        // add the import declaration name to list
        collector.add(id.getNameAsString());
    }
}
