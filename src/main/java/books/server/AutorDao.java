package books.server;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import books.HibernateUtils;
import books.domain.Autor;

public class AutorDao {

	private static AutorDao instance;

	public static synchronized AutorDao getInstance() {
		if (instance == null) {
			instance = new AutorDao();
		}
		return instance;
	}

	public Integer saveAutor(String cityName, Integer countryId) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		Integer id = null;
		try {
			transaction = session.beginTransaction();
			Autor autor = new Autor();
			autor.setName(cityName);
			autor.setCountryId(countryId);
			id = (Integer) session.save(autor);
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
	public List<Autor> listAutors() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Autor> autors = null;
		try {
			transaction = session.beginTransaction();
			autors = session.createQuery("from Autor").list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return autors;
	}

	public void updateAutor(Integer id, String autorName) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Autor autor = (Autor) session.get(Autor.class, id);
			autor.setName(autorName);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteAutor(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Autor autor = (Autor) session.get(Autor.class, id);
			session.delete(autor);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}