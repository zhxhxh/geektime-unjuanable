package com.so_incredible.unjuanable.domain.orgmng.org;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Org {
    private Long id;
    private Long tenantId;
    private Long superiorId;
    private String orgTypeCode;
    private Long leaderId;
    private String name;
    // 使用了枚举类型
    private OrgStatus status;
    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime lastUpdatedAt;
    private Long lastUpdatedBy;

    public Org() {
        //组织的初始状态默认为有效
        status = OrgStatus.EFFECTIVE;
    }
}
