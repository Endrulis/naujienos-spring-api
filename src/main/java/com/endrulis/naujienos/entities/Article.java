package com.endrulis.naujienos.entities;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "First name is mandatory")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String name;

    @Column(name = "text")
    @NotNull(message = "Text cannot be null")
    @NotBlank(message = "Text is mandatory")
    private String text;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "author_id")
    @NotNull(message = "Author cannot be null")
    @NotBlank(message = "Author is mandatory")
    @Size(min = 2, max = 50, message = "Author must be between 2 and 50 characters")
    private Author author;

    public Article() {
        this.author = new Author();
    }

    public Article( String name, String text ) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (text == null || text.trim().isEmpty())
            throw new IllegalArgumentException("Text cannot be null or empty");
        this.name = name;
        this.text = text;
    }

    public Article( String name, String text, Author author ) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (text == null || text.trim().isEmpty())
            throw new IllegalArgumentException("Text cannot be null or empty");
        if (author == null)
            throw new IllegalArgumentException("Author cannot be null");
        this.name = name;
        this.text = text;
        this.author = author;
    }

    public Article( Long id, String name, String text, Author author ) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Id must be greater than zero");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (text == null || text.trim().isEmpty())
            throw new IllegalArgumentException("Text cannot be null or empty");
        if (author == null)
            throw new IllegalArgumentException("Author cannot be null");
        this.id = id;
        this.name = name;
        this.text = text;
        this.author = author;
    }

    public Article( Long id, String name, String text ) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Id must be greater than zero");
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (text == null || text.trim().isEmpty())
            throw new IllegalArgumentException("Text cannot be null or empty");
        this.id = id;
        this.name = name;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText( String text ) {
        this.text = text;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor( Author author ) {
        this.author = author;
    }
}
