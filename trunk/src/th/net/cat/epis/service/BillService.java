package th.net.cat.epis.service;

import org.springframework.beans.factory.annotation.Autowired;

import th.net.cat.epis.repo.BillRepository;
import th.net.cat.epis.repo.SapIBACSSRepository;
import th.net.cat.epis.repo.SapOTBOSSRepository;
import th.net.cat.epis.repo.SapTBOSSRepository;

@org.springframework.stereotype.Service
public class BillService {

	@Autowired BillRepository billRepo;
	@Autowired SapIBACSSRepository ibacssRepo;
	@Autowired SapTBOSSRepository tbossRepo;
	@Autowired SapOTBOSSRepository otbossRepo;

}