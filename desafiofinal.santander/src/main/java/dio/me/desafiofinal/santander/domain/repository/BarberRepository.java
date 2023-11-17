package dio.me.desafiofinal.santander.domain.repository;

import dio.me.desafiofinal.santander.domain.model.Barber;
import dio.me.desafiofinal.santander.domain.dto.BarberWithAppointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {
    @Override
    Optional<Barber> findById(Long aLong);
    @Query(nativeQuery = true, value = " SELECT barbers.*, addresses , appointments FROM barbers " +
            "LEFT JOIN  addresses ON addresses.barber_id = barbers.id " +
            "LEFT JOIN  appointments ON appointments.barber_id = barbers.id " +
            "WHERE barbers.id = :id")
    Optional<BarberWithAppointments> searchUsingAppointments(@Param("id") Long id);
    @Query(nativeQuery = true)
    List<BarberWithAppointments> findBarberWithAppointmentsCount();
}
