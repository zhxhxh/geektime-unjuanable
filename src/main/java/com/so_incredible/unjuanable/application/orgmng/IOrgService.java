package com.so_incredible.unjuanable.application.orgmng;

public interface IOrgService {

    OrgDto addOrg(OrgDto request, Long userId);

    OrgDto updateOrgBasic(Long id, OrgDto request, Long userId);

    Long cancelOrg(Long id, Long tenant, Long userId);

}
