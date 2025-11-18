package com.poliymorf.dagaitem.repository;

import com.poliymorf.dagaitem.data.entity.Account;
import com.poliymorf.dagaitem.data.entity.AccountAddress;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AccountAddressRepository extends JpaRepository<AccountAddress, String>, JpaSpecificationExecutor<AccountAddress> {


    @Modifying
    @Transactional
    @Query("UPDATE AccountAddress ad SET ad.mainAddress = false WHERE ad.id != :uuid")
    void updateMainAddress(String uuid);

}
