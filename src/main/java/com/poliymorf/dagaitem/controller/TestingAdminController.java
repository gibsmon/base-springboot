package com.poliymorf.dagaitem.controller;


import com.poliymorf.dagaitem.util.exception.BusinessException;
import com.poliymorf.dagaitem.data.constanta.GlobalMessage;
import com.poliymorf.dagaitem.data.dto.response.TestResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class TestingAdminController {

    @GetMapping(value = "/")
    public TestResponse get() {
        TestResponse testying = TestResponse.builder().name("TESTYING").build();
        throw new BusinessException(GlobalMessage.DATA_NOT_FOUND);
    }

}
