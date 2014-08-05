package ch.catnip.catbreeder.view;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;
import ch.catnip.catbreeder.presenter.CatListPresenter;

import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@VaadinView(CatListViewImpl.PLACE)
public class CatListViewImpl extends VerticalLayout implements CatView {
	
	public static final String PLACE = "catlist";

	private static final transient Logger logger = LoggerFactory.getLogger(CatListViewImpl.class);

	@Autowired
	private CatListPresenter catListPresenter;

	@PostConstruct
	@Override
	public void postConstruct() {
		construct();
	}

	@Override
	public void initComponent() {
	}

	@Override
	public void addEventHandlers() {
	}

	@Override
	public void setLayout() {
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
	}
}
