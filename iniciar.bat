@echo off
@echo Bienvenido al ejercicio de java de TDC
@echo iniciando configuraciones...

@echo validando maven...


@echo maven valido, compilando...

call mvn clean package

echo app compilada correctamente, iniciando app...
java -jar target\actividadTDC-0.0.1-SNAPSHOT.jar

pause
