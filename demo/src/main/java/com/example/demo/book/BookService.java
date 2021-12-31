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

    public ToFront getBooks(Long id, String title){
        //1) Cuando llaman al metodo GET quiero que primero verifique si se fue dada una id o un titulo, ya que en ese
        // caso se busca un libro en específico.

        //2)En caso de no haber ninguno de estos busca todos los libros en la base de datos y la devuelve.

        //1)
        if (id != null){
            Optional<Book> one = bookRepository.findById(id);
            if (one.isEmpty()){
                return new ToFront("This Id doesnt exist", false);
            }
            List<Book> list = new ArrayList<>();
            list.add(one.get());
            return new ToFront("These is the book with id " + id, true, list);
        }
        if (title!=null && title != ""){
            Optional<Book> one = bookRepository.findBookByTitle(title);
            if (one.isEmpty()){
                return new ToFront("This Title doesnt exist", false);
            }
            List<Book> list = new ArrayList<>();
            list.add(one.get());
            return new ToFront("These is the book with id " + id, true, list);
        }
        //2)
        return new ToFront("These are all the books", true,bookRepository.findAll());
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
            List<Book> list = new ArrayList<>();
            list.add(book);
            return new ToFront("Added successfully", true, list);

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
            return new ToFront("Book with id" + id + "does not exist", false);
        }
        //}
        bookRepository.deleteById(id);
        return new ToFront("Book deleted", true);
    }

    @Transactional//Me permite utilizar book1.get().setETC que modifica los valores directamente en la base de datos.
    public ToFront putBook(Book newbook){
        //1) Cuando llaman al metodo PUT primero verifico que la id dada sea correcta al igual que el nuevo título no este
        // en usada otro libro.

        //2) Una vez terminado, cambio los valores del libro guardado en la base de datos por los brindados por el usuario.
        System.out.println(newbook);
        Long id = newbook.getId();
        String title = newbook.getTitle();
        String author = newbook.getAuthor();
        String price = newbook.getPrice();
        String write = newbook.getWrite();
        //1){
        Optional <Book> book = bookRepository.findById(id);
        if(book.isEmpty()){
            return new ToFront("This id does not exist",false);
        }

        Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);

        if (bookByTitle.isPresent() && !Objects.equals(bookByTitle.get().getId(), id)) {
            return new ToFront("This title already exists", false);
        }
        //}
        //2){
        if (title != null){book.get().setTitle(title);}else{newbook.setTitle(book.get().getTitle());}
        if (author != null){book.get().setAuthor(author);}else{newbook.setAuthor(book.get().getAuthor());}
        if (price != null){book.get().setPrice(price);}else{newbook.setPrice(book.get().getPrice());}
        if (write != null){book.get().setWrite(write);}else{newbook.setWrite(book.get().getWrite());}
        //}
        List<Book> list = new ArrayList<>();
        list.add(newbook);
        return new ToFront("Edited successfully", true, list);
    }
}
