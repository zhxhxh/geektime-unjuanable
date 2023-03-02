package com.so_incredible.unjuanable.domain.orgmng.org;

import com.so_incredible.unjuanable.domain.orgmng.emp.EmpStatus;

import java.util.Optional;

public interface IOrgRepository {

    Optional<Org> findByIdAndStatus(long tenantId, Long id, OrgStatus status);

    int countBySuperiorAndName(long tenantId, Long superiorId, String name);

    boolean existsBySuperiorAndName(Long tenant, Long superior, String name);

    Org save(Org org);

    boolean existsByIdAndStatus(Long tenantId, Long leaderId, EmpStatus... empStatuses);

    int update(Org org);
}
