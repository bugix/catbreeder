package ch.catnip.catbreeder;

import java.util.Locale;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.DiscoveryNavigator;
import ch.catnip.catbreeder.converter.BreederConverterFactory;
import ch.catnip.catbreeder.view.NavigationViewImpl;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@Theme("dawn")
@PreserveOnRefresh
@Title("CatBreeder")
public class MainUI extends UI {

	private Navigator navigator;

	@Override
	protected void init(VaadinRequest request) {
		
		// TODO Is this the right place
		VaadinSession.getCurrent().setLocale(Locale.GERMAN);
		VaadinSession.getCurrent().setConverterFactory(new BreederConverterFactory());

		setLayout();
	}

	// TODO Move this to its own Class
	private void setLayout() {
		setSizeFull();

		// Layout with menu on left and view area on right
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSizeFull();
		setContent(horizontalLayout);
		
		// A panel that contains a content area on right
		Panel content = new Panel("Content");
		content.setSizeFull();
		navigator = new DiscoveryNavigator(this, content);
		setNavigator(navigator);
		
		Panel navigationView = new NavigationViewImpl();
		horizontalLayout.addComponent(navigationView);
		horizontalLayout.addComponent(content);
		horizontalLayout.setExpandRatio(content, 1.0f);
	}

	// TODO Add EventBus
	public void addEventListener(View view) {
		// eventBus.register(view);
	}

	public void removeEventListener(View view) {
		// eventBus.unregister(view);
	}
}
