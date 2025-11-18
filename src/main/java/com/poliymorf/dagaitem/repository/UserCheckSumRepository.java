package com.poliymorf.dagaitem.repository;

import com.poliymorf.dagaitem.data.entity.Account;
import com.poliymorf.dagaitem.data.entity.UserCheckSum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;
import java.util.UUID;

public interface UserCheckSumRepository extends JpaRepository<UserCheckSum, UUID>, JpaSpecificationExecutor<UserCheckSum> {

    Optional<UserCheckSum> findByUserId(Integer userId);
}
