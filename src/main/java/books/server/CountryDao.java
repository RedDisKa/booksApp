package books.server;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import books.HibernateUtils;
import books.domain.Autor;
import books.domain.Country;

public class CountryDao {
	
	private static CountryDao instance;

	public static synchronized CountryDao getInstance() {
		if (instance == null) {
			instance = new CountryDao();
		}
		return instance;
	}

	public Integer saveCountry(String country) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		Integer id = null;
		try {
			transaction = session.beginTransaction();
			Country country2 = new Country();
			country2.setCountry(country);
			id = (Integer) session.save(country2);
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
	public List<Country> listCountries() {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		List<Country> countries = null;
		try {
			transaction = session.beginTransaction();
			countries = session.createQuery("from Country").list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return countries;
	}

	public void updateCountry(Integer id, String countryName) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Country country = (Country) session.get(Autor.class, id);
			country.setCountry(countryName);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public void deleteCountry(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Country country = (Country) session.get(Country.class, id);
			session.delete(country);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public Country getCountry(Integer id) {
		Session session = HibernateUtils.getSessionFactory().openSession();
		Transaction transaction = null;
		Country country = null;
		try {
			transaction = session.beginTransaction();
			country = (Country) session.get(Country.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return country;
	}
}