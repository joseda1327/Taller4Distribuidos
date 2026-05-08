# ***********************************************************************************************
# PONTIFICIA UNIVERSIDAD JAVERIANA
# Taller 04 - Sockets TCP/UDP y Threads
# Autor: Luis Santos
# Fecha: 8 de mayo de 2026
# Archivo de automatizacion de compilacion Makefile <JAVA>
# ***********************************************************************************************

JAVAC = javac
JAVA = java
PKG = tallerThreads
BIN = bin
SOURCES = *.java

.PHONY: all secuencial thread runnable clean

all:
	@mkdir -p $(BIN)
	$(JAVAC) -d $(BIN) $(SOURCES)

secuencial: all
	@echo "\n>>> EJECUCION version Secuencial <<<\n"
	$(JAVA) -cp $(BIN) $(PKG).Main
	@echo ""

thread: all
	@echo "\n>>> EJECUCION version Thread <<<\n"
	$(JAVA) -cp $(BIN) $(PKG).MainThread
	@echo ""

runnable: all
	@echo "\n>>> EJECUCION version Runnable <<<\n"
	$(JAVA) -cp $(BIN) $(PKG).MainRunnable
	@echo ""

clean:
	$(RM) -r $(BIN)
