package io.camunda.connector.model;

import jakarta.validation.constraints.NotEmpty;

import java.util.Objects;

public class ScriptRequest {

  @NotEmpty
  private String language;
  @NotEmpty
  private String script;

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


  @Override
  public int hashCode() {
    return Objects.hash(language, script);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    ScriptRequest other = (ScriptRequest) obj;
    return Objects.equals(script, other.script)
        && Objects.equals(language, other.language);
  }

  @Override
  public String toString() {
    return "ScriptRequest{" +
        "language='" + language + '\'' +
        ", script='" + script + '\'' +
        '}';
  }
}
