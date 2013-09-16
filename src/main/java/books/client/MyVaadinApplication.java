package books.client;

import java.util.ArrayList;
import java.util.List;

import shared.AutorDto;
import books.domain.Autor;
import books.domain.Book;
import books.domain.Country;
import books.server.DBUtils;

import com.vaadin.Application;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
public class MyVaadinApplication extends Application {
	private Window window;
	AutorsTable autors;
	BooksTable books;
	EditAutorWindow editAutorWindow;
	EditBookWindow editBookWindow;
	DeleteWindow deleteWindow;
	VerticalLayout rightLayout;
	Button deleteAutorButton;
	Button deleteBookButton;

	@Override
	public void init() {
		window = new Window("BooksApp");
		setMainWindow(window);
		List<Country> countries = DBUtils.getInstance().getCountries();
		if (countries == null || countries.size() == 0) {
			DBUtils.getInstance().saveCountry("КНР");
			DBUtils.getInstance().saveCountry("Индия");
			DBUtils.getInstance().saveCountry("США");
			DBUtils.getInstance().saveCountry("Индонезия");
			DBUtils.getInstance().saveCountry("Россия");
			DBUtils.getInstance().saveCountry("Германия");
			DBUtils.getInstance().saveCountry("Испания");
			DBUtils.getInstance().saveCountry("Украина");
			DBUtils.getInstance().saveCountry("Италия");
			DBUtils.getInstance().saveCountry("Австрия");
			DBUtils.getInstance().saveCountry("Швейцария");
			DBUtils.getInstance().saveCountry("Канада");
			DBUtils.getInstance().saveCountry("Болгария");
			DBUtils.getInstance().saveCountry("Венгрия");
			DBUtils.getInstance().saveCountry("Чехия");
		}
		initPage();
	}

	private void initAutorsTable() {
		autors.addContainerProperty("name", String.class, null);
		autors.addContainerProperty("country", String.class, null);
		autors.setSizeFull();
		autors.addListener(new ItemClickListener() {
			public void itemClick(ItemClickEvent event) {
				rightLayout.setVisible(true);
				updateBooksTable((Integer) event.getItemId());
				deleteAutorButton.setEnabled(true);
			}
		});
		autors.setSelectable(true);
	}

	private void initBooksTable() {
		books.addContainerProperty("Title", String.class, null);
		books.addContainerProperty("Genre", String.class, null);
		books.setSelectable(true);
		books.addListener(new ItemClickListener() {
			public void itemClick(ItemClickEvent event) {
				deleteBookButton.setEnabled(true);
			}
		});
		books.setSizeFull();
	}

	private void initPage() {
		window.setSizeFull();
		((VerticalLayout) window.getContent()).setHeight("100%");

		HorizontalSplitPanel splitPanel = new HorizontalSplitPanel();
		splitPanel.setHeight("100%");
		window.addComponent(splitPanel);

		VerticalLayout leftLayout = new VerticalLayout();
		leftLayout.setHeight("100%");
		rightLayout = new VerticalLayout();
		rightLayout.setHeight("100%");
		splitPanel.addComponent(leftLayout);
		splitPanel.addComponent(rightLayout);

		autors = new AutorsTable();
		initAutorsTable();
		leftLayout.addComponent(autors);
		updateAutorsTable();
		HorizontalLayout buttons = new HorizontalLayout();
		Button addAutorButton = new Button("Добавить");
		addAutorButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				setAutorEditWindow();
			}
		});
		buttons.addComponent(addAutorButton);
		deleteAutorButton = new Button("Удалить");
		deleteAutorButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				setDeleteWindow((Integer) autors.getValue(), "autor");
			}
		});
		deleteAutorButton.setEnabled(false);
		buttons.addComponent(deleteAutorButton);
		leftLayout.addComponent(buttons);
		leftLayout.setSizeFull();

		books = new BooksTable();
		initBooksTable();
		rightLayout.addComponent(books);
		HorizontalLayout layout = new HorizontalLayout();
		Button addBookButton = new Button("Добавить");
		addBookButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				setBookEditWindow((Integer) autors.getValue());
			}
		});
		layout.addComponent(addBookButton);
		deleteBookButton = new Button("Удалить");
		deleteBookButton.setEnabled(false);
		deleteBookButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				setDeleteWindow((Integer) books.getValue(), "book");
			}
		});
		layout.addComponent(deleteBookButton);
		rightLayout.addComponent(layout);
		rightLayout.setSizeFull();
		rightLayout.setVisible(false);
	}

	private void setAutorEditWindow() {
		if (editAutorWindow == null) {
			editAutorWindow = new EditAutorWindow(this);
			editAutorWindow.clear();
			window.addWindow(editAutorWindow);
		} else {
			editAutorWindow.setVisible(true);
			editAutorWindow.clear();
		}
	}

	private void setBookEditWindow(Integer autorId) {
		if (editBookWindow == null) {
			editBookWindow = new EditBookWindow(this);
			window.addWindow(editBookWindow);
		} else {
			editBookWindow.setVisible(true);
			editBookWindow.clear();
		}
	}

	private void setDeleteWindow(Integer id, String string) {
		if (deleteWindow == null) {
			deleteWindow = new DeleteWindow(this, id, string);
			window.addWindow(deleteWindow);
		} else {
			deleteWindow.setVisible(true);
			deleteWindow.setId(id);
			deleteWindow.setMode(string);
		}
	}

	public void updateAutorsTable() {
		List<AutorDto> autorDtos = new ArrayList<AutorDto>();
		List<Autor> autorsList = DBUtils.getInstance().getAutors();
		for (Autor autor : autorsList) {
			autorDtos.add(ModelUtils.asDto(autor));
		}
		autors.update(autorDtos);
	}

	public void updateBooksTable(Integer autorId) {
		if (autorId != null) {
			List<Book> booksList = DBUtils.getInstance().getBooks(autorId);
			books.update(booksList);
		} else {
			books.update(new ArrayList<Book>());
		}
	}

	public Integer getSelectedAutor() {
		return (Integer) autors.getValue();
	}
}
