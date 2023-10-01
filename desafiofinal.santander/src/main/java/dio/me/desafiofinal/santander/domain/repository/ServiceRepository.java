package dio.me.desafiofinal.santander.domain.repository;

import dio.me.desafiofinal.santander.domain.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
}
