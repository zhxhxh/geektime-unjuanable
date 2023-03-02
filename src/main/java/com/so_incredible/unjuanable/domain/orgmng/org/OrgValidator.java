package com.so_incredible.unjuanable.domain.orgmng.org;

import com.so_incredible.unjuanable.application.orgmng.OrgDto;
import com.so_incredible.unjuanable.domain.orgmng.org.validator.OrgLeaderValidator;
import com.so_incredible.unjuanable.domain.orgmng.org.validator.OrgNameValidator;
import com.so_incredible.unjuanable.domain.orgmng.org.validator.OrgTypeValidator;
import com.so_incredible.unjuanable.domain.orgmng.org.validator.SuperiorValidator;
import com.so_incredible.unjuanable.domain.tenantmng.TenantValidator;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class OrgValidator {

    private final TenantValidator tenantValidator;
    private final OrgTypeValidator orgTypeValidator;
    private final SuperiorValidator superiorValidator;
    private final OrgNameValidator orgNameValidator;
    private final OrgLeaderValidator orgLeaderValidator;

    public OrgValidator(TenantValidator tenantValidator,
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

    public void validate(OrgDto request) {
        final Long tenant = request.getTenantId();
        tenantValidator.tenantShouldValid(tenant);
        orgLeaderValidator.leaderShouldBeEffective(tenant, request.getLeaderId());
        orgTypeValidator.verify(tenant, request.getOrgTypeCode());
        superiorValidator.verify(tenant, request.getSuperiorId(), request.getOrgTypeCode());
        orgNameValidator.verify(tenant, request.getName(), request.getSuperiorId());
    }
}
