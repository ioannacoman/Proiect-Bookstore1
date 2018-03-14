package ro.sci.service;

import org.springframework.stereotype.Service;
import ro.sci.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    public List<Book> books = new ArrayList<>();

    @Override
    public List<Book> getAll() {
        return books;
    }

    @Override
    public void createBook(Book book) {
        book.setIdBook(System.currentTimeMillis());
        books.add(book);
    }

    @Override
    public void removeBook(long idBook) {
        books = books.stream().filter(c -> c.getIdBook() != idBook).collect(Collectors.toList());
    }

    @Override
    public void updateBook(Book book, long idBook) {
        removeBook(idBook);
        book.setIdBook(idBook);
        books.add(book);
    }

    @Override
    public Book getByIDBook(long idBook) {
        //TODO check that the id exists
        return books.stream().filter(c -> c.getIdBook() == idBook).collect(Collectors.toList()).get(0);
    }

}