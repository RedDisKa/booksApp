package books;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import books.domain.Autor;
import books.domain.Book;
import books.domain.Country;

public class HibernateUtils {

	private static final SessionFactory sessionFactory;

	static {
		try {
			sessionFactory = new Configuration().configure()
					.addPackage("src.main.java.books")
					.addAnnotatedClass(Autor.class)
					.addAnnotatedClass(Book.class)
					.addAnnotatedClass(Country.class).buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}