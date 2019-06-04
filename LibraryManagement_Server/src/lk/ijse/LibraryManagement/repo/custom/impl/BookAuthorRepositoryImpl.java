package lk.ijse.LibraryManagement.repo.custom.impl;

import lk.ijse.LibraryManagement.entity.Author;
import lk.ijse.LibraryManagement.entity.Book;
import lk.ijse.LibraryManagement.entity.BookAuthor;
import lk.ijse.LibraryManagement.repo.CrudRepositoryImpl;
import lk.ijse.LibraryManagement.repo.custom.BookAuthorRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookAuthorRepositoryImpl extends CrudRepositoryImpl<BookAuthor,String>implements BookAuthorRepository {
    private Session session;


    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
        super.setSession(session);
    }

    @Override
    public boolean updateAuthorForBook(Author author, int bid) throws Exception {
        Query query = session.createQuery("update BookAuthor set aid=:author where bid=:bid");
        query.setParameter("bid", bid);
        query.setParameter("aid", author);

        return query.executeUpdate() > 0;
    }

    @Override
    public boolean updateBookForAuthor(Book book, int aid) throws Exception {
        Query query = session.createQuery("update BookAuthor set bid=:bid where aid=:aid");
        query.setParameter("aid", aid);
        query.setParameter("bid", book);

        return query.executeUpdate() > 0;
    }

    @Override
    public Author getAuthorForBook(int bid) throws Exception {

        Query query = session.createQuery("select ba.aid From BookAuthor ba where ba.bid=:bid");
        query.setParameter("bid", bid);
        List<Author> author = query.list();
        if (author.isEmpty()) {
            return null;
        } else {
            return author.get(0);
        }
    }

    @Override
    public Book getBookForAuthor(int aid) throws Exception {

        Query query = session.createQuery("select ba.bid From BookAuthor ba where ba.aid=:aid");
        query.setParameter("aid", aid);
        List<Book> books = query.list();
        if (books.isEmpty()) {
            return null;
        } else {
            return books.get(0);
        }
    }
}
