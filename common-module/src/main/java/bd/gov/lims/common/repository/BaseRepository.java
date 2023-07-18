package bd.gov.lims.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, S>  extends JpaSpecificationExecutor<T>, JpaRepository<T, S> {

}
