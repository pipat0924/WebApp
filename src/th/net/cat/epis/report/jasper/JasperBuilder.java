/*
 * Report builder module. Using Jasper Report as main engine.
 */

package th.net.cat.epis.report.jasper;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;

import org.springframework.stereotype.Repository;

@Repository
public class JasperBuilder {

    /**
     * Build PDF with specific output file.
     * @param <E>
     * @param jrxmlFileFullPath (with file name and file type attached)
     * @param outputFileName (with file name and file type attached)
     * @param collections
     * @param params
     * @return
     * @throws Exception
     */
    public <E> void buildJasperReportToPDF(String jrxmlFileFullPath, String outputFileFullPath, List<E> collections, Map<String, Object> params) throws Exception {
        JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        JasperReport report = JasperCompileManager.compileReport(jrxmlFileFullPath);
        JasperPrint print = JasperFillManager.fillReport(report, params, dataSource);
        JasperExportManager.exportReportToPdfFile(print, outputFileFullPath);
    }

     /**
     * Build xlsx with specific output file.
     * @param <E>
     * @param jrxmlFileFullPath (with file name and file type attached)
     * @param outputFileName  (with file name and file type attached)
     * @param collections
     * @param params
     * @return
     * @throws Exception
     */
    public <E> void buildJasperReportToXLSX(String jrxmlFileFullPath, String outputFileFullPath, List<E> collections, Map<String, Object> params) throws Exception {
        JRDataSource dataSource = (collections != null && !collections.isEmpty()) ? new JRBeanCollectionDataSource(collections) : new JREmptyDataSource();
        JasperPrint print = JasperFillManager.fillReport(JasperCompileManager.compileReport(jrxmlFileFullPath), params, dataSource);
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setParameter(JRExporterParameter.INPUT_FILE_NAME, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outputFileFullPath);
        exporter.exportReport();
    }

}
