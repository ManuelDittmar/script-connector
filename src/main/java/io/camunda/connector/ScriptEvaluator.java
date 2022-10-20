package io.camunda.connector;

import java.util.HashMap;
import java.util.Map;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEvaluator {

  private final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();

  private final Map<String, ScriptEngine> cachedScriptEngines = new HashMap<>();

  public Object evaluate(String language, String script, Map<String, Object> variables) {

    return evalWithScriptEngine(language, script, variables);
  }

  private Object evalWithScriptEngine(
      String language, String script, Map<String, Object> variables) {
    final ScriptEngine scriptEngine =
        cachedScriptEngines.computeIfAbsent(language, scriptEngineManager::getEngineByName);

    if (scriptEngine == null) {
      final String msg = String.format("No script engine found with name '%s'", language);
      throw new RuntimeException(msg);
    }

    try {
      return eval(scriptEngine, script, variables);

    } catch (ScriptException e) {
      final String msg = String.format("Failed to evaluate script '%s' (%s)", script, language);
      throw new RuntimeException(msg, e);
    }
  }

  private Object eval(ScriptEngine scriptEngine, String script, Map<String, Object> variables)
      throws ScriptException {

    final ScriptContext context = scriptEngine.getContext();
    final Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
    bindings.putAll(variables);

    return scriptEngine.eval(script, context);
  }

}