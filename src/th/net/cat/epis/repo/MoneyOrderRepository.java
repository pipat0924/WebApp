package th.net.cat.epis.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import th.net.cat.epis.entity.MethodMoneyOrder;
import th.net.cat.epis.entity.Transaction;

public interface MoneyOrderRepository extends JpaRepository<MethodMoneyOrder,Long>{
	


}
