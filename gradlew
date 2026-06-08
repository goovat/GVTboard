#!/usr/bin/env bash

# Find the Java home
if [ -z "$JAVA_HOME" ]; then
    JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java))))
fi

# Find gradle wrapper jar
GRADLE_WRAPPER_JAR="$(dirname "$0")/gradle/wrapper/gradle-wrapper.jar"

# Execute gradle
exec "$JAVA_HOME/bin/java" -jar "$GRADLE_WRAPPER_JAR" "$@"
