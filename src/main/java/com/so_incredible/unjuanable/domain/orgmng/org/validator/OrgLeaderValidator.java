package com.so_incredible.unjuanable.domain.orgmng.org.validator;

import com.so_incredible.unjuanable.common.exception.BusinessException;
import com.so_incredible.unjuanable.domain.orgmng.emp.EmpStatus;
import com.so_incredible.unjuanable.domain.orgmng.org.IOrgRepository;
import org.springframework.stereotype.Component;

@Component
public class OrgLeaderValidator {

    private final IOrgRepository empRepository;

    public OrgLeaderValidator(IOrgRepository empRepository) {
        this.empRepository = empRepository;
    }

    // 组织负责人可以空缺, 如果有的话, 必须是一个在职员工 (含试用期)
    public void leaderShouldBeEffective(Long tenantId, Long leaderId) {
        if (leaderId != null && !empRepository.existsByIdAndStatus(tenantId, leaderId, EmpStatus.REGULAR, EmpStatus.PROBATION)) {
            throw new BusinessException("组织负责人(id='" + leaderId + "')不是在职员工！");
        }
    }

}
