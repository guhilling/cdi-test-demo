package de.hilling.cdi.sampleapp.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

@Entity
@NamedQuery(name = "allBooks", query = "SELECT b FROM Book b")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long   id;
    private String title;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
