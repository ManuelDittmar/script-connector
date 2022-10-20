package io.camunda.connector;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.connector.impl.ConnectorInputException;
import io.camunda.connector.model.ScriptRequest;
import io.camunda.connector.test.outbound.OutboundConnectorContextBuilder;
import org.junit.jupiter.api.Test;

public class ScriptRequestTest {

  @Test
  void shouldMapInputCorrectly() throws JsonProcessingException {
    // given
    String variables = "{" +
        "\"language\":" +
        "\"javascript\"," +
        "\"script\":" +
        "\"print ('Hello World!')\"," +
        "\"foo\":" +
        "{\"value\": \"foo\", \"type\": \"String\"}}" +
        "}";

    ObjectMapper mapper = new ObjectMapper();

    // when
    ScriptRequest request = mapper.readValue(variables, ScriptRequest.class);

    // then
    assertThat(request.getLanguage()).isEqualTo("javascript");
    assertThat(request.getScript()).isEqualTo("print ('Hello World!')");
    assertThat(request.getVariables()).hasSize(1);
  }

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