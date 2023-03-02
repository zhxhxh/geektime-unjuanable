package com.so_incredible.unjuanable.domain.tenantmng;

public interface ITenantRepository {

    boolean existsByIdAndStatus(Long id, TenantStatus tenantStatus);

}
