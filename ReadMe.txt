Proyecto manejo de test con Junit
=================================

La ruta mueve los archivos que se encuentran en la carpeta files/incoming, a la carpeta files/outgoing.

Para correr el proyecto, desde consola ejecutar:

    mvn celan camel:run

Desde JBoss developer studio, crear un perfil de ejecuciÃ³n:

	clean camel:run
	
Para ejecutar las pruebas dede JBoss developer studio:

	clic derecho sobre el proyecto -> Run As -> JUnit Test
	