> A template for new C8 connectors.
>
> To use this template update the following resources to match the name of your connector:
>
> * [Connector Function](./src/main/java/io/camunda/connector/MyConnectorFunction.java) (rename, implement, update `OutboundConnector` annotation)
>
> ...and delete this hint.


# Script Connector

Camunda Connector to execute Scripts.
Main Implementation was inspired by the [Zeebe Script Worker](https://github.com/camunda-community-hub/zeebe-script-worker/blob/master/src/main/resources/META-INF/services/org.camunda.feel.valuemapper.CustomValueMapper)

## Build

```bash
mvn clean package
```

## API

### Input

```json
{
  "language": ".....",
  "script": "....."
}
```

### Output

```json
{
  "result": "....."
}
```

## Test locally

Maven package

```bash
mvn clean package
```

Built docker image

```bash
docker build . -t script-connector:0.1.0
```

Run docker-compose

```bash
docker compose up
```

## Element Template

The element templates can be found in the [element-templates/template-connector.json](element-templates/script-connector.json) file.
