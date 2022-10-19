package io.camunda.connector;

import java.util.HashMap;
import java.util.Map;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEvaluator {

  private final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();


  private final Map<String, ScriptEngine> cachedScriptEngines = new HashMap<>();

  public Object evaluate(String language, String script) {


    return evalWithScriptEngine(language, script);
  }

  private Object evalWithScriptEngine(
      String language, String script) {
    final ScriptEngine scriptEngine =
        cachedScriptEngines.computeIfAbsent(language, scriptEngineManager::getEngineByName);

    if (scriptEngine == null) {
      final String msg = String.format("No script engine found with name '%s'", language);
      throw new RuntimeException(msg);
    }

    try {
      final ScriptContext context = scriptEngine.getContext();
      return scriptEngine.eval(script, context);

    } catch (ScriptException e) {
      final String msg = String.format("Failed to evaluate script '%s' (%s)", script, language);
      throw new RuntimeException(msg, e);
    }
  }

}