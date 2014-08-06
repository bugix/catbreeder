package ch.catnip.catbreeder.view;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;
import ch.catnip.catbreeder.model.Breed;
import ch.catnip.catbreeder.model.Cat;
import ch.catnip.catbreeder.presenter.CatListPresenter;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@VaadinView(CatListViewImpl.PLACE)
public class CatListViewImpl extends VerticalLayout implements CatView {
	
	public static final String PLACE = "";

	private static final transient Logger logger = LoggerFactory.getLogger(CatListViewImpl.class);

	@Autowired
	private CatListPresenter catListPresenter;
	
	private Table table;

	@PostConstruct
	@Override
	public void postConstruct() {
		construct();
	}

	// TODO How to handle properties unknown to Vaadin? (eg. LocalDate, Enums like Breed)
	@Override
	public void initComponent() {
		table = new Table("My sweet Pussycats");
		table.addContainerProperty("name", String.class, null);
		//table.addContainerProperty("breed", Breed.class, null);
		//table.addContainerProperty("birthDay", Date.class, null);
	}

	@Override
	public void addEventHandlers() {
	}

	@Override
	public void setLayout() {
		addComponent(table);
		setMargin(true);
		setSpacing(true);
	}

	@Override
	public void attach() {
		super.attach();
	}

	@Override
	public void detach() {
		super.detach();
	}

	// TODO This is ugly I think
	@Override
	public void enter(ViewChangeEvent event) {
		
		Integer count = 1;
		
		for (Cat cat : catListPresenter.getCatList())
		{
			logger.debug("Add " + cat.getName() + " to table");
			table.addItem(new Object[] {cat.getName()}, count++);
		}
	}
}
