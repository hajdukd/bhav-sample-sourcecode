#!/bin/bash
echo "Configured JAVA_OPTS:"
echo $JAVA_OPTS
echo "Starting Application: $APP_VERSION"
exec java $JAVA_OPTS -jar app.jar "$@"
