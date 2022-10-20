package io.camunda.connector;

import static org.assertj.core.api.Assertions.assertThat;

import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class ScriptConnectorFunctionTest {

  String input =
      "{\n"
          + "   \"language\":\"javascript\",\n"
          + "   \"script\":\"a + b\",\n"
          + "   \"a\":5,\n"
          + "   \"b\":7\n"
          + "}";

  @Test
  void shouldReturnResultWhenExecuteJavaScript() throws Exception {
    // given
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