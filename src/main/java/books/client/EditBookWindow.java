package books.client;


import books.server.DBUtils;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class EditBookWindow extends Window {

	private static final long serialVersionUID = 6286647182495782990L;

	TextField title;
	TextField genre;
	MyVaadinApplication myVaadinApplication;
	

	EditBookWindow(MyVaadinApplication myVaadinApplication) {
		super();
		this.myVaadinApplication = myVaadinApplication;
		init();
	}

	private void init() {
		setResizable(false);
		setScrollable(false);
		setClosable(false);
		setWidth("300px");
		setHeight("200px");
		setModal(true);
		FormLayout formLayout = new FormLayout();
		title = new TextField("Название книги");
		formLayout.addComponent(title);
		genre = new TextField("Жанр");
		formLayout.addComponent(genre);
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Button addButton = new Button("Сохранить");
		addButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				EditBookWindow.this.setVisible(false);
				DBUtils.getInstance().saveBook(title.getValue().toString(), myVaadinApplication.getSelectedAutor(), genre.getValue().toString());
				myVaadinApplication.updateBooksTable(myVaadinApplication.getSelectedAutor());
			}
		});
		horizontalLayout.addComponent(addButton);
		Button cancelButton = new Button("Отмена");
		cancelButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				EditBookWindow.this.setVisible(false);
			}
		});
		horizontalLayout.addComponent(cancelButton);
		formLayout.addComponent(horizontalLayout);
		addComponent(formLayout);
	}

	public void clear() {
		title.setValue("");
		genre.setValue("");
	}
}
