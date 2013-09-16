package books.client;

import shared.AutorDto;
import books.domain.Book;
import books.server.DBUtils;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class DeleteWindow extends Window {

	private static final long serialVersionUID = 6286647182495782990L;
	Integer id;
	String mode;
	MyVaadinApplication application;

	DeleteWindow(MyVaadinApplication application, Integer id, String string) {
		super();
		this.id = id;
		this.mode = string;
		this.application = application;
		init();
	}

	private void init() {
		setResizable(false);
		setScrollable(false);
		setClosable(false);
		setWidth("300px");
		setHeight("200px");
		setModal(true);
		Label label = new Label(
				"Вы уверены, что хотите удалить выбранный элемент?");
		addComponent(label);
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Button addButton = new Button("Удалить");
		addButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (mode.equals("book")) {
					DBUtils.getInstance().deleteBook(id);
					application.updateBooksTable((Integer) application.autors
							.getValue());
				} else if (mode.equals("autor")) {
					DBUtils.getInstance().deleteAutor(id);
					application.updateAutorsTable();
					application.rightLayout.setVisible(false);
				}
				DeleteWindow.this.setVisible(false);
			}
		});
		horizontalLayout.addComponent(addButton);
		Button cancelButton = new Button("Отмена");
		cancelButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				DeleteWindow.this.setVisible(false);
			}
		});
		horizontalLayout.addComponent(cancelButton);
		addComponent(horizontalLayout);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
}
