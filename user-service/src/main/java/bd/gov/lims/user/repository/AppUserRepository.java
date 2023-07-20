package bd.gov.lims.user.repository;

import bd.gov.lims.common.repository.BaseRepository;
import bd.gov.lims.user.entity.AppUser;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppUserRepository extends BaseRepository<AppUser, UUID> {
}
