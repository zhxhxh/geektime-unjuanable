package com.so_incredible.unjuanable.domain.orgmng.org.validator;

import com.so_incredible.unjuanable.common.exception.BusinessException;
import com.so_incredible.unjuanable.common.exception.DirtyDataException;
import com.so_incredible.unjuanable.domain.orgmng.org.IOrgRepository;
import com.so_incredible.unjuanable.domain.orgmng.org.Org;
import com.so_incredible.unjuanable.domain.orgmng.org.OrgStatus;
import com.so_incredible.unjuanable.domain.orgmng.orgtype.IOrgTypeRepository;
import com.so_incredible.unjuanable.domain.orgmng.orgtype.OrgType;
import com.so_incredible.unjuanable.domain.orgmng.orgtype.OrgTypeStatus;
import org.springframework.stereotype.Component;

@Component
public class SuperiorValidator {

    private final IOrgTypeRepository orgTypeRepository;
    private final IOrgRepository orgRepository;

    public SuperiorValidator(IOrgTypeRepository orgTypeRepository, IOrgRepository orgRepository) {
        this.orgTypeRepository = orgTypeRepository;
        this.orgRepository = orgRepository;
    }

    // 校验上级组织的规则分组
    public void verify(Long tenantId, Long superiorId, String orgTypeCode) {
        Org superiorOrg = superiorShouldEffective(tenantId, superiorId);
        OrgType superiorOrgType = findSuperiorOrgType(tenantId, superiorId, superiorOrg);
        superiorOfDevGroupMustDevCenter(superiorId, orgTypeCode, superiorOrgType.getCode());
        superiorOfDevCenterAndDirectDeptMustEntp(orgTypeCode, superiorId, superiorOrgType);
    }

    // 上级组织应该是有效组织
    private Org superiorShouldEffective(Long tenantId, Long superiorId) {
        return orgRepository.findByIdAndStatus(tenantId, superiorId, OrgStatus.EFFECTIVE)
                .orElseThrow(() -> new BusinessException("'" + superiorId + "' 不是有效的组织 id !"));
    }

    // 取上级组织的组织类别
    private OrgType findSuperiorOrgType(Long tenantId, Long superiorId, Org superiorOrg) {
        return orgTypeRepository.findByCodeAndStatus(tenantId, superiorId, OrgTypeStatus.EFFECTIVE)
                .orElseThrow(() -> new DirtyDataException("id 为 '" + superiorId + "' 的组织的组织类型代码 '" + superiorOrg.getOrgTypeCode() + "' 无效!"));
    }

    // 开发组的上级只能是开发中心
    private void superiorOfDevGroupMustDevCenter(Long superiorId, String orgTypeCode, String superiorOrgType) {
        if ("DEVGRP".equals(orgTypeCode) && !"DEVCENT".equals(superiorOrgType)) {
            throw new BusinessException("开发组的上级(id = '" + superiorId + "')不是开发中心！");
        }
    }

    // 开发中心和直属部门的上级只能是企业
    private void superiorOfDevCenterAndDirectDeptMustEntp(String orgTypeCode, Long superiorId, OrgType superiorOrgType) {
        if (("DEVCENT".equals(orgTypeCode) || "DIRDEP".equals(orgTypeCode)) && !"ENTP".equals(superiorOrgType.getCode())) {
            throw new BusinessException("开发中心或直属部门的上级(id = '" + superiorId + "')不是企业！");
        }
    }

}
