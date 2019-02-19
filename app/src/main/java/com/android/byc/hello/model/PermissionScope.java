package com.android.byc.hello.model;

/**
 * @author yu
 * @version 1.0
 * @date 2019/2/19 14:28
 * @description
 */
public enum PermissionScope {
    User("User", 1),
    Group("Group", 2),
    Department("Department", 3),
    Region("Region", 4),
    Company("Company", 5);

    private  String scopeName;
    private int scopeValue;

    PermissionScope(String scopeName, int scopeValue) {
        this.scopeName = scopeName;
        this.scopeValue = scopeValue;
    }

    public String getScopeName() {
        return scopeName;
    }

    public void setScopeName(String scopeName) {
        this.scopeName = scopeName;
    }

    public int getScopeValue() {
        return scopeValue;
    }

    public void setScopeValue(int scopeValue) {
        this.scopeValue = scopeValue;
    }

    public static PermissionScope valueOf(int scopeValue){
        switch (scopeValue){
            case 2:
                return Group;
            case 3:
                return Department;
            case 4:
                return Region;
            case 5:
                return Company;
            default:
                return User;
        }
    }
}
