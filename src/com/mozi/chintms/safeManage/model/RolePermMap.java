package com.mozi.chintms.safeManage.model;

import com.mozi.chintms.common.BaseDomain;

@SuppressWarnings("serial")
public class RolePermMap extends BaseDomain{
    private String roleId;

    private String permId;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getPermId() {
        return permId;
    }

    public void setPermId(String permId) {
        this.permId = permId == null ? null : permId.trim();
    }
}