package io.camunda.connector;

import static org.assertj.core.api.Assertions.assertThat;

import io.camunda.connector.model.ScriptRequest;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class ScriptConnectorFunctionTest {

  @Test
  void shouldReturnResultWhenExecuteJavaScript() throws Exception {
    // given
    var input = new ScriptRequest();
    input.setLanguage("javascript");
    input.setScript("5 + 7");
    var function = new ScriptConnectorFunction();
    var context = OutboundConnectorContextBuilder.create()
      .variables(input)
      .build();
    // when
    var result = function.execute(context);
    // then
    assertThat(result)
      .isEqualTo(12);
  }

  @Test
  void shouldReturnResultWhenExecuteGroovy() throws Exception {
    // given
    var input = new ScriptRequest();
    input.setLanguage("groovy");
    input.setScript("5 + 7");
    var function = new ScriptConnectorFunction();
    var context = OutboundConnectorContextBuilder.create()
        .variables(input)
        .build();
    // when
    var result = function.execute(context);
    // then
    assertThat(result)
        .isEqualTo(12);
  }
}