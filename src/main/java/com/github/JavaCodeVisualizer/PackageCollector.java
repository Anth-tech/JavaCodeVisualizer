package com.github.JavaCodeVisualizer;

import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class PackageCollector extends VoidVisitorAdapter<List<String>> {
    /**
     * Visits and collects all packagedeclaration nodes
     * @param pd PackageDeclaration Node
     * @param collector List that collects names
     */
    @Override
    public void visit(PackageDeclaration pd, List<String> collector) {
        // Always do super.visit
        super.visit(pd, collector);
        // add the package declaration name to the list
        collector.add(pd.getNameAsString());
    }
}
