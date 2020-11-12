package com.alpha.classpie.pojo.user;

public class RolePermissionKey {
    private Integer roleId;

    private Integer permissionCode;

    public RolePermissionKey(Integer roleId, Integer permissionCode) {
        this.roleId = roleId;
        this.permissionCode = permissionCode;
    }

    public RolePermissionKey() {
        super();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(Integer permissionCode) {
        this.permissionCode = permissionCode;
    }
}
