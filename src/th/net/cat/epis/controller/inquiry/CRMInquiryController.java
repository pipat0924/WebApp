package th.net.cat.epis.controller.inquiry;

import static org.apache.commons.lang.StringUtils.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.persistence.*;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import th.net.cat.crm.entity.BillProfile;
import th.net.cat.crm.entity.CustomerAddress;
import th.net.cat.crm.entity.CustomerProfile;
import th.net.cat.crm.entity.CustomerSegment;
import th.net.cat.crm.repo.BillProfileRepository;
import th.net.cat.crm.repo.CustomerProfileRepository;
import th.net.cat.epis.dto.AlertMessage;
import th.net.cat.epis.dto.BillProfileDTO;
import th.net.cat.epis.dto.BillingInfo;
import th.net.cat.epis.dto.CustomerDTO;

@Controller
public class CRMInquiryController {
	
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(CRMInquiryController.class);

	@Resource(name="episJdbcTemplate") JdbcTemplate episJdbcTemplate;
	@Resource(name = "viewCrmJdbcTemplate") JdbcTemplate viewCrmJdbcTemplate;
	
	@Autowired CustomerProfileRepository customerProfileRepository;
	@Autowired
	BillProfileRepository billProfileRepository;
	@ResponseBody
	@RequestMapping(value="searchCustomerProfile.json", method=RequestMethod.GET)
	public CustomerDTO searchCustomerProfileJSON(BillingInfo input) throws Exception {
		CustomerDTO customerDTO = new CustomerDTO();
		List<CustomerProfile> customerProfiles = customerProfileRepository.findByNo(input.getBillingNo());
		
		for(CustomerProfile customerProfile : customerProfiles){
			customerDTO.addData(customerProfile);
		}
		return customerDTO;
	}
	@ResponseBody
	@RequestMapping(value="findCustomerProfile.json", method=RequestMethod.GET)
	public BillProfileDTO findCustomerProfileJSON(
			@RequestParam("customerFullName") String customerFullName,
            @RequestParam("customerNumber") String customerNumber,
			@RequestParam("idRegisterNumber") String idRegisterNumber,
			@RequestParam("catCustomerSegment") String catCustomerSegment
	) throws Exception {

		boolean isCustomerFullNameNotEmpty = isNotEmpty(customerFullName);
		boolean isCustomerNumberNameNotEmpty = isNotEmpty(customerNumber);
		boolean isCatCustomerSegmentNameNotEmpty = isNotEmpty(catCustomerSegment)  && !catCustomerSegment.equals("-1");
		boolean isIdRegisterNumberNameNotEmpty = isNotEmpty(idRegisterNumber);


		BillProfileDTO dto = new BillProfileDTO();

		String query = "SELECT * FROM CRMDATA.V_CATCRM_CUSTOMER_PROFILE c " +
				" left join CRMDATA.CATCRM_CUSTOMER_SEGMENT cs on c.CAT_CUSTOMER_SEGMENT=cs.CODE ";
  			boolean haveWhere = false;
			if (isCustomerFullNameNotEmpty) {
				query += ((!haveWhere)?" WHERE ":" AND ")+" c.CUSTOMER_FULL_NAME LIKE '%"+customerFullName+"%' ";
				haveWhere = true;
			}
		if (isCustomerNumberNameNotEmpty) {
			query += ((!haveWhere)?" WHERE ":" AND ")+" c.CUSTOMER_NUMBER = "+customerNumber+"";
			haveWhere = true;
		}
		if (isCatCustomerSegmentNameNotEmpty) {
			query += ((!haveWhere)?" WHERE ":" AND ")+" c.CAT_CUSTOMER_SEGMENT = '"+catCustomerSegment+"' ";
			haveWhere = true;
		}if (isIdRegisterNumberNameNotEmpty) {
			query += ((!haveWhere)?" WHERE ":" AND ")+" c.CAT_CARD_NUMBER = '"+idRegisterNumber+"' ";
		}
		final List<BillProfile> profiles = new ArrayList<BillProfile>();

		viewCrmJdbcTemplate.query(query, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				BillProfile profile = new BillProfile();;
				CustomerProfile customer = new CustomerProfile();

				customer.setId(row.getString("CUSTOMER_ID"));
				customer.setNo(row.getString("CUSTOMER_NUMBER"));
				customer.setType(row.getString("CUSTOMER_TYPE"));
				customer.setTypeCode(row.getLong("CAT_CARD_TYPE_CODE"));
				customer.setTypeDesc(row.getString("CAT_CARD_TYPE_DESC"));
				customer.setCardNo(row.getString("CAT_CARD_NUMBER"));
				customer.setFullName(row.getString("CUSTOMER_FULL_NAME"));
				customer.setEmployeeId(row.getString("EMPLOYEE_ID"));
				customer.setGroupCode(row.getString("CUSTOMER_GROUP_CODE"));
				customer.setGroupName(row.getString("CUSTOMER_GROUP_NAME"));

				CustomerSegment segment = new CustomerSegment();
				segment.setId(row.getString("CAT_CUSTOMER_SEGMENT"));
				segment.setText(row.getString("TEXT"));
				customer.setSegment(segment);

				profile.setCustomer(customer);
				profiles.add(profile);
			}
			
		});
		
		dto.setStatusCode("0");
		dto.getData().addAll(profiles);
		return dto;
	}
	@ResponseBody
	@RequestMapping(value="findBillingProfile.json", method=RequestMethod.GET)
	public BillProfileDTO findBillingProfileJSON(
			@RequestParam("customerFirstName") String customerFirstName,
			@RequestParam("customerLastName") String customerLastName,
			@RequestParam("orgName") String orgName,
			@RequestParam("customerNumber") String customerNumber,
			@RequestParam("idRegisterNumber") String idRegisterNumber,
			@RequestParam("catCustomerSegment") String catCustomerSegment
	) throws Exception {

		boolean isCustomerFirstNameNotEmpty = isNotEmpty(customerFirstName);
		boolean isCustomerLastNameNotEmpty = isNotEmpty(customerLastName);
		boolean isOrgNameNotEmpty = isNotEmpty(orgName);
		boolean isCustomerNumberNameNotEmpty = isNotEmpty(customerNumber);
		boolean isCatCustomerSegmentNameNotEmpty = isNotEmpty(catCustomerSegment) && !catCustomerSegment.equals("-1");
		boolean isIdRegisterNumberNameNotEmpty = isNotEmpty(idRegisterNumber);

		BillProfileDTO dto = new BillProfileDTO();
		String query = "SELECT * FROM CRMDATA.V_CATCRM_BILLING_PROFILE b " +
				" left join CRMDATA.CATCRM_CUSTOMER_SEGMENT cs on b.ACCT_CAT_LKP=cs.CODE ";

		boolean haveWhere = false;
		boolean isIndividual = false;
		boolean isOrganization = false;
		boolean isSelectedSegment =false;
		if (isCustomerFirstNameNotEmpty) {
			query += ((!haveWhere)?" WHERE ":" AND ")+" b.BILL_FIRST_NAME LIKE '%"+customerFirstName+"%' ";
			haveWhere = true;
			isIndividual =true;
		}
		if (isCustomerLastNameNotEmpty) {
			query += ((!haveWhere)?" WHERE ":" AND ")+" b.BILL_LAST_NAME LIKE '%"+customerLastName+"%' ";
			haveWhere = true;
			isIndividual =true;
		}

		if (isOrgNameNotEmpty) {
			query += ((!haveWhere)?" WHERE ":" AND ")+" b.BILL_COMP_NAME LIKE '%"+orgName+"%' ";
			haveWhere = true;
			isOrganization = true;
		}
		if (isCustomerNumberNameNotEmpty) {
			query += ((!haveWhere)?" WHERE ":" AND ")+" b.CAT_BILL_ACCT_NUMBER = '"+customerNumber+"'";
			haveWhere = true;

		}
		if (isCatCustomerSegmentNameNotEmpty) {
			query += ((!haveWhere)?" WHERE ":" AND ")+" b.ACCT_CAT_LKP = '"+catCustomerSegment+"' ";
			haveWhere = true;
			isSelectedSegment = true;
		}if (isIdRegisterNumberNameNotEmpty) {
			query += ((!haveWhere)?" WHERE ":" AND ")+" b.TAX_REGISTER_NUMBER = '"+idRegisterNumber+"' ";
		}
		if(!isSelectedSegment){
			if(isIndividual)
				query += ((!haveWhere)?" WHERE ":" AND ")+" b.ACCT_CAT_LKP = 3 ";
			if(isOrganization)
				query += ((!haveWhere)?" WHERE ":" AND ")+" b.ACCT_CAT_LKP != 3 ";
		}
		final List<BillProfile> profiles = new ArrayList<BillProfile>();

		viewCrmJdbcTemplate.query(query, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				BillProfile profile = new BillProfile();
				profile.setId(row.getString("CAT_BILL_ACCT_ID"));
				profile.setNo(row.getString("CAT_BILL_ACCT_NUMBER"));
				profile.setTaxRegisterNo(row.getString("TAX_REGISTER_NUMBER"));
				profile.setBranchId(row.getString("BRANCH_ID"));
				profile.setBillFirstName(row.getString("BILL_FIRST_NAME"));
				profile.setBillLastName(row.getString("BILL_LAST_NAME"));
				profile.setBillCompName(row.getString("BILL_COMP_NAME"));
				profile.setBillGroup(row.getString("BILLING_GROUP"));
				profile.setBillGroupFull(row.getString("BILLING_GROUP_FULL"));
				profile.setCollectionUnit(row.getString("COLLECTION_UNIT_DESC"));
				profile.setCustomerAccountName(row.getString("CUSTOMER_ACCOUNT_NAME"));
				profile.setType(row.getString("CUSTOMER_TYPE"));
				profile.setVatType(row.getString("VAT_TYPE"));
				profile.setCurrency(row.getString("CURRENCY"));
				profile.setAccountCategoryLookup(row.getString("ACCT_CAT_LKP"));
				profile.setEgpNumber(row.getString("EGP_NUMBER"));
				profile.setEgpContact(row.getString("EGP_CONTACT"));
				profile.setCatCustomerSegment(row.getString("CAT_CUSTOMER_SEGMENT"));

				CustomerProfile customer = new CustomerProfile();

				CustomerSegment segment = new CustomerSegment();
				segment.setId(row.getString("CAT_CUSTOMER_SEGMENT"));
				segment.setText(row.getString("TEXT"));
				customer.setSegment(segment);

				profile.setCustomer(customer);
				profiles.add(profile);
			}

		});

		dto.setStatusCode("0");
		dto.getData().addAll(profiles);
		return dto;
	}
	@ResponseBody
	@RequestMapping(value="findBillProfileCustomer.json", method=RequestMethod.GET)
	public BillProfileDTO findBillProfileByCustomer(@RequestParam("fn") String firstName, @RequestParam("ln") String lastName, @RequestParam("custNo") String custNo) throws Exception {

		boolean isFNameNotEmpty = isNotEmpty(firstName);
		boolean isLNameNotEmpty = isNotEmpty(lastName);
		BillProfileDTO dto = new BillProfileDTO();
		// short circuit
		/*if(!isFNameNotEmpty & !isLNameNotEmpty) {
			dto.setStatusCode("10");
			dto.getWarningList().add(new AlertMessage("10", "กรุณาระบุ ชื่อ หรือ นามสกุล สำหรับการค้นห้า"));
			return dto;
		}*/

		String query = " SELECT * FROM crmdata.V_CATCRM_BILLING_PROFILE b JOIN crmdata.V_CATCRM_CUSTOMER_PROFILE c ON b.CUSTOMER_ID = c.CUSTOMER_ID ";
		if(isFNameNotEmpty & isLNameNotEmpty) {
			// search by both
			String subQuery = "'%"+firstName+"%"+lastName+"%' ";
			query += "WHERE b.BILL_FIRST_NAME LIKE "+subQuery;
			query += "OR b.BILL_LAST_NAME LIKE "+subQuery;
		} else if (isFNameNotEmpty) {
			// search by firstname
			query += "WHERE b.BILL_FIRST_NAME LIKE '%"+firstName+"%' ";
		} else if (isLNameNotEmpty) {
			// search by lastname
			query += "WHERE b.BILL_LAST_NAME LIKE '%"+lastName+"%' ";
		}else{
			query +=" WHERE 1 = 1 ";
		}
		if(isNotEmpty(custNo)){
			query += " and c.CUSTOMER_NUMBER = '"+custNo+"' ";
		}
		/*if(isIndividual) {
			query += "AND b.CUSTOMER_TYPE = 'INDIVIDUAL' ";
		} else {
			query += "AND b.CUSTOMER_TYPE != 'INDIVIDUAL' ";
		}*/

		final List<BillProfile> profiles = new ArrayList<BillProfile>();

		viewCrmJdbcTemplate.query(query, new RowCallbackHandler() {

			@Override
			public void processRow(ResultSet row) throws SQLException {
				BillProfile profile = new BillProfile();
				profile.setId(row.getString("CAT_BILL_ACCT_ID"));
				profile.setNo(row.getString("CAT_BILL_ACCT_NUMBER"));
				profile.setBillFirstName(row.getString("BILL_FIRST_NAME"));
				profile.setBillLastName(row.getString("BILL_LAST_NAME"));
				profile.setBillGroup(row.getString("BILLING_GROUP"));
				profile.setBillGroupFull(row.getString("BILLING_GROUP_FULL"));
				profile.setCustomerAccountName(row.getString("CUSTOMER_ACCOUNT_NAME"));
				profile.setTaxRegisterNo(row.getString("TAX_REGISTER_NUMBER"));
				profile.setBranchId(row.getString("BRANCH_ID"));
				profile.setType(row.getString("CUSTOMER_TYPE"));
				CustomerProfile customer = new CustomerProfile();
				customer.setId(row.getString("CUSTOMER_ID"));
				customer.setNo(row.getString("CUSTOMER_NUMBER"));
				profile.setCustomer(customer);
				profiles.add(profile);
			}

		});

		dto.setStatusCode("0");
		dto.getData().addAll(profiles);
		return dto;
	}

}