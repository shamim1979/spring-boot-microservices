package bd.gov.lims.common.route;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum Module {
    USER(1, "User", true);

    private Integer id;
    private String name;
    private boolean active;

    Module(Integer id, String name, boolean active) {
        this.id = id;
        this.name = name;
        this.active = active;
    }

    public static Module getById(Integer id) {
        for (Module type : Module.values()) {
            if (type.getId() == id) {
                return type;
            }
        }
        return null;
    }

    public static List<Module> getActiveModules() {
        List<Module> modules = new ArrayList<>();
        for (Module type : Module.values()) {
            if (type.isActive()) {
                modules.add(type);
            }
        }
        return modules;
    }

}
