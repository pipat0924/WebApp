package th.net.cat.epis.service;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.net.cat.epis.controller.EpContextHolder;
import th.net.cat.epis.entity.Document;
import th.net.cat.epis.entity.KeyGenerator;
import th.net.cat.epis.repo.KeyGeneratorRepository;

import java.util.Date;

import static java.util.Locale.ENGLISH;
import static org.apache.commons.lang.StringUtils.leftPad;

/**
 * Created by nastanda on 12/1/2016 AD.
 */
@Service
public class KeyGeneratorService {
    @Autowired
    KeyGeneratorRepository keyGeneratorRepository;
    public String getDocumentNo(String posNo, String branchCode, String docCode, Date docDate) {
        String currentDate = FastDateFormat.getInstance("yyMMdd", ENGLISH).format((docDate != null)?  docDate : new Date());
        KeyGenerator keyGenerator = keyGeneratorRepository.findByDocCodeAndBranchCode(docCode, branchCode);
        String docNo = "NODOCRUNNING";
        if(keyGenerator!=null){
            keyGenerator.setRunningNumber(keyGenerator.getRunningNumber()+1);
            docNo = new StringBuilder(keyGenerator.getDocHeader()).append(leftPad(keyGenerator.getBranchCode(), 5, "0")).append(leftPad(posNo, 2, "0")).append(keyGenerator.getDocType()).append(currentDate).append(leftPad(keyGenerator.getRunningNumber().toString(), 4, "0")).toString();
            keyGeneratorRepository.save(keyGenerator);
        }
        return docNo;
    }
}
