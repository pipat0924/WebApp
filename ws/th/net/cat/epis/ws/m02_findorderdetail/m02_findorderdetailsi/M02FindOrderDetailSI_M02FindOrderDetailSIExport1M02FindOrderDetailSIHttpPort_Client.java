
package th.net.cat.epis.ws.m02_findorderdetail.m02_findorderdetailsi;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 3.1.6
 * 2017-02-26T01:32:30.536+07:00
 * Generated source version: 3.1.6
 * 
 */
public final class M02FindOrderDetailSI_M02FindOrderDetailSIExport1M02FindOrderDetailSIHttpPort_Client {

    private static final QName SERVICE_NAME = new QName("http://M02_FindOrderDetail/M02_FindOrderDetailSI", "M02_FindOrderDetailSIExport1_M02_FindOrderDetailSIHttpService");

    private M02FindOrderDetailSI_M02FindOrderDetailSIExport1M02FindOrderDetailSIHttpPort_Client() {
    }

    public static void main(String args[]) throws Exception {
        URL wsdlURL = M02FindOrderDetailSIExport1M02FindOrderDetailSIHttpService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        M02FindOrderDetailSIExport1M02FindOrderDetailSIHttpService ss = new M02FindOrderDetailSIExport1M02FindOrderDetailSIHttpService(wsdlURL, SERVICE_NAME);
        M02FindOrderDetailSI port = ss.getM02FindOrderDetailSIExport1M02FindOrderDetailSIHttpPort();  
        
        {
        System.out.println("Invoking findOrderDetailInfo...");
            th.net.cat.epis.ws.m02_findorderdetail.FindOrderDetailRequest _findOrderDetailInfo_findOrderDetailRequest = null;
            th.net.cat.epis.ws.m02_findorderdetail.FindOrderDetailResponse _findOrderDetailInfo__return = port.findOrderDetailInfo(_findOrderDetailInfo_findOrderDetailRequest);
        System.out.println("findOrderDetailInfo.result=" + _findOrderDetailInfo__return);


        }

        System.exit(0);
    }

}