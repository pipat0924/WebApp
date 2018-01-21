import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FTPClient ftpClient = new FTPClient();
		try {
			ftpClient.connect("10.36.10.16");
			ftpClient.login("epis_user", "password021166615");
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			File localFile = new File("D://temp//".concat("PAY_EPIS_20170921_025801.TXT"));
			String remoteFile = StringUtils.trim("/home/epis_user/cat/online/").concat("PAY_EPIS_20170921_025801_GGG.TXT");
			InputStream inputStream = new FileInputStream(localFile);
			boolean done = ftpClient.storeFile(remoteFile, inputStream);
			inputStream.close();
			if(done) {
				System.out.println("The file is uploaded successfully.");
			}
			if(ftpClient.isConnected()) {
				ftpClient.logout();
				ftpClient.disconnect();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
