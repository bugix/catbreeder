package ch.catnip.catbreeder.view;

import java.util.EnumSet;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import ru.xpoft.vaadin.VaadinView;
import ch.catnip.catbreeder.model.Breed;
import ch.catnip.catbreeder.model.Cat;
import ch.catnip.catbreeder.presenter.CatPresenter;

import com.vaadin.data.util.BeanContainer;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@VaadinView(CatViewImpl.PLACE)
public class CatViewImpl extends VerticalLayout implements CatView, View, ClickListener {
	
	public static final String PLACE = "cat";

	private static final transient Logger logger = LoggerFactory.getLogger(CatViewImpl.class);
	
	private FormLayout form;
	
	private TextField nameField;
	
	private ListSelect breedSelect;
	
	private BeanContainer<Integer, Breed> catBreedContainer;
	
	private Button saveButton;

	@Autowired
	@Qualifier("catPresenter")
	private CatViewListener catViewListener;

	@Autowired
	private CatPresenter catPresenter;

	@PostConstruct
	@Override
	public void postConstruct() {
		construct();
	}

	// TODO https://vaadin.com/book/vaadin7/-/page/datamodel.itembinding.html
	@Override
	public void initComponent() {
		
		form = new FormLayout();
		
		nameField = new TextField("Name");
		nameField.setRequired(true);
		nameField.setRequiredError("Please enter a Name!");
		nameField.setWidth("12em");
		nameField.addValidator(new StringLengthValidator("Name must have 3-10 characters", 3, 10, false));
		nameField.setNullRepresentation("");
		nameField.setImmediate(true);
		
		form.addComponent(nameField);
		
		catBreedContainer = new BeanContainer<>(Breed.class);
		catBreedContainer.setBeanIdProperty("id");
		catBreedContainer.addAll(EnumSet.allOf(Breed.class));
		
		breedSelect = new ListSelect("Breed", catBreedContainer);
		breedSelect.setItemCaptionPropertyId("caption");
		breedSelect.setRequired(true);
		breedSelect.setNullSelectionAllowed(false);
		breedSelect.setWidth("12em");
		breedSelect.setImmediate(true);
		breedSelect.setRows(5);
		
		form.addComponent(breedSelect);
		
		saveButton = new Button("Save", this);
		
		form.addComponent(saveButton);
	}

	@Override
	public void addEventHandlers() {
	}

	@Override
	public void setLayout() {
		addComponent(nameField);
		addComponent(breedSelect);
		addComponent(saveButton);
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
	}

	@Override
	public void buttonClick(ClickEvent event) {
		
		Cat cat = new Cat();
		cat.setName(nameField.getValue());
		
		catViewListener.saveCat(cat);
	}
}
