package com.so_incredible.unjuanable.domain.tenantmng;

import com.so_incredible.unjuanable.common.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class TenantValidator {

    private final ITenantRepository tenantRepository;

    public TenantValidator(ITenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    // 租户必须有效
    public void tenantShouldValid(Long tenantId) {
        if (!tenantRepository.existsByIdAndStatus(tenantId, TenantStatus.EFFECTIVE)) {
            throw new BusinessException("id为'" + tenantId + "'的租户不是有效租户！");
        }
    }

}
