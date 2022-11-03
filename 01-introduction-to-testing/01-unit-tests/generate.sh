#!/usr/bin/env bash
mvn archetype:generate -DgroupId=com.example -DartifactId=unit-tests -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
cd unit-tests
mvn -N io.takari:maven:wrapper