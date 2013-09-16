package books.client;

import java.util.List;

import books.domain.Book;

import com.vaadin.ui.Table;

public class BooksTable extends Table {

	private static final long serialVersionUID = 5373636795572405024L;

	public void addItem(Book dto) {
		addItem(new Object[] { dto.getTitle(), dto.getGenre() }, dto.getId());
	}

	public void update(List<Book> books) {
		this.items.removeAllItems();
		for (Book dto : books) {
			addItem(new Object[] { dto.getTitle(), dto.getGenre() },
					dto.getId());
		}
	}
}
