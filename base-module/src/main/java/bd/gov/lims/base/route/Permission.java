package bd.gov.lims.base.route;


import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Permission {

    /* USERS API*/
    USER_ADD("User add", Module.USER),
    USER_VIEW("User view", Module.USER),
    USER_EDIT("User edit", Module.USER),
    USER_VIEW_ALL("User view all", Module.USER),
    USER_DELETE("User delete", Module.USER);

    private String display;
    private Module module;

    Permission(String display, Module module) {
        this.display = display;
        this.module = module;
    }


    public Module getModule() {
        return module;
    }

    public static List<Permission> toList() {
        return Arrays.asList(Permission.values());
    }

    public static List<Permission> findByModule(Module module) {
        return Arrays.asList(Permission.values()).stream()
                .filter(permission -> permission.module.equals(module))
                .collect(Collectors.toList());
    }

    public static String[] getPermissionAsStringArrayOf(Module module) {
        return Arrays.asList(Permission.values()).stream()
                .filter(permission -> permission.module.equals(module))
                .map(p -> new String(p.name()))
                .toArray(String[]::new);
    }

}
