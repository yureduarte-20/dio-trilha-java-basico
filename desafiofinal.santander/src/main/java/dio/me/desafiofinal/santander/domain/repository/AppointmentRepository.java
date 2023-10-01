package dio.me.desafiofinal.santander.domain.repository;

import dio.me.desafiofinal.santander.domain.model.Appointment;
import dio.me.desafiofinal.santander.domain.model.Barber;
import dio.me.desafiofinal.santander.domain.model.Customer;
import dio.me.desafiofinal.santander.domain.model.Service;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {




}
