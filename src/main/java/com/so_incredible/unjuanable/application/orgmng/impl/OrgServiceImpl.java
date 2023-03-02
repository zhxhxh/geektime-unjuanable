package com.so_incredible.unjuanable.application.orgmng.impl;

import com.so_incredible.unjuanable.application.orgmng.IOrgService;
import com.so_incredible.unjuanable.application.orgmng.OrgDto;
import com.so_incredible.unjuanable.common.exception.BusinessException;
import com.so_incredible.unjuanable.domain.orgmng.org.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrgServiceImpl implements IOrgService {

    private final OrgBuilderFactory orgBuilderFactory;
    private final IOrgRepository orgRepository;
    private final OrgHandler orgHandler;

    public OrgServiceImpl(OrgBuilderFactory orgBuilderFactory,
                          IOrgRepository orgRepository,
                          OrgHandler orgHandler) {
        this.orgBuilderFactory = orgBuilderFactory;
        this.orgRepository = orgRepository;
        this.orgHandler = orgHandler;
    }


    @Override
    public OrgDto addOrg(OrgDto request, Long userId) {
        OrgBuilder orgBuilder = orgBuilderFactory.create();
        Org org = orgBuilder
                .orgTypeCode(request.getOrgTypeCode())
                .leaderId(request.getLeaderId())
                .superiorId(request.getSuperiorId())
                .name(request.getName())
                .createdBy(userId)
                .build();

        org = orgRepository.save(org);
        return buildOrgDto(org);
    }

    // 修改组织基本信息
    @Override
    @Transactional
    public OrgDto updateOrgBasic(Long id, OrgDto request, Long userId) {
        Org org = orgRepository.findByIdAndStatus(request.getTenantId(), id, OrgStatus.EFFECTIVE)
                .orElseThrow(() -> {
                    throw new BusinessException("要修改的组织(id =" + id + " )不存在！");
                });

        orgHandler.updateBasic(org, request.getName(), request.getLeaderId(), userId);
        orgRepository.update(org);
        return buildOrgDto(org);
    }

    private OrgDto buildOrgDto(Org org) {
        // 将领域对象的值赋给DTO...
        return new OrgDto();
    }

    // 取消组织
    @Override
    public Long cancelOrg(Long id, Long tenantId, Long userId) {
        Org org = orgRepository.findByIdAndStatus(tenantId, id, OrgStatus.EFFECTIVE)
                .orElseThrow(() -> {
                    throw new BusinessException("要取消的组织(id =" + id + " )不存在！");
                });
        orgHandler.cancel(org, userId);
        orgRepository.update(org);
        return org.getId();
    }
}
