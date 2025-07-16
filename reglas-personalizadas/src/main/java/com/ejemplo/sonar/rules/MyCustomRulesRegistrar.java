package com.ejemplo.sonar.rules;

import org.sonar.plugins.java.api.CheckRegistrar;


import java.util.Collections;


public class MyCustomRulesRegistrar implements CheckRegistrar {
    @Override
    public void register(RegistrarContext context) {
        context.registerClassesForRepository(
            "mi-sonar-plugin", // repositorio declarado en RulesDefinition
            Collections.singletonList(AvoidSystemOutPrintlnRule.class),
            Collections.emptyList()
        );
    }
}
