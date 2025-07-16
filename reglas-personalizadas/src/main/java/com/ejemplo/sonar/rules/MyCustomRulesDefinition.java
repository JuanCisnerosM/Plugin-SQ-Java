package com.ejemplo.sonar.rules;

import org.sonar.api.rules.RuleType;
import org.sonar.api.server.rule.RulesDefinition;

public class MyCustomRulesDefinition implements RulesDefinition {

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository("mi-sonar-plugin", "java")
            .setName("Reglas Personalizadas");
        
        // Definimos la regla personalizada
        repository.createRule("CJR001")

            .setName("Evita usar System.out.println")
            .setHtmlDescription("Usa un logger en lugar de System.out.println para mejores prácticas de registro.")
            .setSeverity("MINOR")
            .setType(RuleType.CODE_SMELL)
            .setTags("logging", "java", "clean-code");
            
        // Definimos otra regla personalizada
        repository.createRule("CJR002")

            .setName("Evita métodos muy largos")
            .setHtmlDescription("Los métodos largos son difíciles de entender y mantener. " + "Considera dividir los métodos grandes en métodos más pequeños y cohesivos.")
            .setSeverity("MAJOR")
            .setType(RuleType.CODE_SMELL)
            .setTags("complexity", "readability", "java")
            .createParam("maximumMethodLines")
            .setDescription("Número máximo de líneas permitidas en un método")
            .setDefaultValue("50");
        repository.done();
    }
}

