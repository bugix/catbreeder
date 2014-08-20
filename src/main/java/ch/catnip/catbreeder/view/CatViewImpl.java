package ch.catnip.catbreeder.view;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import ch.catnip.catbreeder.ui.EditableImage;
import ch.catnip.catbreeder.ui.ImageData;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.util.BeanContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@VaadinView(CatView.PLACE)
public class CatViewImpl extends VerticalLayout implements CatView, View, ClickListener {

	private static final transient Logger logger = LoggerFactory.getLogger(CatViewImpl.class);

	private FormLayout formLayout;

	private ListSelect breedSelect;

	private BeanContainer<Integer, Breed> catBreedContainer;

	private BeanFieldGroup<Cat> catBinder;

	private Button saveButton;

	private Cat catBean;

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

		catBinder = new BeanFieldGroup<Cat>(Cat.class);

		catBean = new Cat();

		catBinder.setItemDataSource(catBean);

		formLayout = new FormLayout();

		catBreedContainer = new BeanContainer<>(Breed.class);
		catBreedContainer.setBeanIdProperty("id");
		catBreedContainer.addAll(EnumSet.allOf(Breed.class));

		breedSelect = new ListSelect("Breed", catBreedContainer);
		breedSelect.setItemCaptionPropertyId("caption");
		breedSelect.setRequired(true);
		breedSelect.setNullSelectionAllowed(false);
		// breedSelect.setWidth("12em");
		// breedSelect.setImmediate(true);
		breedSelect.setRows(3);

		TextField nameField = (TextField) catBinder.buildAndBind("Name", "name");
		nameField.setNullRepresentation("");

		formLayout.addComponent(nameField);

		catBinder.bind(breedSelect, "breed");

		formLayout.addComponent(breedSelect);

		formLayout.addComponent(catBinder.buildAndBind("Birthday", "birthDay"));

		// Load an initial image
        File baseDir = VaadinService.getCurrent().getBaseDirectory();
        Path initialpath = Paths.get(baseDir.getAbsolutePath() + "/img/no.jpg");
        ImageData initialimage = null;
        try {
            initialimage = new ImageData(initialpath.toString(), "image/jpeg", Files.readAllBytes(initialpath));
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		EditableImage edditableImage = new EditableImage("Image", initialimage);
		
		formLayout.addComponent(edditableImage);
		
		catBinder.bind(edditableImage, "picture");

		saveButton = new Button("Save", this);

		formLayout.addComponent(saveButton);
	}

	@Override
	public void addEventHandlers() {
	}

	@Override
	public void setLayout() {
		addComponent(formLayout);

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

		try {
			logger.debug("Try to commit cat " + catBean);
			
			catBinder.commit();

			logger.debug("Commited cat " + catBean);

			catViewListener.enter(catBean);
			
		} catch (CommitException e) {
			
			logger.debug("Could not commit form: " + e.getStackTrace());
			
			Notification.show("You fail!");
		}

	}

	@Override
	public void renderSaveResponse() {
		Notification.show("Thanks for breeding a cat!");
	}
}
