package ch.catnip.catbreeder.view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.ListSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Component
@Scope("prototype")
@VaadinView(CatViewImpl.PLACE)
public class CatViewImpl extends VerticalLayout implements CatView, View, ClickListener {

	public static final String PLACE = "cat";

	private static final transient Logger logger = LoggerFactory.getLogger(CatViewImpl.class);

	private FormLayout formLayout;

	// private TextField nameField;

	private ListSelect breedSelect;

	private BeanContainer<Integer, Breed> catBreedContainer;

	private BeanFieldGroup<Cat> catBinder;

	private Button saveButton;

	private Cat catBean;

	// Show uploaded file in this placeholder
	private Embedded image;
	
	//private ImageUploader receiver;

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

	/*
	// Implement both receiver that saves upload in a file and
	// listener for successful upload
	class ImageUploader implements Receiver, SucceededListener {
		public File file;

		public OutputStream receiveUpload(String filename, String mimeType) {
			// Create upload stream
			FileOutputStream fos = null; // Stream to write to
			try {
				// Open the file for writing.
				new File("/tmp/uploads/").mkdirs();
				file = new File("/tmp/uploads/" + filename);
				
				fos = new FileOutputStream(file);
			} catch (final FileNotFoundException e) {
				new Notification("Could not open file", e.getMessage(), Notification.Type.ERROR_MESSAGE).show(Page.getCurrent());
				return null;
			}
			return fos; // Return the output stream to write to
		}

		public void uploadSucceeded(SucceededEvent event) {
			// Show the uploaded file in the image viewer
			image.setVisible(true);
			image.setSource(new FileResource(file));
		}
	};
	*/

	// TODO https://vaadin.com/book/vaadin7/-/page/datamodel.itembinding.html
	@Override
	public void initComponent() {

		catBinder = new BeanFieldGroup<Cat>(Cat.class);

		catBean = new Cat();

		catBinder.setItemDataSource(catBean);

		formLayout = new FormLayout();

		/* TODO Remove this block
		nameField = new TextField("Name");
		nameField.setRequired(true);
		nameField.setRequiredError("Please enter a Name!");
		nameField.setWidth("12em");
		nameField.addValidator(new StringLengthValidator("Name must have 3-10 characters", 3, 10, false));
		nameField.setNullRepresentation("");
		nameField.setImmediate(true);

		formLayout.addComponent(nameField);
		*/

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

		// form.addComponent(breedSelect);

		TextField nameField = (TextField) catBinder.buildAndBind("Name", "name");
		nameField.setNullRepresentation("");

		// formLayout.addComponent(catBinder.buildAndBind("Name", "name"));
		formLayout.addComponent(nameField);

		catBinder.bind(breedSelect, "breed");

		// formLayout.addComponent(catBinder.buildAndBind("Breed", "breed"));
		formLayout.addComponent(breedSelect);

		formLayout.addComponent(catBinder.buildAndBind("Birthday", "birthDay"));
		
		/* TODO Remove this block
		
		
		image = new Embedded("Picture");
		image.setVisible(false);
		image.setWidth("60%");
		
		receiver = new ImageUploader();
		
		
		Upload upload = new Upload("Upload a Picture", receiver);
		upload.setButtonCaption("Start Upload");
		upload.addSucceededListener(receiver);
		
		formLayout.addComponent(image);
		formLayout.addComponent(upload);
		*/
		
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

			catViewListener.saveCat(catBean);

			Notification.show("Thanks!");
		} catch (CommitException e) {
			
			logger.debug("Could not commit form: " + e.getStackTrace());
			
			Notification.show("You fail!");
		}

	}
}
