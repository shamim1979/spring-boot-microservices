package bd.gov.lims.authorization.repository;

import bd.gov.lims.authorization.entity.Role;
import bd.gov.lims.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role, UUID> {
}
