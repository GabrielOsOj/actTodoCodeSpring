### Introduccion

En este ejercicio se nos pidió realizar un pequeño CRUD para el seguimiento de materias y cursos de un estudiante de programación.

### Consignas de la actividad:

Desarrollar una api que permita realizar las siguientes acciones:

1. Crear un nuevo curso
2. Crear temas y asociarlos a un curso
3. Obtener todos los cursos
4. Obtener todos los temas de un determinado curso
5. Obtener todos los cursos que contengan una palabra especifica
6. Modificar los datos de un curso
7. Modificar los datos de un determinado tema

## Diseño y estructura

En esta actividad, utilice una arquitectura de capas junto con el patrón de diseño MVC

Las distintas partes del proyecto están separadas según su funcionalidad. El proyecto cuenta con 5 paquetes, cada clase de la base de datos cuenta con su propio servicio, modelo, repositorio y controlador, en el caso de la clase Tema, se utilizo un DTO para recibir datos. (Explicación mas abajo).

### DTO para tema

En el endpoint tema, decidí utilizar un objeto DTOtema para recibir datos, en estructura tiene los mismos atributos que la clase tema, pero se agrego la posibilidad de enviar un Long desde el frontend. Esto es para que, en el caso de se quiera asignar el tema a un curso de forma inmediata, con solo colocar el id del curso al que pertenece se añade automáticamente (siempre y cuando el id del curso añadido exista en la base de datos, de lo contrario retorna un 404).  

## Controlador y Endpoints:

### temaController:

la URL base es:

```bash
localhost:8080/tema
```

cuenta con 3 endpoints:

### crear tema:

```bash
localhost:8080/tema/crearTema
```

Petición tipo post, recibe un objeto con estas características:

```json
{
  "idTema": long (ignorado),
  "nombre": "string",
  "descripcion": "string",
  "idCursoPerteneciente": long o null
}
```

- Si se desea asignar el tema a un curso de forma inmediata se debe agregar el id del curso, sino se deja en null
- En este endpoint, el idTema es ignorado para evitar errores

### crear varios temas:

Variante del endpoint para crear un tema, peticion tipo post, este endpoint acepta una lista de temas

```json
localhost:8080/tema/crearVariosTemas
```

y acepta:

```json
[{
  "idTema": long (ignorado),
  "nombre": "string",
  "descripcion": "string",
  "idCursoPerteneciente": long o null
},
{
  "idTema": long (ignorado),
  "nombre": "string",
  "descripcion": "string",
  "idCursoPerteneciente": long o null
}
]
```

- Aplican las mismas reglas que el endpoint crearTema

### Editar tema:

```json
localhost:8080/tema/editar
```

permite editar un tema especifico, requiere una id de un tema existente para funcionar correctamente, es una peticion tipo PUT :

```json
{
  "idTema": long (debe ser de un tema existente),
  "nombre": "string",
  "descripcion": "string"
}
```

# cursoController

ruta base:

```json
localhost:8080/curso
```

cuenta con 6 endpoints, 3 del tipo GET, 2 tipo POST y 1 tipo PUT:

## Peticiones get:

### Obtener todos los cursos:

```json
localhost:8080/curso/obtenerTodosLosCursos
```

Muestra todos los cursos existentes en la base de datos junto con los temas que tienen asignados.

### Obtener temas de un curso:

```json
localhost:8080/curso/obtenerTemasCurso
```

- Muestra todos los temas que tiene un curso
- REQUIERE UN PARAMETRO llamado idCurso junto al id del curso requerido
- si no existe un curso con ese id, retorna un 404

Ejemplo con el curso de id 1

```json
localhost:8080/curso/obtenerTemasCurso?idCurso=1
```

## Buscar curso

(me encanto como implemente este endpoint, mas detalles abajo)

```json
localhost:8080/curso/buscarCursos
```

busca cursos en base a un texto y retorna una lista de todos los que contengan la palabra ingresada en su nombre

- REQUIERE UN PARAMETRO nombre con el texto a buscar
- Si no hay ningún curso que contenga esos caracteres en su nombre, retorna un 404

Ejemplo de búsqueda de un curso con nombre “laboratorio”

```json
localhost:8080/curso/buscarCursos?nombre=programacion
```

## Peticiones post

### crear curso

```json
localhost:8080/curso/crear
```

recibe un objeto con el siguiente formato

