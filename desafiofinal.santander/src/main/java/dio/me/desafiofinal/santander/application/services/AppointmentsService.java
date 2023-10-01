package dio.me.desafiofinal.santander.application.services;

import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.GivenDateInvalidException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Appointment;


import java.util.List;

public interface AppointmentsService {
    Appointment create(Appointment Appointment) throws AlreadyExistsException, GivenDateInvalidException;
    Appointment findById(Long id) throws NotFoundException;
    void deleteById(Long id);
    List<Appointment> getAppointments();
}
