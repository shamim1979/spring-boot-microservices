package bd.gov.lims.authorization.repository;

import bd.gov.lims.authorization.entity.Privilege;
import bd.gov.lims.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrivilegeRepository extends BaseRepository<Privilege, UUID> {
}
