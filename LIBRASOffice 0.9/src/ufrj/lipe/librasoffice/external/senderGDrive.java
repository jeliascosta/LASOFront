package ufrj.lipe.librasoffice.external;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.googleapis.media.MediaHttpUploaderProgressListener;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.SecurityUtils;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.ServiceAccountCredentials;

class CustomProgressListener2 implements MediaHttpUploaderProgressListener {
	  JProgressBar pbar;
	  CustomProgressListener2(JProgressBar pbar){ this.pbar = pbar; }
	  public void progressChanged(MediaHttpUploader uploader) throws IOException {
	    switch (uploader.getUploadState()) {
	      case INITIATION_STARTED:
	        System.err.println("Initiation has started!");
	        break;
	      case INITIATION_COMPLETE:
	        System.err.println("Initiation is complete!");
	        pbar.setValue(0);
	        break;
	      case MEDIA_IN_PROGRESS:
	        System.err.println(uploader.getProgress());
	        pbar.setValue((int) (uploader.getProgress()*100));
	        break;
	      case MEDIA_COMPLETE:
	        System.err.println("Upload is complete!");
	        pbar.setValue(100);
	        JOptionPane.showMessageDialog(null, "ARQUIVO RECEBIDO! OBRIGADO!");
		case NOT_STARTED:
	        System.err.println("Not Started!");
			break;
		default:
	        System.err.println("Não deveria estar aqui!");
			break;
	    }
	  }
	}

public class senderGDrive implements Runnable {
	
	private Drive service;
	private java.io.File ioFile;
	private JProgressBar progress;
	
	/** Application name. */
	private final String APPLICATION_NAME = "LIBRASOffice";

	/** Global instance of the JSON factory. */
	private final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private HttpTransport HTTP_TRANSPORT;

	/** Global instance of the scopes required by this API. */
	private final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE_FILE);

	{
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	public senderGDrive(java.io.File file, JProgressBar pBar) {
		ioFile = file;
		progress = pBar;
	}
	
	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	@SuppressWarnings("deprecation")
	public HttpCredentialsAdapter authorize() throws IOException {
		System.out.println(senderGDrive.class.getClassLoader().getResourceAsStream("LIBRASOffice-7b93cd6faf0e.p12"));
		try {
			PrivateKey privateKey = SecurityUtils.loadPrivateKeyFromKeyStore(SecurityUtils.getPkcs12KeyStore(),
					senderGDrive.class.getClassLoader().getResourceAsStream("LIBRASOffice-7b93cd6faf0e.p12"), "notasecret", "privatekey","notasecret");
			return new HttpCredentialsAdapter(new ServiceAccountCredentials(null,
					"lasofront@librasoffice.iam.gserviceaccount.com", privateKey, null, SCOPES));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;

	}

	/**
	 * Build and return an authorized Drive client service.
	 * 
	 * @return an authorized Drive client service
	 * @throws IOException
	 */
	private Drive getDriveService() throws IOException {
		HttpCredentialsAdapter credential = authorize();
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}
	
	@Override
	public void run() {
		try {
			service = getDriveService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.err.println(service.toString());
		// subir um arquivo e pegar o ID dele no Drive
		File fileMetadata = new File();
		List<String> folderID = new ArrayList<String>();
		folderID.add("0BxM5TVr27KOodWtLNzRpT2lzaXc");
		fileMetadata.setParents(folderID);
	    fileMetadata.setName(ioFile.getName());
		FileContent mediaContent = new FileContent("application/octet-stream", ioFile);

		Drive.Files.Create request = null;
		try {
			request = service.files().create(fileMetadata, mediaContent);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MediaHttpUploader uploader = request.getMediaHttpUploader();
		uploader.setDirectUploadEnabled(false);
		uploader.setChunkSize(MediaHttpUploader.MINIMUM_CHUNK_SIZE);
		System.err.println(uploader.getChunkSize());
		uploader.setProgressListener(new CustomProgressListener2(progress));
		
		File file = null;
		try {
			file = request.setFields("id").execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("File ID: " + file.getId());
	}
}
