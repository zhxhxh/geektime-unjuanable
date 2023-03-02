package com.so_incredible.unjuanable.domain.orgmng.orgtype;

import java.util.Optional;

public interface IOrgTypeRepository {

    boolean existsByCodeAndStatus(Long tenantId, String orgTypeCode, OrgTypeStatus effective);

    Optional<OrgType> findByCodeAndStatus(Long tenantId, Long superiorId, OrgTypeStatus effective);
}
