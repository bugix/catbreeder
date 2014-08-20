package ch.catnip.catbreeder.view;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class NavigationViewImpl extends Panel implements ViewChangeListener {

	private static final transient Logger logger = LoggerFactory.getLogger(NavigationViewImpl.class);
	
	private Map<String, Button> menuButtons = new LinkedHashMap<String, Button>();
	
	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		
		return true;
	}

	@Override
	public void afterViewChange(ViewChangeEvent event) {
		for (Button menuButton : menuButtons.values()) {
			menuButton.setEnabled(true);
		}
		
		if (menuButtons.containsKey(event.getViewName())) {
			menuButtons.get(event.getViewName()).setEnabled(false);
		}
	}

	public NavigationViewImpl() {
		setCaption("Menu");
		setHeight("100%");
		setWidth(null);

		VerticalLayout menuContent = new VerticalLayout();
		
		menuButtons.put(CatListView.PLACE, new Button("List cats"));
		menuButtons.put(CatView.PLACE, new Button("Add cat"));

		for (Button menuButton : menuButtons.values()) {
			menuContent.addComponent(menuButton);
		}

		menuContent.setWidth(null);
		menuContent.setMargin(true);
		menuContent.setSpacing(true);
		
		setContent(menuContent);
	}
	
	@Override
	public void attach() {
		super.attach();
		
		Navigator navigator = getUI().getNavigator();
		
		for (Entry<String, Button> entry : menuButtons.entrySet()) {
			entry.getValue().addClickListener(e -> {
				navigator.navigateTo(entry.getKey());
			});	
		}
		
		navigator.addViewChangeListener(this);
	}
	
	@Override
	public void detach() {
		Navigator navigator = getUI().getNavigator();
		
		navigator.removeViewChangeListener(this);
		super.detach();
	}
}