```json
{
  "idCurso": Long (ignorado),
  "nombre": "string",
  "modalidad": "string",
  "fechaFinalizacion": "2025-02-21" (formato yyyy-mmmm-dddd),
  "listaDeTemas": [
     {
      "idTema": long (debe existir un tema con ese id en la bdd)
     },
      {
      "idTema": null (si se añade nombre y descripcion obligatorio),
      "nombre": "string",
      "descripcion": "string"
    }
  ]
}
```

- En la lista de temas, se puede agregar solo los temas por id (deben existir o devuelve un 404), un nuevo tema (con id null) o se pueden combinar temas que ya existen como temas nuevos.

### crear varios cursos

Variante para crear varios cursos en simultaneo, acepta un array de cursos, el formato y reglas son iguales que el json de crear curso

```json
localhost:8080/curso/crearVarios
```

## Petición PUT

### Editar curso

```json
localhost:8080/curso/editar
```

permite editar una clase en especifico, se debe enviar la clase completa con los parametros editados

```json
{
  "idCurso": Long (debe existir en la base de datos),
  "nombre": "string",
  "modalidad": "string",
  "fechaFinalizacion": "2025-02-21" (yyyy-mmmm-dddd),
  "listaDeTemas": [
    {
      "idTema": Long,
      "nombre": "string",
      "descripcion": "string"
    }
  ]
}
```

- Se deben incluir en el json la lista de temas, si se desea borrar un tema de un curso, simplemente no lo añada en el json
- No se puede editar los datos del tema usando este endpoint, si edita la información del tema, no se verán reflejados los cambios del tema

# Probar el proyecto

para probar el proyecto correctamente, se necesitan ciertos pasos previos:

1. Este proyecto utiliza una base de datos MySQL desplegada usando xampp
2. Una vez que instales xampp, inicia el servidor MySQL e ingresa usando phpMyAdmin (tambien puedes usar MySQLWorkwrench, que es como lo hice yo, pero necesitas la descarga, instalacion y configuración del programa), necesitas crear una nueva base de datos con el nombre de tu preferencia:

```sql
CREATE DATABASE nuevaDataBase;
```

1. con xampp y MySQL configurados y luego de haber creado la base de datos, vamos a crear un nuevo archivo .txt llamado application
2. dentro de este archivo, vamos a configurar los datos de acceso para la base de datos:

```bash
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/actividadTDC
spring.datasource.username=usuario
spring.datasource.password=contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect

```

- jdbc:mysql://127.0.0.1:3308/actividadTDC esto se divide en 3
- 127.0.0.1:3306 es la dirección de tu [localHost](http://localHost) y el puerto que usa MySQL
- actividadTDC es el nombre que tiene la base de datos que creaste usando phpMyAdmin
- username y password son el usuario y contraseña de tu base de datos, si no tiene contraseña, se deja vacio, los nombres NO van entre comillas
- los demás son configuraciones necesarias para spring, se dejan asi como están

Una vez creado el txt y haber añadido los cambios, guardamos y tenemos que cambiar la terminación del archivo, de .txt a .properties, el nombre del archivo final debe ser:

application.properties

este archivo lo vamos a usar mas adelante.

Ademas para compilar el proyecto, es necesario que cuentes con el jdk y maven correctamente configurado.

Descargamos el proyecto desde github, podemos descargarlo en formato zip o clonarlo de la siguiente forma:

1. creamos una nueva carpeta en donde vamos a clonar el proyecto.
2. ejecutamos la consola de windows en ese directorio, si utilizas windows puedes hacer click en la barra de navegacion y escribir cmd, esto ejecuta una consola en el directorio actual.
3. clonamos el repositorio usando:

```bash
git clone "https://github.com/GabrielOsOj/actTodoCodeSpring.git"
```

Una vez descargado el repositorio, (si lo bajaste comprimido, lo descomprimís). Nos dirigimos a la carpeta:

src → main → resources

y dentro colocamos el archivo application.properties 

Finalmente, nos dirigimos al directorio principal y ejecutamos el archivo iniciar.bat

si todo funciono correctamente, podemos ir a la ruta: 

```bash
http://localhost:8080/swagger-ui/index.html
```

donde podemos probar los distintos endpoints mediante swagger.

# Notas finales

### Mas endpoints

Solo cree estos endpoints porque eran los solicitados por la actividad, no descarto en un futuro crear mas endpoints para eliminar tanto clases como temas.

### Cosas que me gustaron

Este no es el primer ejercicio del curso, pero este fue uno que combino casi todos los conocimientos que aprendí en este curso, además me llevo a investigar cosas que no sabia utilizar, como por ejemplo swagger, o a hacer consultas sql puntuales usando @query, tambien investigue un poco el como crear un .bat que sea capas de buildear e iniciar el proyecto
