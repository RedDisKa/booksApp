package books.client;

import shared.AutorDto;
import books.domain.Autor;
import books.server.CountryDao;

public class ModelUtils {
	static CountryDao countryDao = new CountryDao();

	public static AutorDto asDto(Autor autor) {
		String country = countryDao.getCountry(autor.getCountryId()).getCountry();
		AutorDto dto = new AutorDto(autor.getId(), autor.getName(), country);
		return dto;
	}
}
