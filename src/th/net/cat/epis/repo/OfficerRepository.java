package th.net.cat.epis.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import th.net.cat.epis.entity.Menu;
import th.net.cat.epis.entity.Officer;

@RepositoryRestResource(path = "officers")
public interface OfficerRepository extends CrudRepository<Officer, Long> {

	@RestResource(path = "code")
	Officer findByCode(@Param("code") String code);

	@RestResource(path = "name")
	Officer findByName(@Param("name") String userName);
	
	List<Officer> findByCodeContainingIgnoreCaseAndNameContainingIgnoreCaseAndGivenNameContainingIgnoreCaseAndFamilyNameContainingIgnoreCase(@Param("code") String code, @Param("name") String userName, @Param("givenName") String givenName, @Param("familyName") String familyName);
	/*
	@Query(
			value=" select "
  +"      officer0_.OFFICERID as OFFICERID, "
  +"      officer0_.OFFICERCODE as OFFICERCODE2_39_0_, "
  +"      officer0_.DESCRIPTION as DESCRIPTION3_39_0_, "
  +"      officer0_.OFFICERFAMILYNAME as OFFICERFAMILYNAME4_39_0_, "
  +"      officer0_.OFFICERGIVENNAME as OFFICERGIVENNAME5_39_0_, "
  +"      officer0_.ISPOSITIVE as ISPOSITIVE6_39_0_, "
  +"      officer0_.USERNAME as USERNAME7_39_0_, "
  +"      officer0_.PERMISSION as PERMISSION8_39_0_, "
  +"      officer0_.PRINCIPALID as PRINCIPALID12_39_0_, "
  +"      officer0_.SESSIONID as SESSIONID9_39_0_, "
  +"      officer0_.VERIFY_FLAG as VERIFY_FLAG10_39_0_, "
  +"      officer0_.VERIFY_KEY as VERIFY_KEY11_39_0_, "
  +"      machines1_.OFFICERID as OFFICERID1_12_1_, "
  +"      pos2_.POSID as POSID2_12_1_, "
  +"      pos2_.POSID as POSID1_41_2_, "
  +"      pos2_.DESCRIPTION as DESCRIPTION2_41_2_, "
  +"      pos2_.ISPOSITIVE as ISPOSITIVE3_41_2_, "
  +"      pos2_.MAC as MAC4_41_2_, "
  +"      pos2_.POSNAME as POSNAME5_41_2_, "
  +"      pos2_.POSNO as POSNO6_41_2_, "
  +"      pos2_.SHOPID as SHOPID7_41_2_, "
  +"      shop3_.SHOPID as SHOPID1_43_3_, "
  +"      shop3_.ADDRESS as ADDRESS2_43_3_, "
  +"      shop3_.BUAREANAME as BUAREANAME3_43_3_, "
  +"      shop3_.BUPLACENAME as BUPLACENAME4_43_3_, "
  +"      shop3_.BUILDING as BUILDING5_43_3_, "
  +"      shop3_.BUAREA as BUAREA6_43_3_, "
  +"      shop3_.BUPLACE as BUPLACE7_43_3_, "
  +"      shop3_.COSTCENTER as COSTCENTER8_43_3_, "
  +"      shop3_.COSTCENTERNAME as COSTCENTERNAME9_43_3_, "
  +"      shop3_.DESCABVRTH as DESCABVRTH10_43_3_, "
  +"      shop3_.DESCRIPTION as DESCRIPTION11_43_3_, "
  +"      shop3_.DISTRICT as DISTRICT12_43_3_, "
  +"      shop3_.EMAIL as EMAIL13_43_3_, "
  +"      shop3_.FAX as FAX14_43_3_, "
  +"      shop3_.FLOOR as FLOOR15_43_3_, "
  +"      shop3_.ISPOSITIVE as ISPOSITIVE16_43_3_, "
  +"      shop3_.SHOPNAME as SHOPNAME17_43_3_, "
  +"      shop3_.SHOPNO as SHOPNO18_43_3_, "
  +"      shop3_.PHONE as PHONE19_43_3_, "
  +"      shop3_.PROVINCE as PROVINCE20_43_3_, "
  +"      shop3_.ROOM as ROOM21_43_3_, "
  +"      shop3_.STREET as STREET22_43_3_, "
  +"      shop3_.SUBDISTRICT as SUBDISTRICT23_43_3_, "
  +"      shop3_.ZIPCODE as ZIPCODE24_43_3_, "
  +"      principal4_.PRINCIPALID as PRINCIPALID1_7_4_, "
  +"      principal4_.DESCRIPTION as DESCRIPTION2_7_4_, "
  +"      principal4_.NAME as NAME3_7_4_, "
  +"      principal4_.UPDATEDTTM as UPDATEDTTM4_7_4_, "
  +"      principal4_.UPDATESYSTEM as UPDATESYSTEM5_7_4_, "
  +"      principal4_.UPDATEUSER as UPDATEUSER6_7_4_, "
  +"      principal4_.VERSIONSTAMP as VERSIONSTAMP7_7_4_, "
  +"      authen5_.USERAUTHNTICNID as USERAUTHNTICNID1_8_5_, "
  +"      authen5_.OFFICERID as OFFICERID, "
  +"      authen5_.PASSWORD as PASSWORD  "
  +"  from "
  +"      MASOFFICER officer0_  "
  +"  left outer join "
  +"      COROFFICERPOS machines1_  "
  +"          on officer0_.OFFICERID=machines1_.OFFICERID  "
  +"  left outer join "
  +"      MASPOS pos2_  "
  +"          on machines1_.POSID=pos2_.POSID  "
  +"  left outer join "
  +"      MASSHOP shop3_  "
  +"          on pos2_.SHOPID=shop3_.SHOPID  "
  +"  inner join "
  +"      ARCPRINCIPAL principal4_  "
  +"          on officer0_.PRINCIPALID=principal4_.PRINCIPALID  "
  +"  left outer join "
  +"      ARCUSERAUTHNTICN authen5_  "
  +"          on officer0_.OFFICERID=authen5_.OFFICERID  "
  +"  where "
  +"      officer0_.OFFICERID=:offerId ", nativeQuery=true)
	List<Officer> findByOferrIdOnAuthenOfferIdEqualOfficerOfferId(@Param("offerId") Long long1);*/
	
}