package ch.catnip.catbreeder.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.vaadin.server.StreamResource;
import com.vaadin.server.StreamResource.StreamSource;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.ChangeEvent;
import com.vaadin.ui.Upload.ChangeListener;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

public class EditableImage extends CustomField<ImageData> implements Receiver, ProgressListener, FailedListener, SucceededListener {
	private static final long serialVersionUID = -4520882923454628875L;

	// Put upload in this memory buffer that grows automatically
	private ByteArrayOutputStream os = new ByteArrayOutputStream(10240);

	private VerticalLayout content;
	private Image image;
	private Upload upload;
	private ProgressBar progress = new ProgressBar(0.0f);

	private ImageData newimage = null;

	public EditableImage(String caption, ImageData imagedata) {
		setCaption(caption);

		content = new VerticalLayout();
		image = new Image();
		content.addComponent(image);

		// These also set the image so can't call before it's set
		setValue(imagedata);
		setInternalValue(imagedata);

		content.addComponent(progress);
		progress.setVisible(false);

		// Create the upload component and handle all its events
		upload = new Upload("Upload new image here", this);
		upload.setButtonCaption(null);
		upload.addProgressListener(this);
		upload.addFailedListener(this);
		upload.addSucceededListener(this);
		content.addComponent(upload);

		upload.addChangeListener(new ChangeListener() {
			private static final long serialVersionUID = 8712035630286804938L;

			@Override
			public void filenameChanged(ChangeEvent event) {
				upload.submitUpload();
			}
		});
	}

	/* This should be static method */
	public StreamSource createStreamSource(final ImageData imageData) {
		return new StreamSource() {
			private static final long serialVersionUID = -4905654404647215809L;

			public InputStream getStream() {
				return new ByteArrayInputStream(imageData.getImagedata());
			}
		};
	}

	protected void setInternalValue(final ImageData newValue) {
		super.setInternalValue(newValue);

		if (newValue == null)
			return;

		StreamSource source = createStreamSource(newValue);

		if (image.getSource() == null)
			// Create a new stream resource
			image.setSource(new StreamResource(source, newValue.getFilename()));
		else { // Reuse the old resource
			StreamResource resource = (StreamResource) image.getSource();
			resource.setStreamSource(source);
			resource.setFilename(newValue.getFilename());
		}

		image.setVisible(true);
		image.markAsDirty();
	}

	@Override
	public void setReadOnly(boolean readOnly) {
		super.setReadOnly(readOnly);

		upload.setVisible(!readOnly);
	}

	@Override
	protected Component initContent() {
		return content;
	}

	@Override
	public Class<? extends ImageData> getType() {
		return ImageData.class;
	}

	public OutputStream receiveUpload(String filename, String mimeType) {
		newimage = new ImageData(filename, mimeType, null);
		os.reset(); // Needed to allow re-uploading
		return os;
	}

	@Override
	public void updateProgress(long readBytes, long contentLength) {
		progress.setVisible(true);
		if (contentLength == -1)
			progress.setIndeterminate(true);
		else {
			progress.setIndeterminate(false);
			progress.setValue(((float) readBytes) / ((float) contentLength));
		}
	}

	public void uploadSucceeded(SucceededEvent event) {
		newimage.setImagedata(os.toByteArray());
		setValue(newimage);
		progress.setVisible(false);
	}

	@Override
	public void uploadFailed(FailedEvent event) {
		Notification.show("Upload failed", Notification.Type.ERROR_MESSAGE);
	}
}
