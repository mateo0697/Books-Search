package com.example.demo.book;

import com.example.demo.book.Functions.SortAndPages;
import com.example.demo.book.ToFront.ToFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class BookService {//aca voy a delcarar que se hace cuando se llaman a distintos metodos http

    @Autowired //me conecta con BookRepository
    BookRepository bookRepository;



    public ResponseEntity<ToFront> getBooks(Long id, String title, String author, String price, String write, String sort, Integer page, Integer eachPage){
        //1) Cuando llaman al metodo GET quiero que primero verifique si se fue dada una id o un titulo, ya que en ese
        // caso se busca un libro en específico.

        //2)En caso de no haber ninguno de estos busca todos los libros en la base de datos y la devuelve.
        //1)
        try{
            Pageable SortPage = null;
            if (page == null){
                page = 0;
            }
            if(eachPage == null){
                eachPage = Integer.MAX_VALUE;
            }

            SortPage = SortAndPages.sortAndPages(sort, page, eachPage);
            if (id == null && title == null && author == null && price == null && write == null){
                return new ResponseEntity<>(new ToFront("These are all the books", true, bookRepository.findAll(SortPage).getContent()), HttpStatus.OK);
            }
            if (id != null){
                Optional<Book> one = bookRepository.findById(id);
                if (one.isEmpty()){
                    return new ResponseEntity<>(new ToFront("This Id " + id + " does not exist", false), HttpStatus.NOT_FOUND);
                }
                List<Book> list = new ArrayList<>();
                list.add(one.get());
                return new ResponseEntity<>(new ToFront("This is the book with id " + id, true, list), HttpStatus.OK);
            }
            if (title!=null && !title.equals("")){
                Optional<Book> one = bookRepository.findBookByTitle(title);
                if (one.isEmpty()){
                    return new ResponseEntity<>(new ToFront("This Title " + title + " does not exist", false), HttpStatus.NOT_FOUND);
                }
                List<Book> list = new ArrayList<>();
                list.add(one.get());
                return new ResponseEntity<>(new ToFront("This is the book with title " + title, true, list), HttpStatus.OK);
            }
            if (author != null && price != null && write != null){
                String firstW = String.valueOf(write.charAt(0));
                String firstP = String.valueOf(price.charAt(0));
                Optional<List<Book>> byPriceAuthorAndWrite = Optional.empty();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d", Locale.ENGLISH);
                if(firstP.equals("<") && firstW.equals("<")){
                    byPriceAuthorAndWrite = bookRepository.findAllByAuthorAndPriceGreaterThanEqualAndWriteGreaterThanEqual(author, Integer.parseInt(price.substring(1)), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if(firstP.equals("<") && firstW.equals(">")){
                    byPriceAuthorAndWrite = bookRepository.findAllByAuthorAndPriceGreaterThanEqualAndWriteLessThanEqual(author, Integer.parseInt(price.substring(1)), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if(firstP.equals(">") && firstW.equals("<")){
                    byPriceAuthorAndWrite = bookRepository.findAllByAuthorAndPriceLessThanEqualAndWriteGreaterThanEqual(author, Integer.parseInt(price.substring(1)), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if(firstP.equals(">") && firstW.equals(">")){
                    byPriceAuthorAndWrite = bookRepository.findAllByAuthorAndPriceLessThanEqualAndWriteLessThanEqual(author, Integer.parseInt(price.substring(1)), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if((!firstP.equals(">") && !firstP.equals("<")) && firstW.equals(">")){
                    byPriceAuthorAndWrite = bookRepository.findAllByAuthorAndPriceAndWriteLessThanEqual(author, Integer.parseInt(price), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if((!firstP.equals(">") && !firstP.equals("<")) && firstW.equals("<")){
                    byPriceAuthorAndWrite = bookRepository.findAllByAuthorAndPriceAndWriteGreaterThanEqual(author, Integer.parseInt(price), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if(firstP.equals(">")){
                    byPriceAuthorAndWrite = bookRepository.findAllByAuthorAndPriceLessThanEqualAndWrite(author, Integer.parseInt(price.substring(1)), LocalDate.parse(write, formatter), SortPage);
                }else if(firstP.equals("<")){
                    byPriceAuthorAndWrite = bookRepository.findAllByAuthorAndPriceGreaterThanEqualAndWrite(author, Integer.parseInt(price.substring(1)), LocalDate.parse(write, formatter), SortPage);
                }else {
                    byPriceAuthorAndWrite = bookRepository.findAllByAuthorAndPriceAndWrite(author, Integer.parseInt(price), LocalDate.parse(write, formatter), SortPage);
                }
                if (byPriceAuthorAndWrite.isEmpty() || byPriceAuthorAndWrite.get().size() == 0){
                    return new ResponseEntity<>(new ToFront("Books for that author, price and write do not exist", false), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new ToFront("These are the books for that author, price and write", true,byPriceAuthorAndWrite.get()), HttpStatus.OK);
            }
            if (author != null && price != null){
                String first = String.valueOf(price.charAt(0));
                Optional<List<Book>> byPriceAndAuthor = Optional.empty();
                if (first.equals("<")){
                    byPriceAndAuthor = bookRepository.findAllByAuthorAndPriceGreaterThanEqual(author, Integer.parseInt(price.substring(1)), SortPage);
                }else if(first.equals(">")){
                    byPriceAndAuthor = bookRepository.findAllByAuthorAndPriceLessThanEqual(author,Integer.parseInt(price.substring(1)), SortPage);
                }else{
                    byPriceAndAuthor = bookRepository.findAllByAuthorAndPrice(author, Integer.parseInt(price), SortPage);
                }
                if (byPriceAndAuthor.isEmpty() || byPriceAndAuthor.get().size() == 0){
                    return new ResponseEntity<>(new ToFront("Books for that author and price do not exist", false), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new ToFront("These are the books for that author and price", true,byPriceAndAuthor.get()), HttpStatus.OK);
            }

            if (author != null && write != null){
                String first = String.valueOf(write.charAt(0));
                Optional<List<Book>> byWriteAndAuthor = Optional.empty();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d", Locale.ENGLISH);
                if (first.equals("<")){
                    byWriteAndAuthor = bookRepository.findAllByAuthorAndWriteGreaterThanEqual(author, LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if(first.equals(">")){
                    byWriteAndAuthor = bookRepository.findAllByAuthorAndWriteLessThanEqual(author,LocalDate.parse(write.substring(1), formatter), SortPage);
                }else{
                    byWriteAndAuthor = bookRepository.findAllByAuthorAndWrite(author,LocalDate.parse(write, formatter), SortPage);
                }
                if (byWriteAndAuthor.isEmpty() || byWriteAndAuthor.get().size() == 0){
                    return new ResponseEntity<>(new ToFront("Books for that author and write do not exist", false), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new ToFront("These are the books for that author and write", true,byWriteAndAuthor.get()), HttpStatus.OK);
            }

            if (write != null && price != null){
                String firstW = String.valueOf(write.charAt(0));
                String firstP = String.valueOf(price.charAt(0));
                Optional<List<Book>> byPriceAndWrite = Optional.empty();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d", Locale.ENGLISH);
                if(firstP.equals("<") && firstW.equals("<")){
                    byPriceAndWrite = bookRepository.findAllByPriceGreaterThanEqualAndWriteGreaterThanEqual(Integer.parseInt(price.substring(1)), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if(firstP.equals("<") && firstW.equals(">")){
                    byPriceAndWrite = bookRepository.findAllByPriceGreaterThanEqualAndWriteLessThanEqual(Integer.parseInt(price.substring(1)), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if(firstP.equals(">") && firstW.equals("<")){
                    byPriceAndWrite = bookRepository.findAllByPriceLessThanEqualAndWriteGreaterThanEqual(Integer.parseInt(price.substring(1)), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if(firstP.equals(">") && firstW.equals(">")){
                    byPriceAndWrite = bookRepository.findAllByPriceLessThanEqualAndWriteLessThanEqual(Integer.parseInt(price.substring(1)), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if((!firstP.equals(">") && !firstP.equals("<")) && firstW.equals(">")){
                    byPriceAndWrite = bookRepository.findAllByPriceAndWriteLessThanEqual(Integer.parseInt(price), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if((!firstP.equals(">") && !firstP.equals("<")) && firstW.equals("<")){
                    byPriceAndWrite = bookRepository.findAllByPriceAndWriteGreaterThanEqual(Integer.parseInt(price), LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if(firstP.equals(">")){
                    byPriceAndWrite = bookRepository.findAllByPriceLessThanEqualAndWrite( Integer.parseInt(price.substring(1)), LocalDate.parse(write, formatter), SortPage);
                }else if(firstP.equals("<")){
                    byPriceAndWrite = bookRepository.findAllByPriceGreaterThanEqualAndWrite(Integer.parseInt(price.substring(1)), LocalDate.parse(write, formatter), SortPage);
                }else {
                    byPriceAndWrite = bookRepository.findAllByPriceAndWrite(Integer.parseInt(price), LocalDate.parse(write, formatter), SortPage);
                }
                if (byPriceAndWrite.isEmpty() || byPriceAndWrite.get().size() == 0){
                    return new ResponseEntity<>(new ToFront("Books for that write and price do not exist", false), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new ToFront("These are the books for that write and price", true,byPriceAndWrite.get()), HttpStatus.OK);
            }

            if(author != null){
                System.out.println(author);
                Optional<List<Book>> byAuthor = bookRepository.findAllByAuthor(author, SortPage);
                if (byAuthor.isEmpty() || byAuthor.get().size() == 0){
                    return new ResponseEntity<>(new ToFront("Books for that author do not exist", false), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new ToFront("This are all the books of this author", true, byAuthor.get()), HttpStatus.OK);
            }

            if (price != null){
                String first = String.valueOf(price.charAt(0));
                Optional<List<Book>> byPrice = Optional.empty();
                int index = 0;
                if (first.equals("<")){
                    System.out.println(Integer.parseInt(price.substring(1)));
                    byPrice = bookRepository.findAllByPriceGreaterThanEqual(Integer.parseInt(price.substring(1)),SortPage);
                    index = 1;
                }else if(first.equals(">")){
                    System.out.println(Integer.parseInt(price.substring(1)));
                    byPrice = bookRepository.findAllByPriceLessThanEqual(Integer.parseInt(price.substring(1)),SortPage);
                    index = 1;
                }else{
                    byPrice = bookRepository.findAllByPrice(Integer.parseInt(price), SortPage);
                }
                if (byPrice.isEmpty() || byPrice.get().size() == 0){
                    return new ResponseEntity<>(new ToFront("Books for that price do not exist", false), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new ToFront("These are the books for that price", true, byPrice.get()), HttpStatus.OK);
            }

            if (write != null){
                String first = String.valueOf(write.charAt(0));
                Optional<List<Book>> byWrite = Optional.empty();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d", Locale.ENGLISH);
                if (first.equals("<")){
                    byWrite = bookRepository.findAllByWriteGreaterThanEqual(LocalDate.parse(write.substring(1), formatter), SortPage);
                }else if(first.equals(">")){
                    byWrite = bookRepository.findAllByWriteLessThanEqual(LocalDate.parse(write.substring(1), formatter), SortPage);
                }else{
                    byWrite = bookRepository.findAllByWrite(LocalDate.parse(write, formatter), SortPage);
                }
                if (byWrite.isEmpty() || byWrite.get().size() == 0){
                    return new ResponseEntity<>(new ToFront("Books for that write do not exist", false), HttpStatus.BAD_REQUEST);
                }
                return new ResponseEntity<>(new ToFront("This are the books for that write", true, byWrite.get()), HttpStatus.OK);
            }
            return new ResponseEntity<>(new ToFront("Books for that write do not exist", false), HttpStatus.BAD_REQUEST);
        }catch(Exception e){
            throw new InternalError(e.getMessage());
        }
    }

    public ResponseEntity<ToFront> addNewBook(Book book){
        //1) Cuando llaman al metodo POST primero verifico que el title y el write no sean null, no está hecho en el constructor
        // de books, ya que si la persona quiere editar un libro y mantener estos datos no es necesario que los envie.

        //2) Una vez hecho eso verifico que en caso de que se mande una id esta no exista al igual que no haya un libro
        // con el mismo título.

        //3) Por último determino que si no hay un author o price dado por el usuario se ponga por default author:"Anonymous"
        // y price:0.

        try{
            Long id = book.getId();
            String title = book.getTitle();
            //1){
            if (title == null){
                return new ResponseEntity<>(new ToFront("Insert Title", false), HttpStatus.BAD_REQUEST);
            }

            if (book.getWrite() == null){
                return new ResponseEntity<>(new ToFront("Insert date of publishing", false), HttpStatus.BAD_REQUEST);
            }
//            else{
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy", Locale.ENGLISH);
//                LocalDate date = LocalDate.parse(book.getWrite(), formatter);
//                book.setDop(date);
//            }
            //}
            //2){
            if (id != null){
                Optional<Book> bookById = bookRepository.findById(id);
                if (bookById.isPresent()){
                    return new ResponseEntity<>(new ToFront("Id already exist", false), HttpStatus.BAD_REQUEST);
                }
            }

            Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);
            if (bookByTitle.isPresent()) {
                return new ResponseEntity<>(new ToFront("Book already exist", false), HttpStatus.BAD_REQUEST);
            }
            //}
            //3){
            if (book.getAuthor()==null || Objects.equals(book.getAuthor(), "")){
                book.setAuthor("Anonymous");
            }

            if (book.getPrice()==null || Objects.equals(book.getPrice(), "")){
                book.setPrice(0);
            }
            //}
            bookRepository.save(book);
            List<Book> list = new ArrayList<>();
            list.add(book);
            return new ResponseEntity<>(new ToFront("Added successfully", true, list), HttpStatus.CREATED);
        }catch(Exception e){
            throw new InternalError(e.getMessage());
        }

    }

    public ResponseEntity<ToFront> deleteBook(Long id){
        // 1) Cuando llaman al metodo DELETE lo que hago es verificar que haya una id dada por el usuario y que el libro con esa
        // id exista.
        //1){
        try{
            if (id.describeConstable().isEmpty()){
                return new ResponseEntity<>(new ToFront("No id is given", false), HttpStatus.BAD_REQUEST);
            }
            boolean exists = bookRepository.existsById(id);
            if (!exists){
                return new ResponseEntity<>(new ToFront("Book with id" + id + "does not exist", false), HttpStatus.BAD_REQUEST);
            }
            //}
            bookRepository.deleteById(id);
            return new ResponseEntity<>(new ToFront("Book deleted", true), HttpStatus.OK);
        }catch(Exception e){
            throw new InternalError(e.getMessage());
        }
    }

    @Transactional//Me permite utilizar book1.get().setETC que modifica los valores directamente en la base de datos.
    public ResponseEntity<ToFront> putBook(Book newbook){
        //1) Cuando llaman al metodo PUT primero verifico que la id dada sea correcta al igual que el nuevo título no este
        // en usada otro libro.

        //2) Una vez terminado, cambio los valores del libro guardado en la base de datos por los brindados por el usuario.
        try{
            Long id = newbook.getId();
            String title = newbook.getTitle();
            String author = newbook.getAuthor();
            Integer price = newbook.getPrice();
            LocalDate write = newbook.getWrite();
            //1){
            Optional <Book> book = bookRepository.findById(id);
            System.out.println(newbook);
            if(book.isEmpty()){
                return new ResponseEntity<>(new ToFront("This id does not exist",false), HttpStatus.BAD_REQUEST);
            }

            Optional<Book> bookByTitle = bookRepository.findBookByTitle(title);

            if (bookByTitle.isPresent() && !Objects.equals(bookByTitle.get().getId(), id)) {
                return new ResponseEntity<>(new ToFront("This title already exists", false), HttpStatus.BAD_REQUEST);
            }
            //}
            //2){
            if (title != null){
                book.get().setTitle(title);
            }else{
                newbook.setTitle(book.get().getTitle());
            }
            if (author != null){
                book.get().setAuthor(author);
            }else{
                newbook.setAuthor(book.get().getAuthor());
            }
            if (price != null){
                book.get().setPrice(price);
            }else{
                newbook.setPrice(book.get().getPrice());
            }
            if (write != null){
                book.get().setWrite(write);
            }else{
                newbook.setWrite(book.get().getWrite());
            }
            //}
            List<Book> list = new ArrayList<>();
            list.add(newbook);
            return new ResponseEntity<>(new ToFront("Edited successfully", true, list), HttpStatus.CREATED);
        }catch(Exception e){
            throw new InternalError(e.getMessage());
        }
    }
}
