package th.net.cat.epis.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import th.net.cat.epis.util.FileUtil;

public class DownloadReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1636974837533021859L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = request.getParameter("fPath");
		String fileName = request.getParameter("fName");
		String fileType = request.getParameter("fType");
		File pdfFile;
		try {
			pdfFile = new File(filePath);
			response.setContentType("application/pdf");
			response.setHeader("Content-Disposition", "inline;filename=\"" + fileName + "." + fileType + "\"");
			FileUtil.readFileToOutputStream(response.getOutputStream(), pdfFile);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}
	
}
