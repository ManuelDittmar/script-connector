package io.camunda.connector;

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

  @Override
  public Object execute(OutboundConnectorContext context) throws Exception {
    var connectorRequest = context.getVariablesAsType(ScriptRequest.class);

    context.validate(connectorRequest);

    return executeConnector(connectorRequest);
  }

  private Object executeConnector(final ScriptRequest connectorRequest) {
    return scriptEvaluator.evaluate(connectorRequest.getLanguage(), connectorRequest.getScript());
  }
}
