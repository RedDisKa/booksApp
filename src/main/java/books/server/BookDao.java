package books.server;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import books.HibernateUtils;
import books.domain.Autor;
import books.domain.Book;

public class BookDao {
	
	private static BookDao instance;

	public static synchronized BookDao getInstance() {
		if (instance == null) {
			instance = new BookDao();
		}
		return instance;
	}

	public Integer saveBook(String title, Integer autorId, String genre) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		Integer id = null;
		try {
			transaction = session.beginTransaction();
			Book book = new Book();
			book.setTitle(title);
			book.setAutorId(autorId);
			book.setGenre(genre);
			id = (Integer) session.save(book);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	@SuppressWarnings("unchecked")
	public List<Book> listBooks(Integer autorId) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Book> books = null;
		try {
			transaction = session.beginTransaction();
			books = session.createQuery("from Book as book where book.autorId =?").setInteger(0, autorId).list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return books;
	}

	public void deleteBook(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Book book = (Book) session.get(Book.class, id);
			session.delete(book);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}