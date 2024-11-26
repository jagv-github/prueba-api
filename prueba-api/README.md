# Proyecto Backend prueba-api

## Introducción:

Este proyecto está dentro del marco de aplicaciones desarrolladas para Consum.
Se trata de la aplicación backend encargada de la implementación de los distintos servicios que adectan al dominio ...

## Prerequisitos:

### Dependencias de compilación:

Este proyecto se basa en un conjunto de librerías Java, gestionadas por Apache Maven. Requiere por tanto, el uso de Apache Maven >= 3.3.3 y un JDK 1.8.

Depende de librerías especificas desarrolladas dentro del framework de arquitectura Consum.

### Dependencias de ejecución:

El módulo requiere la presencia de un JDK 1.8 y de una base de datos iSeries DB2

## Configuración del puesto de desarrollo:

### Validación de mensajes de commit:

#### Prerequisitos:

* Se debe clonar el repositorio Git con los hooks predefinidos
```Shell
$ git clone TODO:URL XX_RAIZ_REPOSITORIO
```

* Se debe establecer la variable global de Git _**'core.hooksPath'**_ con la raiz del repositorio anterior.
```Shell
$ git config --global core.hooksPath XX_RAIZ_REPOSITORIO
```


## Compilación:

Para compilar el proyecto se utilizará el comando genérico de maven:  
```Shell
$ mvn clean install
```  

Este comando lanzará la compilación del proyecto tras ejecutar los Test Unitarios de la aplicación.

Para agilizar el desarrollo, se han excluido en este paso la ejecución de los Test de Integración.

### Empaquetado completo de la aplicacíon  

Para compilar el projecto y publicarlo en el repositorio de artefactos, es necesario ejecutar el comando anterior usando el perfil "packageFatJar" que encapsulará todas las librerías necesarias para ejecutar la aplicación de forma autónoma ("fat jar").  
```Shell
$ mvn clean install -PpackageFatJar
```  
>   el fichero "prueba-api.jar" se genera en la carpeta "target" de la aplicación

### Arranque / iniciación de la aplicación empaquetada

Para arrancar la aplicación utilizando el fichero generado se deberá ejecutar el siguiente comando (funciona tanto en windows como en Unix). Se utilizará el Tomcat embebido en la aplicación, que arrancará en el puerto por defecto (8080).
```Shell
$ java -jar prueba-api.jar
```


## Despliegue:

### Instalación:

Para arrancar la aplicación hay dos posibilidades:

#### Via Eclipse:

* Hacer click con el botón derecho sobre el proyecto _'prueba-api'_
    * Run as >
        * Maven install

#### Via línea de comandos:

```Shell
$ cd [RAIZ_PROYECTO]
$ mvn clean install
```

### Ejecución:

Para arrancar la aplicación hay dos posibilidades:

#### Via Eclipse
##### Spring-Boot:

* Hacer click con el botón derecho sobre el proyecto _'prueba-api'_
    * Run as >
        * Run configurations
* Crear una configuración para lanzar la aplicación
    * Submenu Spring Boot App > New Build
        * Establecer un nombre a la configuración: `moduleXX Start`
        * Selecionar un proyecto (prueba-api)
        * Seleccionar la clase principal (Main type): `es.consum.backend.pruebaapi.application.Application`
        * Dejar el resto de configuración por defecto.
        * **Solo para Debug Local**: En la pestaña `Source` > Add
            * Seleccionar `Java Source` y seleccionar todos los proyectos necesarios

* Ejecutar la aplicación (Run/Debug)

##### Maven:

* Hacer click con el botón derecho sobre el proyecto _'prueba-api'_
    * Run as >
        * Run configurations
* Crear una configuración para lanzar la aplicación
    * Submenu Maven Build > New Build
        * Establecer un nombre a la configuración: `moduleXX Start Maven`
        * Selecionar directorio base:
        	* Seleccionar el proyecto con el botón `Workspace...`
        * Establecer un _'Goal'_: `spring-boot:run`
        * Para mejorar el tiempo de compilación, marcar la opción `Skip Tests`
        * Asegurar que hay especificada una `Maven Runtime`
        * **Solo para Debug Local**: En la pestaña `Source` > Add
            * Seleccionar `Workspace` y aceptar

* Ejecutar la aplicación (Run/Debug)

#### Via línea de comandos:
##### Sprig-Boot:

Desde la carpeta del proyecto en la consola (prueba-api)
```Shell
$ mvn spring-boot:run
```

## Test:
### URL's principales

Para comprobar el correcto arranque de la aplicación navegar a las siguientes URL's:

URL : http://[servidor]:[puerto]/prueba-api/[path_WebService]

Ejemplos:

*  URL Swagger UI: http://localhost:8080/prueba-api/
*  URL Monitoring: http://localhost:8080/prueba-api/actuator/info
*  URL Monitoring: http://localhost:8080/prueba-api/actuator/health

> Los endpoints de swagger y monitoring vienen definidos y heredados por el paquete infra-ws, concretamente en el modulo infra-ws-monitoring


## Tests automáticos:  

### Ejecución de Tests de Integración:

Los test de integración definidos en la aplicación deben seguir la nomenclatura estándar maven definida por el plugin "failsafe" (el nombre comienza o termina por "IT"). Estas pruebas se caracterizan por requerir el arranque de la aplicación para ejecutarse, atacando sobre los enpoint publicados para comprobar la integración entre las distintas capas definidas. En nuestro caso, las clases que prueben la integración de la aplicación deben finalizar por IT.

Para ejecutar manualmente los IT se debe lanzar el siguiente comando:
```Shell
$ mvn verify -DskipIT=false
```

