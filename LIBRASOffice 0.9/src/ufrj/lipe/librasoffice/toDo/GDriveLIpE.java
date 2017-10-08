package ufrj.lipe.librasoffice.toDo;

import java.io.IOException;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
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

public class GDriveLIpE {
	/** Application name. */
	private static final String APPLICATION_NAME = "LIBRASOffice";

	/** Global instance of the JSON factory. */
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	/** Global instance of the HTTP transport. */
	private static HttpTransport HTTP_TRANSPORT;

	/** Global instance of the scopes required by this API. */
	private static final List<String> SCOPES = Arrays.asList(DriveScopes.DRIVE_FILE);

	static {
		try {
			HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		} catch (Throwable t) {
			t.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * Creates an authorized Credential object.
	 * 
	 * @return an authorized Credential object.
	 * @throws IOException
	 */
	public static HttpCredentialsAdapter authorize() throws IOException {
		System.out.println(GDriveLIpE.class.getClassLoader().getResourceAsStream("LIBRASOffice-7b93cd6faf0e.p12"));
		try {
			PrivateKey privateKey = SecurityUtils.loadPrivateKeyFromKeyStore(SecurityUtils.getPkcs12KeyStore(),
					GDriveLIpE.class.getClassLoader().getResourceAsStream("LIBRASOffice-7b93cd6faf0e.p12"), "notasecret", "privatekey","notasecret");
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
	public static Drive getDriveService() throws IOException {
		HttpCredentialsAdapter credential = authorize();
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}

	public static void main(String[] args) throws IOException {
		// Build a new authorized API client service.
		Drive service = getDriveService();

//		// Print the names and IDs for up to 10 files.
//		FileList result = service.files().list().setPageSize(10).setFields("nextPageToken, files(id, name)").execute();
//		List<File> files = result.getFiles();
//		if (files == null || files.size() == 0) {
//			System.out.println("No files found.");
//		} else {
//			System.out.println("Files:");
//			for (File file : files) {
//				System.out.printf("%s (%s)\n", file.getName(), file.getId());
//			}
//		}

		// subir um arquivo e pegar o ID dele no Drive
		File fileMetadata1 = new File();
		List<String> folderID = new ArrayList<String>();
		folderID.add("0BxM5TVr27KOodWtLNzRpT2lzaXc");
		fileMetadata1.setParents(folderID);
		
		JFrame frame = new JFrame("FrameDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new java.io.File(System.getProperty("user.home")));
		int result1 = fileChooser.showOpenDialog(frame);
		java.io.File selectedFile = null;
		while(result1 == JFileChooser.APPROVE_OPTION) {
		    selectedFile = fileChooser.getSelectedFile();
		    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
			
		    fileMetadata1.setName(selectedFile.getName());
			FileContent mediaContent1 = new FileContent("application/octet-stream", selectedFile);
			File file = service.files().create(fileMetadata1, mediaContent1).setFields("id").execute();
			System.out.println("File ID: " + file.getId());
			result1 = fileChooser.showOpenDialog(frame);
		}
		frame.dispose();
	}
}
