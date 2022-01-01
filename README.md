# Books-Search

## Iniciar el proyecto

### Instalaciones Obligatorias

Java version 17 https://www.oracle.com/java/technologies/downloads/ and https://www.java.com/es/download/ie_manual.jsp

Apache Maven 3.8.4 https://maven.apache.org/download.cgi

Node.Js https://nodejs.org/es/

Postgresql https://www.postgresql.org/download/

### Pasos a seguir

Clonar el repositorio de github

#### Back-end

Entrar a la carpeto Book-Search

Entrar a la carpeta demo

Una vez adentro abrir consola sobre esa carpeta, escribir mvn spring-boot:run y hacer enter

Ya esta funcionando todo el Back-end

#### Front-end

Entrar en la carpeta Book-Search

Entrar en la carpeta demo

Entrar en la carpeta src

Entrar en la carpeta main

Entrar en la carpeta frontend

Una vez dentro abrir la consola sobre esta carpeta, escribir npm install y dar enter

Una vez terminado de ejecutarse escribir npm start

Ya esta funcionando todo el Front-end

### Metodos HTTP:

#### GET:

http://localhost:8080/ -> devuelve todos los libro.

http://localhost:8080/?title= devuelve el libro con ese titulo.

http://localhost:8080/?id= devuelve el libro con ese id.

http://localhost:8080/?title=&id= devuelve el libro con ese id y el libro con ese titulo.

En caso de que el titulo o id no sean validos se avisara al usuario.

#### POST:

http://localhost:8080/

Body:{

  title:

  price:

  author:

  write:

}

En caso que el price sea nulo se otorgara 0.

En caso que el author sea nulo se otorgara Anonymous.

En caso de que write o title sean nulos se avisara al usuario y no se creara el libro.

Se puede agregar un id especifico pero en caso de que ya exista se avisara al usuario y no se creara el libro.

#### PUT:

http://localhost:8080/

Body:{

  id:

  title:

  price:

  author:

  write:
  
}

Cualquier dato puede estar en nulo menos el id.

Si el usuario quiere modificar solo el price no hace falta que ingrese todo los otros datos, pero si no agrega el id se le avisara y no se realizaran los cambios.

#### DELETE:

http://localhost:8080/id

En caso de no agregar un id existente se le avisara al usuario.
