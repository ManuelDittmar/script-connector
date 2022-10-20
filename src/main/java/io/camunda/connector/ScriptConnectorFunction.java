package io.camunda.connector;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.camunda.connector.api.annotation.OutboundConnector;
import io.camunda.connector.api.outbound.OutboundConnectorContext;
import io.camunda.connector.api.outbound.OutboundConnectorFunction;
import io.camunda.connector.model.ScriptRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@OutboundConnector(
    name = "SCRIPTCONNECTOR",
    inputVariables = {"language", "script"},
    type = "io.camunda:script")
public class ScriptConnectorFunction implements OutboundConnectorFunction {

  private static final Logger LOGGER = LoggerFactory.getLogger(ScriptConnectorFunction.class);
  private final ScriptEvaluator scriptEvaluator = new ScriptEvaluator();
  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public Object execute(OutboundConnectorContext context) throws Exception {

    ScriptRequest request = objectMapper.readValue(context.getVariables(), ScriptRequest.class);

    context.validate(request);

    return executeConnector(request);
  }

  private Object executeConnector(final ScriptRequest connectorRequest) {
    return scriptEvaluator.evaluate(connectorRequest.getLanguage(), connectorRequest.getScript(), connectorRequest.getVariables());
  }
}
