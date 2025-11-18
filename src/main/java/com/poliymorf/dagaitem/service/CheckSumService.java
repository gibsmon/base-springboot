package com.poliymorf.dagaitem.service;

import com.poliymorf.dagaitem.data.enums.EnumSum;

import java.util.List;

public interface CheckSumService {
    String validateCheckSumProfile(String userId, String feSum, EnumSum profile);

    Boolean saveNewSum(String userId, List<EnumSum> enumSums);
}
