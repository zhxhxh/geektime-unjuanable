package com.so_incredible.unjuanable.domain.orgmng.orgtype;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class OrgType {

    private String code;

    private Long tenantId;

    private String name;

    private LocalDateTime createdAt;
    private Long createdBy;
    private LocalDateTime lastUpdatedAt;
    private Long lastUpdatedBy;

}
