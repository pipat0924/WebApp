package th.net.cat.epis.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;

public class FileUtil {
	
	/**
	 * Write file with UTF-8 encoding
	 * @param path
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static File writeFile(String path, String data) throws UnsupportedEncodingException, FileNotFoundException, IOException {
		File file = createFile(path);
		// Add '\ufeff' for fix UTF-8 representation of the BOM
		FileUtils.writeStringToFile(file, '\ufeff' + data, "UTF-8");
		return file;
	}
	
	/**
	 * Read file to output stream
	 * @param outputStream
	 * @param file
	 * @throws IOException 
	 */
	public static void readFileToOutputStream(OutputStream outputStream, File file) throws IOException {
		byte[] byteArray = convertFileToByteArray(file);
		outputStream.write(byteArray, 0, byteArray.length);
		outputStream.flush();
		outputStream.close();
	}

	/**
	 * Read byte array to output stream
	 * @param outputStream
	 * @param file
	 * @throws IOException 
	 */
	public static void readByteArrayToOutputStream(OutputStream outputStream, byte[] byteArray) throws IOException {
		outputStream.write(byteArray, 0, byteArray.length);
		outputStream.flush();
		outputStream.close();
	}
	
	/**
	 * Convert file to byte array
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static byte[] convertFileToByteArray(File file) throws IOException {
		return FileUtils.readFileToByteArray(file);
	}
	
	
	/**
	 * Delete File
	 * @param path
	 */
	public static boolean deleteFile(String path) {
		return deleteFile(new File(path));
	}

	/**
	 * Delete File
	 * @param file
	 */
	public static boolean deleteFile(File file) {
		boolean status = false;
		if (file.exists()) {
			status = file.delete();
		} else {
			status = true;
		}
		return status;
	}
	
	/**
	 * Create File
	 * @param path
	 * @return file
	 * @throws IOException 
	 */
	public static File createFile(String path) throws IOException {
		File file = new File(path);
		deleteFile(file);
		createDirectory(file.getParentFile());
		file.createNewFile();
		return file;
	}
	
	/**
	 * Create directory
	 * @param directory
	 */
	public static void createDirectory(File directory) {
		if(!directory.exists()) {
			directory.mkdirs();
		}
	}
	
	/**
	 * List file in directory with not include sub directory file
	 * @param directoryPath
	 * @param type
	 * @return
	 */
	public static List<File> listFileInDirectory(String directoryPath, String[] type) {
		return listFileInDirectory(directoryPath, type, false);
	}
	
	/**
	 * List file in directory
	 * @param directoryPath
	 * @param type
	 * @param isIncludeSubDirectory
	 * @return
	 */
	public static List<File> listFileInDirectory(String directoryPath, String[] type, boolean isIncludeSubDirectory) {
		File directory = new File(directoryPath);
		return (List<File>) FileUtils.listFiles(directory, type, isIncludeSubDirectory);
	}
	
	/**
	 * Upload file
	 * @throws IOException
	 */
	public static boolean uploadFile(String path, String dir, String fileName, byte[] byteArray) throws IOException {
		try {
			// Creating the directory to store file
			File dirFile = new File(path + File.separator + dir);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			// Create the file on server
			File serverFile = new File(dirFile.getAbsolutePath() + File.separator + fileName);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(byteArray);
			stream.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void zipFile(String zipFilePath, List<File> inputFileList) {
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
			ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);
			for (File inputFile : inputFileList) {
				ZipEntry zipEntry = new ZipEntry(inputFile.getName());
				zipOutputStream.putNextEntry(zipEntry);

				FileInputStream fileInputStream = new FileInputStream(inputFile);
				byte[] buf = new byte[1024];
				int bytesRead;
				while ((bytesRead = fileInputStream.read(buf)) > 0) {
					zipOutputStream.write(buf, 0, bytesRead);
				}
				fileInputStream.close();
				
				zipOutputStream.closeEntry();
			}
			zipOutputStream.close();
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
    private static final String INPUT_FILE_1 = "C:\\cat\\inventory\\failed\\ERP_PO00001_20150710.xls";
    private static final String INPUT_FILE_2 = "C:\\cat\\inventory\\failed\\SUP_PO00001_20150710.xls";
    private static final String OUTPUT_FILE  = "C:\\cat\\inventory\\failed\\ERP_PO00001_20150710.zip";

    public static void main(String[] args) {
    	List<File> inputFileList = new ArrayList<File>();
    	inputFileList.add(new File(INPUT_FILE_1));
    	inputFileList.add(new File(INPUT_FILE_2));
        zipFile(OUTPUT_FILE, inputFileList);
    }

    public static String genFileName(String file){
		String file_name = "";
		String filesub = file.substring(0, file.length() - 5);
		Long dl = new Date().getTime();
		file_name = filesub +"_"+ dl + ".json";
		return file_name;
	}
        	

}
