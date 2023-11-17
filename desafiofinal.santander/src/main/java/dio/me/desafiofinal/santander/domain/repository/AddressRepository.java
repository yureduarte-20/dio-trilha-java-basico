package dio.me.desafiofinal.santander.domain.repository;

import dio.me.desafiofinal.santander.domain.model.Address;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
