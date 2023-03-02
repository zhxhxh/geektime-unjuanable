package com.so_incredible.unjuanable.domain.orgmng.org;

import com.so_incredible.unjuanable.domain.orgmng.org.validator.OrgLeaderValidator;
import com.so_incredible.unjuanable.domain.orgmng.org.validator.OrgNameValidator;
import com.so_incredible.unjuanable.domain.orgmng.org.validator.OrgTypeValidator;
import com.so_incredible.unjuanable.domain.orgmng.org.validator.SuperiorValidator;
import com.so_incredible.unjuanable.domain.tenantmng.TenantValidator;
import org.springframework.stereotype.Component;

@Component
public class OrgBuilderFactory {

    private final TenantValidator tenantValidator;
    private final OrgTypeValidator orgTypeValidator;
    private final SuperiorValidator superiorValidator;
    private final OrgNameValidator orgNameValidator;
    private final OrgLeaderValidator orgLeaderValidator;

    public OrgBuilderFactory(TenantValidator tenantValidator,
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

    //每次调用都创建一个新的 OrgBuilder
    public OrgBuilder create() {
        return new OrgBuilder(tenantValidator, orgTypeValidator, superiorValidator, orgNameValidator, orgLeaderValidator);
    }
}
