package com.so_incredible.unjuanable.domain.orgmng.org;

import com.so_incredible.unjuanable.application.orgmng.OrgDto;
import org.springframework.stereotype.Component;

@Component
@Deprecated
public class OrgFactory {

    private final OrgValidator validator;

    public OrgFactory(OrgValidator validator) {
        this.validator = validator;
    }

    public Org build(OrgDto request, Long userId) {
        validator.validate(request);
        return buildOrg(request, userId);
    }

    private Org buildOrg(OrgDto request, Long useId) {
        // 将DTO的值赋给领域对象...
        return new Org();
    }
}
