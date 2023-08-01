package bd.gov.lims.authorization.repository;

import bd.gov.lims.authorization.entity.AppUser;
import bd.gov.lims.common.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends BaseRepository<AppUser, UUID> {
    Optional<AppUser> findByUsername(String username);
}
