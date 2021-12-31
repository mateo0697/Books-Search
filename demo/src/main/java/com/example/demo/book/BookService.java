package com.example.demo.book;

import com.example.demo.book.ToFront.ToFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {//aca voy a delcarar que se hace cuando se llaman a distintos metodos http

    private final BookRepository bookRepository;

    @Autowired //me conecta con BookRepository
    public BookService (BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(Long id){
        //1) Cuando llaman al metodo GET quiero que primero verifique si se fue dada una id, ya que en ese caso se busca un libro
        //en específico.
        //2)En caso de no haber un id busca todos los libros en la base de datos y la devuelve.

        //1)
        if (id != null){
            Book one = bookRepository.findById(id).orElseThrow(()-> new IllegalStateException("This Id doesnt exist"));
            List<Book> list = new ArrayList<>();
            list.add(one);
            return list;
        }
        //2)
        return bookRepository.findAll();
    }

    public ToFront addNewBook(Book book){
        //1) Cuando llaman al metodo POST primero verifico que el title y el write no sean null, no está hecho en el constructor
        // de books, ya que si la persona quiere editar un libro y mantener estos datos no es necesario que los envie.

        //2) Una vez hecho eso verifico que en caso de que se mande una id esta no exista al igual que no haya un libro
        // con el mismo título.

        //3) Por último determino que si no hay un author o price dado por el usuario se ponga por default author:"Anonymous"
        // y price:0.

        Long id = book.getId();
        String title = book.getTitle();
        //1){
        if (title == null){
            return new ToFront("Insert Title", false);
        }

        if (book.getWrite() == null){
            return new ToFront("Insert date of publishing", false);
        }
        //}
        //2){
        if (id != null){
            Optional<Book> bookById = bookRepository.findById(id);
            if (bookById.isPresent()){
                return new ToFront("Id already exist", false);
            }
        }

        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);
        if (bookByTitle.isPresent()) {
            return new ToFront("Book already exist", false);
        }
        //}
        //3){
        if (book.getAuthor()==null || Objects.equals(book.getAuthor(), "")){
            book.setAuthor("Anonymous");
        }

        if (book.getPrice()==null || Objects.equals(book.getPrice(), "")){
            book.setPrice("0");
        }
        //}
        bookRepository.save(book);

        return new ToFront("Added successfully", true);

    }

    public ToFront deleteBook(Long id){
        // 1) Cuando llaman al metodo DELETE lo que hago es verificar que haya una id dada por el usuario y que el libro con esa
        // id exista.
        //1){
        if (id.describeConstable().isEmpty()){
            return new ToFront("No id is given", false);
        }
        boolean exists = bookRepository.existsById(id);
        if (!exists){
            return new ToFront("Book with id " + id + " does not exist", false);
        }
        //}
        bookRepository.deleteById(id);
        return new ToFront("Book deleted", true);
    }

    @Transactional//Me permite utilizar book1.get().setETC que modifica los valores directamente en la base de datos.
    public ToFront putBook(Book book){
        //1) Cuando llaman al metodo PUT primero verifico que la id dada sea correcta al igual que el nuevo título no este
        // en usada otro libro.

        //2) Una vez terminado, cambio los valores del libro guardado en la base de datos por los brindados por el usuario.
        System.out.println(book);
        Long id = book.getId();
        String title = book.getTitle();
        String author = book.getAuthor();
        String price = book.getPrice();
        String write = book.getWrite();
        //1){
        if(id == null){
            return new ToFront("Id needed", false);
        }
        Optional <Book> book1 = bookRepository.findById(id);
        if(book1.isEmpty()){
            return new ToFront("This id does not exist",false);
        }

        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);

        if (bookByTitle.isPresent() && !Objects.equals(bookByTitle.get().getId(), id)) {
            return new ToFront("This title already exists", false);
        }
        //}
        //2){
        if (title != null){book1.get().setTitle(title);}
        if (author != null){book1.get().setAuthor(author);}
        if (price != null){book1.get().setPrice(price);}
        if (write != null){book1.get().setWrite(write);}
        //}
        return new ToFront("Edited successfully", true);
    }
}
