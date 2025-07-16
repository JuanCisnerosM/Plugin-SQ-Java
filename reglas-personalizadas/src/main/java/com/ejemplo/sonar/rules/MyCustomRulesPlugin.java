package com.ejemplo.sonar.rules;

import org.sonar.api.Plugin;

public class MyCustomRulesPlugin implements Plugin {
    @Override
    public void define(Context context) {
        // Registrar las clases que extienden la API de extensión
        context.addExtension(MyCustomRulesRegistrar.class);
        context.addExtension(MyCustomRulesDefinition.class);
    }
}
