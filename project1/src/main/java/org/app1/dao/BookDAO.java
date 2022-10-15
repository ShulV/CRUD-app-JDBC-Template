package org.app1.dao;

import org.app1.models.Book;
import org.app1.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //получить список всех книг
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * FROM Books", new BookRowMapper());
    }

    //получить список книг, взятых определенным человеком
    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("select p.id, p.name, p.patronymic, p.surname, p.patronymic from people as p " +
                "join books as b " +
                "on p.id = b.person_id " +
                "where b.id = ?;", new PersonRowMapper(), id).stream().findAny();
    }

    //добавить книгу
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO public.books(name, author, date) " +
                        "VALUES (?, ?, ?);",
                book.getName(),
                book.getAuthor(),
                book.getDate());
    }

    //получить книгу по id
    public Optional<Book> getBook(int id) {
        return jdbcTemplate.query("SELECT * FROM Books WHERE id=?;",
                new BookRowMapper(), new Object[]{id}).stream().findAny();
    }
}
