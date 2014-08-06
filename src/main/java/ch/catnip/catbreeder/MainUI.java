package ch.catnip.catbreeder;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.DiscoveryNavigator;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
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
	
	Panel panel;

	@Override
	protected void init(VaadinRequest request) {
		setLayout();
	}
	
	private void setLayout() {
		setSizeFull();
		
		// Layout with menu on left and view area on right
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();

        // Have a menu on the left side of the screen
        Panel menu = new Panel("Menu");
        menu.setHeight("100%");
        menu.setWidth(null);
        
        VerticalLayout menuContent = new VerticalLayout();
        menuContent.addComponent(new Button("List cats"));
        menuContent.addComponent(new Button("Add cat"));
        
        menuContent.setWidth(null);
        menuContent.setMargin(true);
        menuContent.setSpacing(true);
        menu.setContent(menuContent);
        horizontalLayout.addComponent(menu);
        
        // A panel that contains a content area on right
        panel = new Panel("Content");
        panel.setSizeFull();
        horizontalLayout.addComponent(panel);
        horizontalLayout.setExpandRatio(panel, 1.0f);

		setContent(horizontalLayout);

		new DiscoveryNavigator(this, panel);
	}

	public void addEventListener(View view) {
		// eventBus.register(view);
	}

	public void removeEventListener(View view) {
		// eventBus.unregister(view);
	}
}
