package io.camunda.connector;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.camunda.connector.impl.ConnectorInputException;
import io.camunda.connector.model.ScriptRequest;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class ScriptRequestTest {

  @Test
  void shouldFailWhenValidate_NoLanguage() {
    // given
    var input = new ScriptRequest();
    input.setScript("print ('Hello World!')");
    var context = OutboundConnectorContextBuilder.create().build();
    // when
    assertThatThrownBy(() -> context.validate(input))
      // then
      .isInstanceOf(ConnectorInputException.class)
      .hasMessageContaining("language");
  }

  @Test
  void shouldFailWhenValidate_NoScript() {
    // given
    var input = new ScriptRequest();
    input.setLanguage("javascript");
    var context = OutboundConnectorContextBuilder.create().build();
    // when
    assertThatThrownBy(() -> context.validate(input))
        // then
        .isInstanceOf(ConnectorInputException.class)
        .hasMessageContaining("script");
  }

}