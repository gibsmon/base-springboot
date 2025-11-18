package com.poliymorf.dagaitem.repository;

import com.poliymorf.dagaitem.data.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, String>, JpaSpecificationExecutor<Account> {

  Optional<Account> findByEmail(String email);
  Optional<Account> findByUsername(String username);


  Optional<Account> findByEmailOrUsername(String username, String email);
}
