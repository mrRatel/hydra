package com.ratel.hydra.common.properties;
public interface ViewUrlProperty {
    String INDEX = "/index";
    String LOGIN = "/login";
    String ROLE_EDIT = "view/system/role/edit";
    String ROLE_ADD = "view/system/role/edit/add";
    String USER_EDIT = "view/system/user/edit";
    String USER_ADD = "view/system/role/user/add";
    String PERMISSION_EDIT = "view/system/permission/edit";
    String PERMISSION_ADD = "view/system/permission/add";

    String UNAUTHORIZED = "/error/403.html";
    String ERROR = "/error/500.html";
}
