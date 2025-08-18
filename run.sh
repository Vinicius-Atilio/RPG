#!/bin/bash

# Caminho para a pasta compilada
PROJECT_DIR="/Users/vinicius.atilio/mentoria/rpg/out/production/rpg"

# Caminho para o Java (opcional, se não quiser depender do PATH)
JAVA_HOME="/Users/vinicius.atilio/Library/Java/JavaVirtualMachines/corretto-24.0.1/Contents/Home"

# Executa a Main
"$JAVA_HOME/bin/java" -Dfile.encoding=UTF-8 -classpath "$PROJECT_DIR" Main
