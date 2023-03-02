package com.so_incredible.unjuanable.domain.orgmng.org;

import com.so_incredible.unjuanable.domain.orgmng.org.validator.OrgLeaderValidator;
import com.so_incredible.unjuanable.domain.orgmng.org.validator.OrgNameValidator;
import com.so_incredible.unjuanable.domain.orgmng.org.validator.OrgTypeValidator;
import com.so_incredible.unjuanable.domain.orgmng.org.validator.SuperiorValidator;
import com.so_incredible.unjuanable.domain.tenantmng.TenantValidator;

import java.time.LocalDateTime;

public class OrgBuilder {

    private final TenantValidator tenantValidator;
    private final OrgTypeValidator orgTypeValidator;
    private final SuperiorValidator superiorValidator;
    private final OrgNameValidator orgNameValidator;
    private final OrgLeaderValidator orgLeaderValidator;

    //用这些属性保存创建对象用到的参数
    private Long tenantId;
    private Long superiorId;
    private String orgTypeCode;
    private Long leaderId;
    private String name;
    private Long createdBy;

    public OrgBuilder(TenantValidator tenantValidator,
                      OrgTypeValidator orgTypeValidator,
                      SuperiorValidator superiorValidator,
                      OrgNameValidator orgNameValidator,
                      OrgLeaderValidator orgLeaderValidator) {
        this.tenantValidator = tenantValidator;
        this.orgTypeValidator = orgTypeValidator;
        this.superiorValidator = superiorValidator;
        this.orgNameValidator = orgNameValidator;
        this.orgLeaderValidator = orgLeaderValidator;
    }

    public OrgBuilder tenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    public OrgBuilder superiorId(Long superiorId) {
        this.superiorId = superiorId;
        return this;
    }

    public OrgBuilder orgTypeCode(String orgTypeCode) {
        this.orgTypeCode = orgTypeCode;
        return this;
    }

    public OrgBuilder leaderId(Long leaderId) {
        this.leaderId = leaderId;
        return this;
    }

    public OrgBuilder name(String name) {
        this.name = name;
        return this;
    }

    public OrgBuilder createdBy(Long createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Org build() {
        validate();
        Org org = new Org();
        org.setOrgTypeCode(this.orgTypeCode);
        org.setLeaderId(this.leaderId);
        org.setName(this.name);
        org.setSuperiorId(this.superiorId);
        org.setTenantId(this.tenantId);
        org.setCreatedBy(this.createdBy);
        org.setCreatedAt(LocalDateTime.now());
        return org;
    }

    private void validate() {
        tenantValidator.tenantShouldValid(tenantId);
        orgLeaderValidator.leaderShouldBeEffective(tenantId, leaderId);
        orgTypeValidator.verify(tenantId, orgTypeCode);
        superiorValidator.verify(tenantId, superiorId, orgTypeCode);
        orgNameValidator.verify(tenantId, name, superiorId);
    }
}
