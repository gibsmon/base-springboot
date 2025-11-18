package com.poliymorf.dagaitem.util;

import com.poliymorf.dagaitem.data.constanta.GlobalMessage;
import com.poliymorf.dagaitem.util.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class TimePars {

    public static Date stringDateToDate(String date) {
      try{
          SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
          return sdf.parse(date);
      }catch (Exception e){
          log.error("ERROR : {}",e.getMessage());
          throw new BusinessException(GlobalMessage.FAILED_PARSING);
      }

    }
}
