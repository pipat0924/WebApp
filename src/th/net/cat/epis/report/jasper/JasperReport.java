package th.net.cat.epis.report.jasper;

import static th.net.cat.epis.util.AppConstants.FILE_TYPE_JRXML;
import static th.net.cat.epis.util.AppConstants.FILE_TYPE_PDF;
import static th.net.cat.epis.util.AppConstants.JASPER_JRXML_PATH;
import static th.net.cat.epis.util.AppConstants.JASPER_LOGO_FILENAME;
import static th.net.cat.epis.util.AppConstants.JASPER_LOGO_PATH;
import static th.net.cat.epis.util.AppConstants.JASPER_NOIMG_FILENAME;
import static th.net.cat.epis.util.AppConstants.JASPER_OUTPUT_PATH_PDF;
import static th.net.cat.epis.util.AppConstants.JASPER_REPORT_URL;
import static th.net.cat.epis.util.AppConstants.PDF;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

//import net.sf.jasperreports.engine.JasperCompileManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JasperReport {
	
	@Autowired 
	private ServletContext servletContext;
	
	@Autowired
	private JasperBuilder jasperBuilder;
	
	public <E> String buildSpecificJasperReportToPDF(String jrxmlFileName, String outputFileName, List<E> collections, Map<String, Object> params) throws Exception {
    	String outputFile = getOutputFileFullPath(JASPER_OUTPUT_PATH_PDF, outputFileName, FILE_TYPE_PDF);
//    	params.put(SUB_JASPER_JRXML_FILENAME, JasperCompileManager.compileReport(getJRXMLFullPath(JASPER_JRXML_PATH, SUB_REPORT_OUTPUT_FILENAME)));
    	jasperBuilder.buildJasperReportToPDF(getJRXMLFullPath(JASPER_JRXML_PATH, jrxmlFileName), outputFile, collections, params);
        
        return generateURL(outputFile, outputFileName, PDF);
	}
	
	public <E> String buildSpecificJasperReportToXLSX(String jrxmlFileName, String outputFileName, List<E> collections, Map<String, Object> params) throws Exception {
    	String outputFile = getOutputFileFullPath(JASPER_OUTPUT_PATH_PDF, outputFileName, FILE_TYPE_PDF);
//    	params.put(SUB_JASPER_JRXML_FILENAME, JasperCompileManager.compileReport(getJRXMLFullPath(JASPER_JRXML_PATH, SUB_REPORT_OUTPUT_FILENAME)));
    	jasperBuilder.buildJasperReportToXLSX(getJRXMLFullPath(JASPER_JRXML_PATH, jrxmlFileName), outputFile, collections, params);
        
        return generateURL(outputFile, outputFileName, PDF);
	}
    
    private String generateURL(String filePath, String outputFileName, String fileType) {
    	return servletContext.getContextPath() + JASPER_REPORT_URL + "?fPath="+ filePath +"&fName=" + outputFileName + "&fType=" + fileType;
    }

    private String getOutputFileFullPath(String jasperOutputPath, String outputFileName, String fileType) {
        return servletContext.getRealPath(jasperOutputPath) + File.separatorChar + outputFileName + fileType;
    }

    private String getJRXMLFullPath(String jrxmlPath, String jrxmlFileName) {
        return servletContext.getRealPath(jrxmlPath) + File.separatorChar + jrxmlFileName + FILE_TYPE_JRXML;
    }

    public String getLogoFullPath() {
        return servletContext.getRealPath(JASPER_LOGO_PATH) + File.separatorChar + JASPER_LOGO_FILENAME;
    }
    
    public String getNoImgFullPath() {
        return servletContext.getRealPath(JASPER_LOGO_PATH) + File.separatorChar + JASPER_NOIMG_FILENAME;
    }
    
}
