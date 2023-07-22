package bd.gov.lims.user.service.impl;

import bd.gov.lims.user.dto.RoleDto;
import bd.gov.lims.user.entity.Role;
import bd.gov.lims.user.mapper.RoleMapper;
import bd.gov.lims.user.param.RoleParam;
import bd.gov.lims.user.repository.RoleRepository;
import bd.gov.lims.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceIml implements RoleService<RoleParam, Role, RoleDto> {
    private final RoleMapper roleMapper;
    private final RoleRepository roleRepository;
    @Autowired
    RoleServiceIml(RoleMapper roleMapper, RoleRepository roleRepository) {
        this.roleMapper = roleMapper;
        this.roleRepository = roleRepository;
    }
    @Override
    public Page<RoleDto> findAll(Specification<Role> specification, Pageable pageable) {
        Page<Role> rolePage = roleRepository.findAll(specification,pageable);
        return rolePage.map(role -> roleMapper.entityToDto(role));
    }

    @Override
    public List<RoleDto> findAll(Specification<Role> specification, Sort sort) {
        List<Role> roleList = roleRepository.findAll(specification, sort);
        return roleList.stream()
                .map(role -> roleMapper.entityToDto(role))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RoleDto> findById(UUID id) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        return Optional.ofNullable(roleMapper.entityToDto(optionalRole.orElse(null)));
    }

    @Override
    public Optional<RoleDto> save(RoleParam param) {
        Role role = roleMapper.paramToEntity(param);
        role = roleRepository.save(role);
        return Optional.ofNullable(roleMapper.entityToDto(role));
    }

    @Override
    public Optional<RoleDto> update(UUID id, RoleParam param) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        Role role = optionalRole.orElse(null);
        roleMapper.paramToEntity(param, role);
        role = roleRepository.save(role);
        return Optional.ofNullable(roleMapper.entityToDto(role));
    }

    @Override
    public void deleteById(UUID id) {
        roleRepository.deleteById(id);
    }
}
