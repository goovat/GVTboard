#!/usr/bin/env sh
if [ ! -d "$HOME/.gradle/wrapper/dists/gradle-8.0-bin" ]; then
    echo "Downloading Gradle 8.0..."
    curl -L https://services.gradle.org/distributions/gradle-8.0-bin.zip -o /tmp/gradle-8.0-bin.zip
    unzip -q /tmp/gradle-8.0-bin.zip -d "$HOME/.gradle/wrapper/dists/"
    mv "$HOME/.gradle/wrapper/dists/gradle-8.0" "$HOME/.gradle/wrapper/dists/gradle-8.0-bin"
    rm /tmp/gradle-8.0-bin.zip
fi
exec "$HOME/.gradle/wrapper/dists/gradle-8.0-bin/bin/gradle" "$@"
