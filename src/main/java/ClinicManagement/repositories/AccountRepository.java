package ClinicManagement.repositories;


import ClinicManagement.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {


    Account findByUsernameAndPasswordAndIsActive(String username,String password,boolean isActive);

   /* @Query("Select new ClinicManagement.aid.LoginResponse(a.account_id,r.role_id,r.name) From Role r JOIN r.accounts a where a.account_id =:account_id")
    LoginResponse findUseInformation(@Param("account_id") int account_id);*/

    Optional<Account> findByAccountIdAndIsActive(int account_id,boolean isActive);
    List<Account>findByIsActive(boolean isActive);

}
