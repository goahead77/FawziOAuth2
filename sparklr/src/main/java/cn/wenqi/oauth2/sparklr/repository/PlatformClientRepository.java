package cn.wenqi.oauth2.sparklr.repository;

import cn.wenqi.oauth2.sparklr.model.PlatformClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author wenqi
 */
@Repository
public interface PlatformClientRepository extends JpaRepository<PlatformClient,Long>,JpaSpecificationExecutor<PlatformClient> {

    PlatformClient findByCid(String cid);
}
