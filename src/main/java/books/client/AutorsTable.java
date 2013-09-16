package books.client;

import java.util.List;

import shared.AutorDto;
import books.domain.Autor;

import com.vaadin.ui.Table;

public class AutorsTable extends Table {

	private static final long serialVersionUID = -8603634299451448960L;

	public void addItem(AutorDto dto) {
		addItem(new Object[] { dto.getName(), dto.getCountry() }, dto.getId());
	}

	public void update(List<AutorDto> autors) {
		this.items.removeAllItems();
		for (AutorDto dto : autors) {
			addItem(new Object[] { dto.getName(), dto.getCountry() },
					dto.getId());
		}
	}
}
