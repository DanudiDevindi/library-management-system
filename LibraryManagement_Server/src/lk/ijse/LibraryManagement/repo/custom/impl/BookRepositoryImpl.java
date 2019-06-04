package lk.ijse.LibraryManagement.repo.custom.impl;

import lk.ijse.LibraryManagement.entity.Book;
import lk.ijse.LibraryManagement.repo.CrudRepositoryImpl;
import lk.ijse.LibraryManagement.repo.custom.BookRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class BookRepositoryImpl  extends CrudRepositoryImpl<Book,String>implements BookRepository {

    private Session session;

    @Override
    public void setSession(Session session) throws Exception {
        this.session = session;
        super.setSession(session);
    }

    @Override
    public Book searchBook(int bid) throws Exception {
        Query query = session.createQuery("From Book where machiningNo ='" + bid + "'");
        List<Book> books = query.list();
        System.out.println("Yudi3 " + books);
        if (books.isEmpty()) {
            return null;
        } else {
            return books.get(0);
        }
    }

    @Override
    public Book searchBook(String bookName) throws Exception {
        Query query = session.createQuery("From Book where bookName=:name");
        query.setParameter("name", bookName);
        List<Book> books = query.list();
        if (books.isEmpty()) {
            return null;
        } else {
            return books.get(0);
        }
    }

    @Override
    public boolean removeBook(int bid) throws Exception {
        Query query = session.createQuery("delete from Book where bid=:bid");
        query.setParameter("bid", bid);
        return query.executeUpdate() > 0;
    }

    @Override
    public List<Book> getDetails(String name) throws Exception {
        return session.createQuery("from Book where bookName='" + name + "'", Book.class).list();
    }
}
