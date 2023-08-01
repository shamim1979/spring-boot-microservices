package bd.gov.lims.authorization.service.impl;

import bd.gov.lims.authorization.dto.PrivilegeDto;
import bd.gov.lims.authorization.entity.Privilege;
import bd.gov.lims.authorization.mapper.PrivilegeMapper;
import bd.gov.lims.authorization.param.PrivilegeParam;
import bd.gov.lims.authorization.repository.PrivilegeRepository;
import bd.gov.lims.authorization.service.PrivilegeService;
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
public class PrivilegeServiceImpl implements PrivilegeService<PrivilegeParam, Privilege, PrivilegeDto> {
    private final PrivilegeRepository privilegeRepository;
    private final PrivilegeMapper privilegeMapper;
    @Autowired
    PrivilegeServiceImpl(PrivilegeRepository privilegeRepository, PrivilegeMapper privilegeMapper) {
        this.privilegeMapper = privilegeMapper;
        this.privilegeRepository = privilegeRepository;
    }
    @Override
    public Page<PrivilegeDto> findAll(Specification<Privilege> specification, Pageable pageable) {
        Page<Privilege> privilegePage = privilegeRepository.findAll(specification, pageable);
        return privilegePage.map(privilege -> privilegeMapper.entityToDto(privilege));
    }
    @Override
    public List<PrivilegeDto> findAll(Specification<Privilege> specification, Sort sort) {
        List<Privilege> privilegeList = privilegeRepository.findAll(specification, sort);
        return privilegeList.stream()
                .map(privilege -> privilegeMapper.entityToDto(privilege))
                .collect(Collectors.toList());
    }
    @Override
    public Optional<PrivilegeDto> findById(UUID id) {
        Optional<Privilege> optionalPrivilege = privilegeRepository.findById(id);
        return Optional.ofNullable(privilegeMapper.entityToDto(optionalPrivilege.orElse(null)));
    }
    @Override
    public Optional<PrivilegeDto> save(PrivilegeParam param) {
        Privilege privilege = privilegeMapper.paramToEntity(param);
        privilege = privilegeRepository.save(privilege);
        return Optional.ofNullable(privilegeMapper.entityToDto(privilege));
    }
    @Override
    public Optional<PrivilegeDto> update(UUID id, PrivilegeParam param) {
        Optional<Privilege> privilegeOptional = privilegeRepository.findById(id);
        Privilege privilege = privilegeOptional.orElse(null);
        privilegeMapper.paramToEntity(param, privilege);
        return Optional.ofNullable(privilegeMapper.entityToDto(privilege));
    }
    @Override
    public void deleteById(UUID id) {
        privilegeRepository.deleteById(id);
    }
}
