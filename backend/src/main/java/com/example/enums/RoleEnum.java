package com.example.enums;

public enum RoleEnum {
    COMMON(0, "普通用户"),
    ADMIN(1, "管理员"),
    STAFF(2, "客服"),
    ;
    private final Integer id;
    private final String roleName;

    RoleEnum(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public Integer getId() {
        return id;
    }

    public static RoleEnum getById(Integer id) {
        for (RoleEnum e : RoleEnum.values()) {
            if (e.id.equals(id)) {
                return e;
            }
        }
        return null;
    }

    public static RoleEnum getByRoleName(String roleName) {
        for (RoleEnum e : RoleEnum.values()) {
            if (e.roleName.equals(roleName)) {
                return e;
            }
        }
        return null;
    }

    public static final String column = "rolename";//table role column rolename

}
