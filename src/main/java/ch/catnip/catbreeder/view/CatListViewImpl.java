package ch.catnip.catbreeder.view;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;
import ch.catnip.catbreeder.model.Cat;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@VaadinView(CatListViewImpl.PLACE)
public class CatListViewImpl extends VerticalLayout implements CatListView {
	
	public static final String PLACE = "";

	private static final transient Logger logger = LoggerFactory.getLogger(CatListViewImpl.class);
	
	private Table table;
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	@Qualifier("catListPresenter")
	private CatListViewListener catListViewListener;
	
	@PostConstruct
	@Override
	public void postConstruct() {
		construct();
		
		catListViewListener.setView(this);
	}

	// TODO How to handle properties unknown to Vaadin? (eg. LocalDate, Enums like Breed)
	@Override
	public void initComponent() {
		table = new Table("My sweet Pussycats");
		table.setSizeFull();
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
	
	@Override
	public void enter(ViewChangeEvent event) {
		catListViewListener.enter(event.getParameters()); 
	}

	@Override
	public void renderCatList(List<Cat> catList) {
		int i = 0;
		for (Cat cat : catList) {
			table.addItem(new Object[] {cat.getName()}, i++);
		}
	}
}
