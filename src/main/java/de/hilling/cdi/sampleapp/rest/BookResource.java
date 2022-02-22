package de.hilling.cdi.sampleapp.rest;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import de.hilling.cdi.sampleapp.jpa.Book;
import de.hilling.cdi.sampleapp.service.BookService;

/**
 * REST API for {@link Book}s.
 */
@Path("/books")
@RequestScoped
@Transactional
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    private BookService bookService;

    /**
     * Get all books.
     * @return list of books in JSON format.
     */
    @Path("/")
    @GET
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    /**
     * Load book by id.
     * @param id primary key.
     * @return requested book or 404.
     */
    @Path("/byId/{id}")
    @GET
    public Book getBookById(@PathParam("id") Long id) {
        return bookService.getBookById(id);
    }

    /**
     * Create new book
     * @param book new book.
     * @return primary key of newly created data set.
     */
    @Path("/")
    @POST
    public long addBook(Book book) {
        return bookService.saveBook(book);
    }


}
