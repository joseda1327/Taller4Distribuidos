# ***********************************************************************************************
# PONTIFICIA UNIVERSIDAD JAVERIANA
# Taller 04 - Sockets TCP/UDP y Threads
# Autor: Luis Santos
# Fecha: 8 de mayo de 2026
# Archivo: Makefile para TCP_UDP
# ***********************************************************************************************

JAVAC = javac
JAVA = java
BIN = bin
SOURCES = *.java
HOST = localhost

.PHONY: all tcp-server tcp-client udp-server udp-client clean

all:
	@mkdir -p $(BIN)
	$(JAVAC) -d $(BIN) $(SOURCES)

tcp-server: all
	$(JAVA) -cp $(BIN) serTCPsocket

tcp-client: all
	$(JAVA) -cp $(BIN) cliTCPsocket $(HOST)

udp-server: all
	$(JAVA) -cp $(BIN) serUDPsocket

udp-client: all
	$(JAVA) -cp $(BIN) cliUDPsocket $(HOST)

clean:
	$(RM) -r $(BIN)
