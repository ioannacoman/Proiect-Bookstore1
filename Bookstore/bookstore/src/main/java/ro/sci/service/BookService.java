package ro.sci.service;
import ro.sci.domain.Book;
import java.util.List;

/**
 * Created by Tripura on 3/11/2018.
 */
public interface BookService {
     List<Book> getAll();

     void createBook(Book books);

     void removeBook(long idBook);

     void updateBook(Book book, long idBook);

     Book getByIDBook(long idBook);

}
