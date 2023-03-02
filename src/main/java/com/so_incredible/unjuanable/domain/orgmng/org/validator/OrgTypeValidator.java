package com.so_incredible.unjuanable.domain.orgmng.org.validator;

import com.so_incredible.unjuanable.common.exception.BusinessException;
import com.so_incredible.unjuanable.domain.orgmng.orgtype.IOrgTypeRepository;
import com.so_incredible.unjuanable.domain.orgmng.orgtype.OrgTypeStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class OrgTypeValidator {

    private final IOrgTypeRepository orgTypeRepository;

    public OrgTypeValidator(IOrgTypeRepository orgTypeRepository) {
        this.orgTypeRepository = orgTypeRepository;
    }

    // 校验组织类别的规则分组
    public void verify(Long tenantId, String orgTypeCode) {
        orgTypeShouldNotEmpty(orgTypeCode);
        orgTypeShouldBeValid(tenantId, orgTypeCode);
        shouldNotCreateEntpAlone(orgTypeCode);
    }

    // 组织类别不能为空
    private void orgTypeShouldNotEmpty(String orgTypeCode) {
        if (StringUtils.hasLength(orgTypeCode)) {
            throw new BusinessException("组织类别不能为空！");
        }
    }

    // 组织类别必须有效
    private void orgTypeShouldBeValid(Long tenantId, String orgTypeCode) {
        if (!orgTypeRepository.existsByCodeAndStatus(tenantId, orgTypeCode, OrgTypeStatus.EFFECTIVE)) {
            throw new BusinessException("'" + orgTypeCode + "'不是有效的组织类别代码！");
        }
    }

    // 企业是在创建租户的时候创建好的，因此不能单独创建企业
    private void shouldNotCreateEntpAlone(String orgTypeCode) {
        if ("ENTP".equals(orgTypeCode)) {
            throw new BusinessException("企业是在创建租户的时候创建好的，因此不能单独创建企业!");
        }
    }

}
