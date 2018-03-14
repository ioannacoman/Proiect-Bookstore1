package ro.sci.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.sci.domain.Book;
import ro.sci.service.BookService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by Tripura on 3/11/2018.
 */
public class BookController {
   @Autowired
    private BookService bookService;

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks (Model model, HttpServletRequest request){
        List<Book> books = bookService.getAll();
        model.addAttribute("books",books);
        return "listBooks";
    }

    @RequestMapping(value = "books", method = RequestMethod.POST)
    public String createBooks(Book bookRequest, Model model){
        Book book = getBook(bookRequest);
        bookService.createBook(book);
        return "redirect:/books";
    }

    @RequestMapping(value = "/books/{idBook}", method = RequestMethod.GET)
    public String getBook(@PathVariable long idBook, Model model) {
        Book book = bookService.getByIDBook(idBook);
        model.addAttribute("updateBookRequest", getBook(book));
        model.addAttribute("book_id", idBook);
        return "updateBook";
    }


    @RequestMapping(value = "/clients/update/{idBook}", method = RequestMethod.POST)
    public String updateBook(Book bookRequest, @PathVariable long idBook) {
        Book book = getBook(bookRequest);

        bookService.updateBook(book, idBook);

        return "redirect:/books";
    }

    @RequestMapping(value = "/clients/delete/{id}", method = RequestMethod.POST)
    public String removeBook(@PathVariable long idBook, Model model) {
        bookService.removeBook(idBook);

        return "redirect:/books";
    }

    private Book getBook(Book bookRequest) {
         Book book;
         book= new Book();
         book.setTitle(bookRequest.getTitle());
         book.setAuthor(bookRequest.getAuthor());
         book.setPublisher(bookRequest.getPublisher());
         book.setPublicationDate(bookRequest.getPublicationDate());
         book.setLanguage(bookRequest.getLanguage());
         book.setCategory(bookRequest.getCategory());
         book.setNumberOfPages(bookRequest.getNumberOfPages());
         book.setFormat(bookRequest.getFormat());
         book.setIsbn(bookRequest.getIsbn());
         book.setListPrice(bookRequest.getListPrice());
         book.setOurPrice(bookRequest.getOurPrice());
       return book;
    }

}
