FROM camunda/connectors:0.2.2

COPY target/script-connector-*SNAPSHOT.jar /opt/app

# Using entry point to allow downstream images to add JVM arguments using CMD
ENTRYPOINT ["java", "-cp", "/opt/app/*", "io.camunda.connector.runtime.jobworker.Main"]


