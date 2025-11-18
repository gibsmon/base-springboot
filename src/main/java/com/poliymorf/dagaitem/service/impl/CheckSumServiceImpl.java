package com.poliymorf.dagaitem.service.impl;

import com.poliymorf.dagaitem.data.constanta.GlobalMessage;
import com.poliymorf.dagaitem.data.entity.UserCheckSum;
import com.poliymorf.dagaitem.data.enums.EnumSum;
import com.poliymorf.dagaitem.repository.UserCheckSumRepository;
import com.poliymorf.dagaitem.service.CheckSumService;
import com.poliymorf.dagaitem.util.RandomUtil;
import com.poliymorf.dagaitem.util.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CheckSumServiceImpl implements CheckSumService {

    private final UserCheckSumRepository userCheckSumRepository;
    @Override
    public String validateCheckSumProfile(String userId, String feSum, EnumSum enumSum) {
        String checkSUum = RandomUtil.getSaltString();
        Optional<UserCheckSum> getSum = userCheckSumRepository.findByUserId(Integer.parseInt(userId));
        if(getSum.isPresent()){
            //account, address, bidoata, profile
            var userCheckSum = getSum.get();
            switch (enumSum) {
                case ACCOUNT -> {
                    if (getSum.get().getAcc().equals(feSum)) {
                        throw new BusinessException(GlobalMessage.SUM_SAME);
                    }
                    userCheckSum.setAcc(checkSUum);
                }
                case ADDRESS -> {
                    if (getSum.get().getAdd().equals(feSum)) {
                        throw new BusinessException(GlobalMessage.SUM_SAME);
                    }
                    userCheckSum.setAdd(checkSUum);
                }
                case BIODATA -> {
                    if (getSum.get().getBio().equals(feSum)) {
                        throw new BusinessException(GlobalMessage.SUM_SAME);
                    }
                    userCheckSum.setBio(checkSUum);
                }
                case PROFILE -> {
                    if (getSum.get().getProfile().equals(feSum)) {
                        throw new BusinessException(GlobalMessage.SUM_SAME);
                    }
                    userCheckSum.setProfile(checkSUum);
                }
            }
            userCheckSumRepository.save(userCheckSum);
        }
        return checkSUum;
    }


    public Boolean saveNewSum(String userId, List<EnumSum> enumSum) {
        Optional<UserCheckSum> getSum = userCheckSumRepository.findByUserId(Integer.parseInt(userId));
        if(getSum.isPresent()){
            //account, address, bidoata, profile
            var userCheckSum = getSum.get();
            enumSum.forEach(enumSum1 -> {
                String checkSUum = RandomUtil.getSaltString();
                switch (enumSum1) {
                    case ACCOUNT -> {
                        userCheckSum.setAcc(checkSUum);
                    }
                    case ADDRESS -> {
                        userCheckSum.setAdd(checkSUum);
                    }
                    case BIODATA -> {
                        userCheckSum.setBio(checkSUum);
                    }
                    case PROFILE -> {
                        userCheckSum.setProfile(checkSUum);
                    }
                }
            });
            userCheckSumRepository.save(userCheckSum);
        }
        return true;
    }


}
