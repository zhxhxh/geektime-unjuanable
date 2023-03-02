package com.so_incredible.unjuanable.adapter.driven.restful.orgmng;

import com.so_incredible.unjuanable.application.orgmng.IOrgService;
import com.so_incredible.unjuanable.application.orgmng.OrgDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class OrgController {

    private IOrgService orgService;

    @Autowired
    public void setOrgService(IOrgService orgService) {
        this.orgService = orgService;
    }

    @PostMapping("/api/organizations")
    public OrgDto addOrg(@RequestBody OrgDto orgDto) {
        // 从请求里解析出 userId ...
        Long userId = getUserId(orgDto);
        OrgDto request = orgDto;
        return orgService.addOrg(request, userId);
    }

    private Long getUserId(OrgDto orgDto) {
        Random random = new Random();
        return random.nextLong();
    }
}
