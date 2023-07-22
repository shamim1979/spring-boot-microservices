package bd.gov.lims.user.repository;

import bd.gov.lims.common.repository.BaseRepository;
import bd.gov.lims.user.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role, UUID> {
}
