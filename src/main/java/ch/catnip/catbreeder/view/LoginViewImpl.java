package ch.catnip.catbreeder.view;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;
import ch.catnip.catbreeder.MainUI;
import ch.catnip.catbreeder.model.Breeder;
import ch.catnip.catbreeder.presenter.LoginPresenter;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@VaadinView(LoginViewImpl.PLACE)
public class LoginViewImpl extends VerticalLayout implements LoginView {

	public static final String PLACE = "login";

	private static final transient Logger logger = LoggerFactory.getLogger(LoginViewImpl.class);

	@Autowired
	private LoginPresenter loginPresenter;
	
	private TextField loginField;
	
	private TextField passwordField;
	
	private Button resetButton;
	
	private Button loginButton;
	
	private HorizontalLayout buttonsLayout;
	
	private BeanFieldGroup<Breeder> fieldGroup;
	
	private Breeder breeder;

	@PostConstruct
	@Override
	public void postConstruct() {
		construct();
	}

	@Override
	public void initComponent() {
		loginField = new TextField("Login");
		loginField.setRequired(true);
		loginField.setRequiredError("Please enter a Login!");
		loginField.setWidth("12em");
		loginField.addValidator(new StringLengthValidator("Login must have 3-10 characters", 3, 10, false));
		loginField.setNullRepresentation("");
		loginField.setImmediate(true);
		
		passwordField = new TextField("Password");
		passwordField.setRequired(true);
		passwordField.setRequiredError("Please enter a Password!");
		passwordField.setWidth("12em");
		passwordField.addValidator(new StringLengthValidator("Password must have 3-10 characters", 3, 10, false));
		passwordField.setNullRepresentation("");
		passwordField.setImmediate(true);

		breeder = new Breeder();
		
		fieldGroup = new BeanFieldGroup<Breeder>(Breeder.class);
        fieldGroup.setItemDataSource(new BeanItem<Breeder>(breeder));
        fieldGroup.bind(loginField, "login");
        fieldGroup.bind(passwordField, "password");
        
		resetButton = new Button("Reset");
		
		loginButton = new Button("Login");
		
		buttonsLayout = new HorizontalLayout();
		buttonsLayout.setSpacing(true);
		
		buttonsLayout.addComponent(resetButton);
		buttonsLayout.setComponentAlignment(resetButton, Alignment.MIDDLE_LEFT);
		
		buttonsLayout.addComponent(loginButton);
	}

	@Override
	public void addEventHandlers() {
		
		resetButton.addClickListener(e -> {
			fieldGroup.discard();
		});
		
		loginButton.addClickListener(e -> {
			try
            {
                fieldGroup.commit();
                //Notification.show("OK");
            }
            catch (Exception exception)
            {
                //Notification.show("Error: " + e.getMessage(), Notification.Type.WARNING_MESSAGE);
            }
		});
	}
	
	@Override
	public void setLayout() {
		addComponent(loginField);
        addComponent(passwordField);
        addComponent(buttonsLayout);
	}

	@Override
	public void attach() {
		super.attach();
		((MainUI) UI.getCurrent()).addEventListener(this);
	}

	@Override
	public void detach() {
		((MainUI) UI.getCurrent()).removeEventListener(this);
		super.detach();
	}

	@Override
	public void enter(ViewChangeEvent event) {
		logger.info("In enter method");
	}
}
