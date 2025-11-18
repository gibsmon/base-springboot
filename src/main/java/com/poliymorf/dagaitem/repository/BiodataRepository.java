package com.poliymorf.dagaitem.repository;

import com.poliymorf.dagaitem.data.entity.Account;
import com.poliymorf.dagaitem.data.entity.Biodata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface BiodataRepository extends JpaRepository<Biodata, UUID>, JpaSpecificationExecutor<Biodata> {

    Optional<Biodata> findByAccount(Account account);
    Optional<Biodata> findByAccount_Id(Integer id);


}
