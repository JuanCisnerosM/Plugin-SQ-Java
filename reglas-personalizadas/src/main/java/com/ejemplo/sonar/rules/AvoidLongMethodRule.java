package com.ejemplo.sonar.rules;

import org.sonar.check.Rule;
import org.sonar.check.RuleProperty;
import org.sonar.java.model.JavaTree;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;
import org.sonar.plugins.java.api.tree.BlockTree;

import java.util.Collections;
import java.util.List;

@Rule(key = "AvoidLongMethod")
public class AvoidLongMethodRule extends IssuableSubscriptionVisitor {

    private static final int DEFAULT_MAX_LINES = 50;

    @RuleProperty(
        key = "maximumMethodLines",
        description = "Número máximo de líneas permitidas en un método",
        defaultValue = "" + DEFAULT_MAX_LINES)
    public int maximumMethodLines = DEFAULT_MAX_LINES;

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.METHOD);
    }

    @Override
    public void visitNode(Tree tree) {
        MethodTree method = (MethodTree) tree;
        BlockTree block = method.block();
        
        // Verificar si el método tiene un bloque de código
        if (block != null) {
            int startLine = ((JavaTree) method).getLine();
            int endLine = ((JavaTree) block.lastToken()).getLine();
            int lines = endLine - startLine + 1;
            
            if (lines > maximumMethodLines) {
                reportIssue(method.simpleName(), 
                    "Este método tiene " + lines + " líneas, lo cual excede el máximo permitido de " + 
                    maximumMethodLines + " líneas.");
            }
        }
    }
}
