package books.client;

import java.util.List;

import books.domain.Country;
import books.server.DBUtils;

import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Select;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;

public class EditAutorWindow extends Window {

	private static final long serialVersionUID = 6286647182495782990L;

	TextField autorName;
	Select countrySelect;
	MyVaadinApplication myVaadinApplication;

	EditAutorWindow(final MyVaadinApplication myVaadinApplication) {
		super();
		this.myVaadinApplication = myVaadinApplication;
		setResizable(false);
		setScrollable(false);
		setClosable(false);
		setWidth("300px");
		setHeight("200px");
		setModal(true);
		FormLayout formLayout = new FormLayout();
		autorName = new TextField("Имя автора");
		formLayout.addComponent(autorName);
		countrySelect = new Select("Страна");
		countrySelect.setNullSelectionAllowed(false);
		setCountrySelectData();
		formLayout.addComponent(countrySelect);
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		Button addButton = new Button("Сохранить");
		addButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				EditAutorWindow.this.setVisible(false);
				DBUtils.getInstance().saveAutor(autorName.getValue().toString(),
						((Country) countrySelect.getValue()).getId());
				myVaadinApplication.updateAutorsTable();
			}
		});
		horizontalLayout.addComponent(addButton);
		Button cancelButton = new Button("Отмена");
		cancelButton.addListener(new ClickListener() {
			public void buttonClick(ClickEvent event) {
				EditAutorWindow.this.setVisible(false);
			}
		});
		horizontalLayout.addComponent(cancelButton);
		formLayout.addComponent(horizontalLayout);
		addComponent(formLayout);
	}

	private void setCountrySelectData() {
		List<Country> countries = DBUtils.getInstance().getCountries();
		if (countries != null) {
			for (Country country : countries) {
				countrySelect.addItem(country);
			}
		}
	}

	public void clear() {
		autorName.setValue("");
		countrySelect.select(countrySelect.getItemIds().toArray()[0]);
	}
}
