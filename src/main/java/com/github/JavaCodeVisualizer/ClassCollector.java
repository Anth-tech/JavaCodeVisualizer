package com.github.JavaCodeVisualizer;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;
import java.util.Map;

public class ClassCollector extends VoidVisitorAdapter<Map<String, List<ClassOrInterfaceDeclaration>>> {

    /**
     * Visits and collects classes and interfaces and adds them to the list
     * @param cid ClassOrInterfaceDeclaration node
     * @param collector map that collects name
     */
    @Override
    public void visit(ClassOrInterfaceDeclaration cid, Map<String, List<ClassOrInterfaceDeclaration>> collector) {
        // always call super.visit
        super.visit(cid, collector);
        if (cid.isInterface()) {
            collector.get("Interfaces").add(cid);
        } else {
            collector.get("Classes").add(cid);
        }
    }
}
