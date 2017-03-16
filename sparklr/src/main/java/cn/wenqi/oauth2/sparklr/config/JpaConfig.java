package cn.wenqi.oauth2.sparklr.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author wenqi
 */
@Configuration
@ImportResource({"classpath:spring_db_prod.xml","classpath:spring_db_dev.xml"})
@EnableJpaRepositories(basePackages = {"cn.wenqi.oauth2.repository"})
public class JpaConfig {
}
