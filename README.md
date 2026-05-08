# Taller 04 - Sockets TCP/UDP y Threads

Repositorio académico para el Taller 04 de Introducción a los Sistemas Distribuidos.
Incluye implementaciones documentadas de comunicación por sockets TCP/UDP y una
comparación entre ejecución secuencial, `Thread` y `Runnable` en Java.

## Estructura

```text
Taller04_entrega/
├── TCP_UDP/
│   ├── cliTCPsocket.java
│   ├── serTCPsocket.java
│   ├── cliUDPsocket.java
│   ├── serUDPsocket.java
│   └── Makefile
├── THREADS/
│   ├── Cliente.java
│   ├── Cajera.java
│   ├── CajeraThread.java
│   ├── Main.java
│   ├── MainThread.java
│   ├── MainRunnable.java
│   └── Makefile
├── INFORME_Taller04.pdf
├── README.md
└── .gitignore
```

## Requisitos

- Java JDK 8 o superior.
- `make` opcional, para ejecutar los comandos del Makefile.
- Terminal con dos ventanas para probar cliente/servidor TCP o UDP.

---

## Compilar y ejecutar THREADS

```bash
cd THREADS
make all
make secuencial
make thread
make runnable
make clean
```

Resultados esperados aproximados:

- Versión secuencial: alrededor de 26 segundos.
- Versión con `Thread`: alrededor de 15 segundos.
- Versión con `Runnable`: alrededor de 15 segundos.

La mejora se da porque los dos clientes se atienden en paralelo y el tiempo total
queda cercano al cliente que más tarda.

---

## Compilar y ejecutar TCP

Terminal 1:

```bash
cd TCP_UDP
make all
make tcp-server
```

Terminal 2:

```bash
cd TCP_UDP
make tcp-client HOST=localhost
```

Escriba mensajes y termine con:

```text
fin
```

## Compilar y ejecutar UDP

Terminal 1:

```bash
cd TCP_UDP
make all
make udp-server
```

Terminal 2:

```bash
cd TCP_UDP
make udp-client HOST=localhost
```

Escriba mensajes y termine con:

```text
fin
```

---

## Diferencias principales TCP vs UDP

| Criterio | TCP | UDP |
|---|---|---|
| Conexión | Orientado a conexión | No orientado a conexión |
| Entrega | Confiable y ordenada | No garantiza entrega ni orden |
| Uso típico | Web, correo, transferencia de archivos | Streaming, juegos, DNS, telemetría |
| Sobrecarga | Mayor | Menor |
| Unidad de datos | Flujo de bytes | Datagramas |

---

## Benchmark TCP vs UDP

Para medir el rendimiento real se ejecutó un script de benchmarking
(`bench_servidor.py` / `bench_cliente.py`) enviando **10 000 mensajes** por
protocolo. Cada mensaje incluye un hash MD5 de 6 caracteres para verificar
integridad en el receptor.

### Resultados obtenidos

```
========================================================
  COMPARACION FINAL  UDP vs TCP
========================================================
  Metrica                                     UDP                TCP
  --------------------------------------------------------------------
  Mensajes esperados                        10000              10000
  Mensajes recibidos                         9397              10000
  Mensajes perdidos                           603                  0
  Errores integridad                            0                  0
  Fuera de orden                               12                  -
  Tasa de entrega (%)                        94.0              100.0
  Tiempo recep. (s)                           0.2                0.1
  Throughput (msg/s)                      54917.8           163769.6
  ➤ Mayor tasa de entrega   → TCP
  ➤ Mayor velocidad (msg/s) → TCP
  ➤ Integridad perfecta en ambos protocolos ✓
========================================================
```

### Análisis

- **Pérdida de paquetes (UDP):** se perdieron 603 de 10 000 mensajes (6 %).
  Esto es esperado en UDP bajo alta carga: los buffers del SO descartan datagramas
  cuando no se consumen a tiempo. No hay retransmisión automática.
- **Orden de llegada (UDP):** 12 paquetes llegaron fuera de orden, lo que
  confirma que UDP no garantiza secuencialidad.
- **Integridad:** ningún mensaje llegó corrupto en ninguno de los dos protocolos,
  lo cual es coherente con redes locales donde la corrupción de bits es prácticamente nula.
- **Throughput:** TCP fue aproximadamente **3× más rápido** que UDP en este escenario.
  Aunque UDP tiene menor overhead por datagrama, el cuello de botella aquí es la
  velocidad de consumo en el receptor; TCP, al ser un flujo continuo, se beneficia
  de la gestión de buffer del sistema operativo.
- **Conclusión:** para aplicaciones que requieren entrega garantizada y orden (como
  este taller), TCP es la elección correcta. UDP es preferible cuando se tolera
  pérdida y se prioriza latencia baja (streaming, juegos en red, DNS).

---

## Buenas prácticas aplicadas

- Código documentado y membretado.
- Imports explícitos.
- Cierre seguro de recursos mediante `try-with-resources`.
- Separación de módulos por tema: `TCP_UDP/` y `THREADS/`.
- `.gitignore` para evitar subir `.class`, `bin/`, objetos, ejecutables o comprimidos.
- Informe PDF con objetivos, funcionamiento, pruebas, comparativas, conclusiones y referencias.


  ---

  ## Autor
  Jose David Medina Salgado
