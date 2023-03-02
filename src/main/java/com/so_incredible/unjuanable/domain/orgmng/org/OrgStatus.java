package com.so_incredible.unjuanable.domain.orgmng.org;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrgStatus {

    EFFECTIVE(1, "有效");

    private final int status;

    private final String desc;

}
