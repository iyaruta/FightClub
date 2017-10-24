package home.inna.fc.repository;

import home.inna.fc.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long>{

    Account findByEmail(String email);
}
