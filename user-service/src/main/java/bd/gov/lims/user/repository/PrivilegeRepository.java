package bd.gov.lims.user.repository;

import bd.gov.lims.common.repository.BaseRepository;
import bd.gov.lims.user.entity.Privilege;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrivilegeRepository extends BaseRepository<Privilege, UUID> {
}
