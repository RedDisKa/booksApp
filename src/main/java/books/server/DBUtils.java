package books.server;

import java.util.List;

import books.domain.Autor;
import books.domain.Book;
import books.domain.Country;

public class DBUtils {

	private static DBUtils instance;
	static AutorDao autorDao;
	static BookDao bookDao;
	static CountryDao countryDao;

	public static synchronized DBUtils getInstance() {
		if (instance == null) {
			instance = new DBUtils();
			autorDao = AutorDao.getInstance();
			bookDao = BookDao.getInstance();
			countryDao = CountryDao.getInstance();
		}
		return instance;
	}

	public void deleteAutor(Integer id) {
		List<Book> books = getBooks(id);
		for (Book book : books) {
			deleteBook(book.getId());
		}
		autorDao.deleteAutor(id);
	}

	public void deleteBook(Integer id) {
		bookDao.deleteBook(id);
	}

	public Integer saveAutor(String name, Integer countryId) {
		return autorDao.saveAutor(name, countryId);
	}

	public Integer saveBook(String title, Integer autorId, String genre) {
		return bookDao.saveBook(title, autorId, genre);
	}

	public Integer saveCountry(String country) {
		return countryDao.saveCountry(country);
	}

	public List<Autor> getAutors() {
		return autorDao.listAutors();
	}

	public List<Book> getBooks(Integer autorId) {
		return bookDao.listBooks(autorId);
	}

	public List<Country> getCountries() {
		return countryDao.listCountries();
	}

}
