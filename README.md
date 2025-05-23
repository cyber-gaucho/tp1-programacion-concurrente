# Proyecto TP1 - Programación Concurrente 2025


Este proyecto simula el flujo concurrente de procesamiento de pedidos en un sistema de logística automatizada, utilizando estructuras de datos seguras para múltiples hilos (`LinkedBlockingDeque`) y controlando la ejecución de múltiples procesos con distintos tiempos de espera y probabilidades de fallo.

---

## Objetivo

El objetivo principal del trabajo práctico es aplicar conceptos de programación concurrente como:

- Hilos y concurrencia controlada
- Comunicación entre procesos con estructuras thread-safe
- Sincronización y manejo de condiciones excepcionales
- Generación de logs estadísticos para análisis de rendimiento

---

## Requisitos

### Para compilar o ejecutar el proyecto:

- **Java 17 o superior**
- **Maven 3.6+** (solo si querés compilar desde código fuente)

### Para ejecutar directamente:

Descargar y tener disponible el archivo compilado:

    target/Proyecto_TP1-1.0-SNAPSHOT.jar


Y ejecutarlo con:


    java -jar Proyecto_TP1-1.0-SNAPSHOT.jar

---

## Estructura del Proyecto

    Proyecto_TP1/ 
    ├── src/
    │   └── main/
    │       └── java/
    │           └── org/example/
    │               ├── Main.java
    │               ├── Casillero.java
    │               ├── CentroDeAlmacenamiento.java
    │               ├── ...
    ├── target/
    │   └── Proyecto_TP1-1.0-SNAPSHOT.jar
    ├── LogEstadísticoTXT.txt
    ├── LogEstadísticoCSV.csv
    ├── pom.xml
    ├── README.md
    ├── Diagrama de clases.png
    ├── Diagrama de secuencia.png
    ├── Informe TP1 - Fairness con coca.pdf
    └── Enunciado TP1 Concurrente 2025.pdf


---

## Descripción de Funcionalidades

CentroDeAlmacenamiento: Gestiona el almacenamiento de pedidos con casilleros.

Despachador, Entregador, Preparador, Verificador: Son clases que simulan procesos concurrentes de la cadena logística.

Logs:

- LogEstadísticoTXT.txt: Detallado y descriptivo, útil para revisión manual.

- LogEstadísticoCSV.csv: Compatible con herramientas como LibreOffice Calc o Excel para generar gráficos.

Tiempos de espera aleatorios y configurables: Los procesos simulan comportamiento realista.

---

## Visualización de Resultados

Podés abrir el archivo LogEstadísticoCSV.csv con LibreOffice Calc o Microsoft Excel para graficar:

- Abrir con delimitador coma (,).

- Marcar que la primera fila contiene encabezados.

- Insertar gráficos de líneas, barras o sectores según lo que quieras analizar.