### Ejecución de los Test Unitarios

Para lanzar la ejecución de los test de la aplicación se debe lanzar el siguiente comando. Tener en cuenta que los Test de Integración no se lanzarán en este punto (clases cuyo nombre termine en IT):
```Shell
$ mvn test
```


## Cobertura:
Para revisar la cobertura de los TU de la aplicación desde el entorno de trabajo lanzar el comando siguiente:

Desde la carpeta del proyecto en la consola (prueba-api):
```Shell
$ mvn clean cobertura:cobertura
```
Tras la ejecución, se crean diferentes carpetas en la carpeta 'target' del proyecto. En el fichero `index.html` podemos revisar el resumen de la ejecución e ir navegando por los distintos módulos de la aplicación para mayor detalle.
* `site/cobertura`
    * `index.html`  

## Publicación de artefactos en Nexus

Para realizar la publicación de la aplicación en el repositorio de artefactos es necesario completar la configuración de del apartado ["Publicación de proyectos"](#publicaci-n-de-proyectos).  
Se debe lanzar la compilación del proyecto con el comando definido en el apartado ("Empaquetado completo de la aplicación)[#empaquetado-completo-de-la-aplicac-on]
Una vez compilada la aplicación, se debe ejecutar el siguiente comando maven:
```Shell
$ mvn clean deploy -PpackageFatJar
```  
> Tener en cuenta la política de publicaciones existente en Nexus, donde es posible que no esté permitido subir versiones ya existentes de la aplicación. Generalmente habrá diferencias entre versiones de tipo RELEASE o SNAPSHOT de la aplicación.


## Documentación:

_Capacidades heredadas del proyecto parent_ `consum-parent`  

### Generación:

Para generar la documentación automática del proyecto debe lanzarse el siguiente comando en consola (sobre el proyecto a utilizar):
```Shell
$ mvn site:site
```  

El comando genera una serie de recursos estáticos con estructura de site web en la carpeta `target` del proyecto. El punto de entrada al site es el fichero `index.html`  

### Publicación:

La configuración del proyecto permite adicionalmente publicar esta información en un repositorio nexus separado, donde se almacenarán las documentaciones generadas por proyecto y versión de la aplicación. (Revisar la configuración de nexus en el apartado ["Publicación de proyectos"](#publicaci-n-de-proyectos))
Para publicar la documentación en el repositorio nexus se debe lanzar el siguiente comando (tras haber ejecutado el anterior):
```Shell
$ mvn site:deploy
```



## Publicación de proyectos:

_Capacidades heredadas del proyecto parent_ `consum-parent`  

El proyecto aporta la configuración necesaria para publicar los artefactos generados tras la compilación del proyecto en distintos repositorios maven alojados en Nexus.  
Se definen los siguientes repositorios base en nexus:
* Maven Snapshots
* Maven Releases
* Maven Central
* Maven Site  

Se definen las siguientes variables en el `pom.xml` para configuración sobre el origen de los repositorios:
* `tools.repository.binaries.url`
* `tools.repository.site.url`
* `tools.repository.site.protocol`  

El acceso al repositorio Nexus que se utilice estará protegido por usuario y contraseña, por lo que es necesario que los desarrolladores configuren su maven local para permitir su uso. Para ello, se debe modificar el fichero `settings.xml` de la configuración de maven.
* Windows: `{BasePathDelUsuario}\.m2\settings.xml`
* Linux: `~\.m2\settings.xml`  

En el fichero se deben añadir los siguientes repositorios con sus respectivas credenciales.

* nexus-consum-releases-maven: Repositorio de Releases de aplicaciones backend. **SOLO** se permite la publicación por el servicio de integración continua. Para desarrolladores será de solo lectura
* nexus-consum-snapshots-maven: Repositorio de Snapshots
* nexus-consum-site: Repositorio de documentación de los distintos proyectos de aplicaciones / librerías backend.
* nexus-consum-public: Repositorio público de Nexus. Contiene referencias de los repositorios release, snapshot y central.

```XML
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
	<pluginGroups />

	<!-- RUTA A REPOSITORIO LOCAL MAVEN -->
	<localRepository>/WK_Consum/mvn/mvn_repository</localRepository>
	<proxies />
	<servers>
		<server>
			<id>nexus-consum-releases-maven</id>
			<username>USERNAME</username>
			<password>PASSWORD</password>
		</server>
		<server>
			<id>nexus-consum-snapshots-maven</id>
			<username>USERNAME</username>
			<password>PASSWORD</password>
		</server>
		<server>
			<id>nexus-consum-site</id>
			<username>USERNAME</username>
			<password>PASSWORD</password>
		</server>
		<server>
			<id>nexus-consum-public</id>
			<username>USERNAME</username>
			<password>PASSWORD</password>
		</server>
	</servers>

	<profiles>
		<profile>
			<id>nexus-consum</id>
			<activation>
				<activeByDefault>true</activeByDefault>
			</activation>

			<repositories>
				<repository>
					<id>nexus-consum-public</id>
					<name>Consum's Nexus Public Repository</name>
					<url>https://desnexus.consum.es/repository/maven-public/</url>
					<snapshots>
						<enabled>false</enabled>
					</snapshots>
				</repository>
			</repositories>
      <pluginRepositories>
          <pluginRepository>
              <id>nexus-consum-public</id>
              <name>Consum's Nexus Public Repository</name>
              <url>https://desnexus.consum.es/repository/maven-public/</url>
              <snapshots>
                  <enabled>false</enabled>
              </snapshots>
          </pluginRepository>
      </pluginRepositories>
		</profile>
	</profiles>

</settings>
```
