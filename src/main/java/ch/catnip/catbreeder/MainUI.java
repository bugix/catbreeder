package ch.catnip.catbreeder;

import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.DiscoveryNavigator;
import ch.catnip.catbreeder.converter.BreederConverterFactory;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.Navigator.ComponentContainerViewDisplay;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@Theme("dawn")
@PreserveOnRefresh
@Title("CatBreeder")
public class MainUI extends UI {

	Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		
		// TODO Is this the right place
		VaadinSession.getCurrent().setLocale(Locale.GERMAN);
		VaadinSession.getCurrent().setConverterFactory(new BreederConverterFactory());

		setLayout();
	}

	// TODO Move this to its own Class
	private void setLayout() {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		layout.setSpacing(true);
		setContent(layout);
		ComponentContainerViewDisplay viewDisplay = new ComponentContainerViewDisplay(layout);
		
		navigator = new DiscoveryNavigator(UI.getCurrent(), viewDisplay);
		
		
		
		/*
		setSizeFull();

		// Layout with menu on left and view area on right
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();

		// Have a menu on the left side of the screen
		Panel menu = new Panel("Menu");
		menu.setHeight("100%");
		menu.setWidth(null);

		VerticalLayout menuContent = new VerticalLayout();

		Button listCatsButton = new Button("List cats");
		Button addCatButton = new Button("Add cat");

		menuContent.addComponent(listCatsButton);
		menuContent.addComponent(addCatButton);

		menuContent.setWidth(null);
		menuContent.setMargin(true);
		menuContent.setSpacing(true);
		menu.setContent(menuContent);
		horizontalLayout.addComponent(menu);

		// A panel that contains a content area on right
		Panel content = new Panel("Content");
		content.setSizeFull();
		horizontalLayout.addComponent(content);
		horizontalLayout.setExpandRatio(content, 1.0f);

		setContent(horizontalLayout);

		navigator = new DiscoveryNavigator(this, content);

		listCatsButton.addClickListener(e -> {
			navigator.navigateTo("");
		});

		addCatButton.addClickListener(e -> {
			navigator.navigateTo("cat");
		});
		*/
	}

	// TODO Add EventBus
	public void addEventListener(View view) {
		// eventBus.register(view);
	}

	public void removeEventListener(View view) {
		// eventBus.unregister(view);
	}
}
