package ch.catnip.catbreeder.ui;

import java.io.Serializable;

public class ImageData implements Serializable {
	private static final long serialVersionUID = 7590792385834419260L;

	private String filename;
	private String mimetype;
	private byte[] imagedata;

	public ImageData(String filename, String mimetype, byte[] imagedata) {
		this.filename = filename;
		this.mimetype = mimetype;
		this.imagedata = imagedata;
	}

	public String getFilename() {
		return filename;
	}

	public String getMimetype() {
		return mimetype;
	}

	public byte[] getImagedata() {
		return imagedata;
	}

	public void setImagedata(byte[] imagedata) {
		this.imagedata = imagedata;
	}
}
