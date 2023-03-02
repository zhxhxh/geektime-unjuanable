package com.so_incredible.unjuanable.adapter.driving.persistence.tenantmng;

import com.so_incredible.unjuanable.domain.tenantmng.ITenantRepository;
import com.so_incredible.unjuanable.domain.tenantmng.TenantStatus;
import org.springframework.stereotype.Repository;

@Repository
public class TenantRepositoryJdbc implements ITenantRepository {

    @Override
    public boolean existsByIdAndStatus(Long id, TenantStatus tenantStatus) {
        return false;
    }
}
