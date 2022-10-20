package io.camunda.connector.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ScriptRequest {

  @NotEmpty
  private String language;
  @NotEmpty
  private String script;

  @JsonIgnore
  private Map<String, Object> variables = new HashMap<>();

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  public String getScript() {
    return script;
  }

  public void setScript(String script) {
    this.script = script;
  }

  @JsonAnyGetter
  public Map<String, Object> getVariables() {
    return variables;
  }

  @JsonAnySetter
  public void addVariable(String key, Object value) {
    this.variables.put(key, value);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ScriptRequest that = (ScriptRequest) o;
    return Objects.equals(language, that.language) && Objects.equals(script,
        that.script) && Objects.equals(variables, that.variables);
  }

  @Override
  public int hashCode() {
    return Objects.hash(language, script, variables);
  }

  @Override
  public String toString() {
    return "ScriptRequest{" +
        "language='" + language + '\'' +
        ", script='" + script + '\'' +
        ", variables=" + variables +
        '}';
  }
}
