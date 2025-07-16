package com.ejemplo.sonar.rules;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.Collections;
import java.util.List;

/**
 * Regla personalizada para SonarQube que detecta llamadas a System.out.println
 */
@Rule(key = "AvoidSystemOutPrintln")
public class AvoidSystemOutPrintlnRule extends IssuableSubscriptionVisitor {

    /**
     * Define los tipos de nodos AST que esta regla va a visitar.
     * En este caso, solo visitamos las invocaciones de métodos.
     */
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return Collections.singletonList(Tree.Kind.METHOD_INVOCATION);
    }

    /**
     * Este método es llamado para cada nodo visitado (en este caso, para cada llamada a método).
     * Aquí se implementa la lógica para detectar si la llamada es a System.out.println
     */
    @Override
    public void visitNode(Tree tree) {
        MethodInvocationTree methodInvocation = (MethodInvocationTree) tree;

        // Verificamos que el método invocado sea 'println' y que pertenezca a System.out (PrintStream)
        if (methodInvocation.methodSymbol().owner().type().is("java.io.PrintStream") &&
                methodInvocation.methodSymbol().name().equals("println")) {

            // Reportamos un issue en esta línea con el mensaje que queremos mostrar en SonarQube
            reportIssue(tree, "Evita usar System.out.println, usa un logger en su lugar.");
        }
    }
}

// @Rule(key = "..."): Define un identificador único para esta regla.

// nodesToVisit(): Le dice a SonarQube que queremos analizar los nodos de tipo invocación de métodos.

// visitNode(Tree tree): Por cada invocación de método, verificamos si es println del objeto System.out (que es un PrintStream).

// reportIssue(...): Marca el problema para que aparezca en el reporte.


