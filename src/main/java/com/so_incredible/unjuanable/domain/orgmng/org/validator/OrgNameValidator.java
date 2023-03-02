package com.so_incredible.unjuanable.domain.orgmng.org.validator;

import com.so_incredible.unjuanable.common.exception.BusinessException;
import com.so_incredible.unjuanable.domain.orgmng.org.IOrgRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class OrgNameValidator {

    private final IOrgRepository orgRepository;

    public OrgNameValidator(IOrgRepository orgRepository) {
        this.orgRepository = orgRepository;
    }

    // 校验组织名称的规则分组
    public void verify(Long tenantId, String name, Long superiorId) {
        orgNameShouldNotEmpty(name);
        nameShouldNotDuplicatedInSameSuperior(tenantId, superiorId, name);
    }

    // 组织必须有名称
    private void orgNameShouldNotEmpty(String name) {
        if (StringUtils.hasLength(name)) {
            throw new BusinessException("组织没有名称！");
        }
    }

    // 同一个组织下的下级组织不能重名
    private void nameShouldNotDuplicatedInSameSuperior(Long tenantId, Long superiorId, String name) {
        if (orgRepository.existsBySuperiorAndName(tenantId, superiorId, name)) {
            throw new BusinessException("同一上级下已经有名为'" + name + "'的组织存在！");
        }
    }

}
