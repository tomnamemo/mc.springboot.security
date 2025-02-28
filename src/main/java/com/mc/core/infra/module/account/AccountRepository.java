package com.mc.core.infra.module.account;

import com.mc.core.infra.module.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

}
