package dio.me.desafiofinal.santander.application.services;

import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Barber;
import dio.me.desafiofinal.santander.domain.dto.BarberWithAppointments;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface BarberService {
    Barber create(Barber barber) throws AlreadyExistsException;
    Barber findById(Long id) throws NotFoundException;
    BarberWithAppointments findByIdWithAppointments(Long id) throws NotFoundException;
    void deleteById(Long id);

    List<BarberWithAppointments> getBarbers();
}
