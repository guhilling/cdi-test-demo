package de.hilling.cdi.sampleapp.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

import de.hilling.cdi.sampleapp.jpa.Book;

@Transactional
public class BookService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Book> getAllBooks() {
        return entityManager.createNamedQuery("allBooks", Book.class).getResultList();
    }

    public Book getBookById(long id) {
        return entityManager.find(Book.class, id);
    }

    public long saveBook(Book book) {
        entityManager.persist(book);
        return book.getId();
    }
}
