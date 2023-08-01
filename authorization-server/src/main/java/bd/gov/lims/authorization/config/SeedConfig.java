package bd.gov.lims.authorization.config;

import bd.gov.lims.authorization.entity.AppUser;
import bd.gov.lims.authorization.entity.Privilege;
import bd.gov.lims.authorization.entity.Role;
import bd.gov.lims.authorization.mapper.AppUserMapper;
import bd.gov.lims.authorization.mapper.PrivilegeMapper;
import bd.gov.lims.authorization.mapper.RoleMapper;
import bd.gov.lims.authorization.repository.AppUserRepository;
import bd.gov.lims.authorization.repository.PrivilegeRepository;
import bd.gov.lims.authorization.repository.RoleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Configuration
@Slf4j
public class
SeedConfig {
    @Bean
    public CommandLineRunner run(PasswordEncoder passwordEncoder,
                                 AppUserMapper appUserMapper,
                                 RoleMapper roleMapper,
                                 RoleRepository roleRepository,
                                 AppUserRepository appUserRepository,
                                 PrivilegeRepository privilegeRepository,
                                 PrivilegeMapper privilegeMapper) {
        return (String[] args) -> {
            try {
                createPrivileges(privilegeRepository, privilegeMapper);
                createRoles(roleRepository, roleMapper);
                assignPrivilegesToRoles(roleRepository, privilegeRepository);
                createUsers(passwordEncoder, appUserRepository, appUserMapper);
                assignRolesToUser(appUserRepository, roleRepository);
            } catch (Exception e) {}
        };
    }

    private void assignRolesToUser(AppUserRepository appUserRepository, RoleRepository roleRepository) {
        try {
            InputStream userRoleStream = new ClassPathResource("seed/user-roles.json",
                    this.getClass().getClassLoader()).getInputStream();
            String text = new String(userRoleStream.readAllBytes());
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String>[] userRoleJson = mapper.readValue(text, Map[].class);

            Map<String, AppUser> appUserMap = new Hashtable<>();
            Optional<AppUser> optionalAdminUser = appUserRepository.findByUsername("admin");
            if (optionalAdminUser.isPresent()) {
                appUserMap.put(optionalAdminUser.get().getUsername(), optionalAdminUser.get());
            }
            Optional<AppUser> optionalSuperAdminUser = appUserRepository.findByUsername("superadmin");
            if (optionalSuperAdminUser.isPresent()) {
                appUserMap.put(optionalSuperAdminUser.get().getUsername(), optionalSuperAdminUser.get());
            }
            Optional<AppUser> optionalUser = appUserRepository.findByUsername("user");
            if (optionalUser.isPresent()) {
                appUserMap.put(optionalUser.get().getUsername(), optionalUser.get());
            }

            List<Role> roleList = roleRepository.findAll();
            Map<String, Role> roleMap = new HashMap<>();
            roleList.stream().forEach(role -> roleMap.put(role.getName(), role));

            for (Map<String,String> map : userRoleJson) {
                try {
                    String[] roles = map.get("roles").split(",");
                    Set<Role> roleSet = new HashSet<>();
                    Arrays.stream(roles).forEach(role -> roleSet.add(roleMap.get(role)));

                    AppUser appUser = appUserMap.get(map.get("username"));
                    if (appUser.getRoles().size() == 0) {
                        appUser.setRoles(roleSet);
                        appUserRepository.save(appUser);
                    }
                } catch (Exception e) {}
            }
        } catch (Exception e) {}
    }

    private void createUsers(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository, AppUserMapper appUserMapper) {
        try {
            InputStream usersStream = new ClassPathResource("seed/users.json",
                    this.getClass().getClassLoader()).getInputStream();
            String text = new String(usersStream.readAllBytes());
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String>[] usersJson = mapper.readValue(text, Map[].class);
            Map<String, AppUser> appUserMap = new Hashtable<>();

            Optional<AppUser> optionalAdminUser = appUserRepository.findByUsername("admin");
            if (optionalAdminUser.isPresent()) {
                appUserMap.put(optionalAdminUser.get().getUsername(), optionalAdminUser.get());
            }
            Optional<AppUser> optionalSuperAdminUser = appUserRepository.findByUsername("superadmin");
            if (optionalSuperAdminUser.isPresent()) {
                appUserMap.put(optionalSuperAdminUser.get().getUsername(), optionalSuperAdminUser.get());
            }
            Optional<AppUser> optionalUser = appUserRepository.findByUsername("user");
            if (optionalUser.isPresent()) {
                appUserMap.put(optionalUser.get().getUsername(), optionalUser.get());
            }

            for (Map<String,String> map : usersJson) {
                try {
                    if(!appUserMap.containsKey(map.get("username"))) {
                        AppUser appUser = AppUser.builder()
                                .phone(map.get("phone"))
                                .email(map.get("email"))
                                .password(passwordEncoder.encode(map.get("password")))
                                .username(map.get("username"))
                                .build();
                        appUserRepository.save(appUser);
                    }
                } catch (Exception e) {}
            }

        } catch (Exception e) {
            log.info(e.toString());
            throw new RuntimeException(e);
        }
    }

    private void assignPrivilegesToRoles(RoleRepository roleRepository, PrivilegeRepository privilegeRepository) {
        try {
            InputStream rolesStream = new ClassPathResource("seed/role-privileges.json",
                    this.getClass().getClassLoader()).getInputStream();
            String text = new String(rolesStream.readAllBytes());
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String>[] rolePrivilegesJson = mapper.readValue(text, Map[].class);

            List<Role> roleList = roleRepository.findAll();
            Map<String, Role> roleMap = new HashMap<>();
            roleList.stream().forEach(role -> roleMap.put(role.getName(), role));

            List<Privilege> privilegeList = privilegeRepository.findAll();
            Map<String, Privilege> privilageMap = new HashMap<>();
            privilegeList.stream().forEach(privilege -> privilageMap.put(privilege.getName(), privilege));

            for (Map<String, String> map : rolePrivilegesJson) {
                try {
                    Role role = roleMap.get(map.get("role"));
                    String[] privileges = map.get("privileges").split(",");
                    Set<Privilege> privilegeSet = new HashSet<>();
                    for (String privilege : privileges) {
                        privilegeSet.add(privilageMap.get(privilege));
                    }
                    if (isNotEqual(role.getPrivileges(), privilegeSet)) {
                        role.setPrivileges(privilegeSet);
                        roleRepository.save(role);
                    }
                } catch (Exception e) {}
            }

        } catch (Exception e) {
            log.info(e.toString());
        }
    }

    private boolean isNotEqual(Set<Privilege> firstSet, Set<Privilege> secondSet) {
        return !firstSet.equals(secondSet);
    }

    private void createRoles(RoleRepository roleRepository, RoleMapper roleMapper) throws IOException {
        InputStream rolesStream = new ClassPathResource("seed/roles.json",
                this.getClass().getClassLoader()).getInputStream();
        String text = new String(rolesStream.readAllBytes());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String>[] rolesJson = mapper.readValue(text, Map[].class);
        List<Role> roleList = roleRepository.findAll();
        Map<String, Role> roleMap = new Hashtable<>();
        roleList.stream().forEach(role -> {
            roleMap.put(role.getName(), role);
        });

        for (Map<String,String> map : rolesJson) {
            try {
                if(!roleMap.containsKey(map.get("NAME"))) {
                    Role role = Role.builder()
                            .name(map.get("NAME"))
                            .display(map.get("DISPLAY"))
                            .priority(Integer.parseInt(map.get("PRIORITY")))
                            .build();
                    roleRepository.save(role);
                }
            } catch (Exception e)  {}
        }

    }

    private void createPrivileges(PrivilegeRepository privilegeRepository,
                                  PrivilegeMapper privilegeMapper) throws IOException {
        InputStream privilegeStream = new ClassPathResource("seed/privilege.json",
                this.getClass().getClassLoader()).getInputStream();
        String text = new String(privilegeStream.readAllBytes());
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String>[] privilegeJson = mapper.readValue(text, Map[].class);
        Arrays.stream(privilegeJson).forEach(ma ->System.out.print(ma.get("NAME")+","));
        List<Privilege> privilegeList = privilegeRepository.findAll();
        Map<String, Privilege> privilegeMap = new Hashtable<>();
        privilegeList.stream().forEach(privilege -> {
            privilegeMap.put(privilege.getName(), privilege);
        });

        for (Map<String,String> map : privilegeJson) {
            try {
                if(!privilegeMap.containsKey(map.get("NAME"))) {
                    Privilege privilege = Privilege.builder()
                            .name(map.get("NAME"))
                            .display(map.get("DISPLAY"))
                            .module(map.get("MODULE"))
                            .subModule(map.get("SUBMODULE"))
                            .build();
                    privilegeRepository.save(privilege);
                }
            } catch (Exception e) {
                log.info(e.toString());
            }
        }
    }




}
