# Books-Search

## Iniciar el proyecto

### Instalaciones Obligatorias

Java versión 17 https://www.oracle.com/java/technologies/downloads/ and https://www.java.com/es/download/ie_manual.jsp

Apache Maven 3.8.4 https://maven.apache.org/download.cgi

Node.Js https://nodejs.org/es/

Postgresql https://www.postgresql.org/download/

### Pasos a seguir

Clonar el repositorio de github

#### Back-end

Entrar a la carpeta Book-Search

Entrar a la carpeta demo

Una vez adentro abrir consola sobre esa carpeta, escribir mvn spring-boot:run y hacer enter

Ya está funcionando todo el Back-end

Cuando se inicia se crean por defecto 2 libros, si no se quiere esto vaya al archivo en BookSearch/demo/src/main/java/com/example/demo/book/BookConfig.java y borre todo el codigo desde la línea 14 inclusive hasta la 35(Desde @Bean, hasta el cierre de llaves de CommandLineRunner)

#### Front-end

Entrar en la carpeta Book-Search

Entrar en la carpeta demo

Entrar en la carpeta src

Entrar en la carpeta main

Entrar en la carpeta frontend

Una vez dentro abrir la consola sobre esta carpeta, escribir npm install y dar enter

Una vez terminado de ejecutarse escribir npm start

Ya está funcionando todo el Front-end

### Metodos HTTP:

#### GET:

http://localhost:8080/books/ -> devuelve todos los libro.

##### Querys únicos posibles(solo se aceptan uno de estos a la vez):

?title= : devuelve el libro con ese título.

?id= : devuelve el libro con ese id.

En caso de que el título o id no sean válidos se avisara al usuario.

##### Querys combinables posibles:

###### Filtrado:

?author= : devuelve todos los libro de ese autor

?price=> / ?price=< / ?price= : devuelve todos los libros con precios menores e iguales, mayores e iguales o solo iguales(El orden mostrados es el mismo explicado)

?write=> / ?write=< / ?write= : devuelve todos los libros con fechas de publicación menores e iguales, mayores e iguales o solo iguales(El orden mostrados es el mismo explicado)

Si no existen libros con esas características se avisara al usuario.

###### Ordenamiento(acepta solo un valor):

?sort=author / ?sort=write / ?sort=price : ordena los libro según el parámetro elegido. Por default lo ordena ascendentemente, si se quiere cambiar agregar un - después del =

Si no se otorga un sort se devolverán ordenados de la misma forma en la que fueron creados.

###### Paginación:

?page= : la pagina que queremos seleccionar

?eachPage= : la cantidad de libro por página

Si page o eachPage es nulo se devolverán todos los libro.


#### POST:

http://localhost:8080/books/

Body:{

  title:

  price: (only numbers)

  author:

  write: (yyyy-mm-dd)(esa cantidad de caracteres es requerida)

}

En caso de que el price sea nulo se otorgara 0.

En caso de que el author sea nulo se otorgara Anonymous.

En caso de que write o title sean nulos se avisara al usuario y no se generara el libro.

Se puede agregar un id específico, pero en caso de que ya exista se avisara al usuario y no se generara el libro.

#### PUT:

http://localhost:8080/books/

Body:{

  id:

  title:

  price: (only numbers)

  author:

  write: (yyyy-mm-dd)(esa cantidad de caracteres es requerida)

}

Cualquier dato puede estar en nulo menos el id.

Si el usuario quiere modificar solo el price no hace falta que ingrese todos los otros datos, pero si no agrega el id se le avisara y no se realizaran los cambios.

#### DELETE:

http://localhost:8080/books/id

En caso de no agregar un id existente se le avisará al usuario.
