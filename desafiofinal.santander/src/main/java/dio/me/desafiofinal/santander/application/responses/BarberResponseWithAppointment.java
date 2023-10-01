package dio.me.desafiofinal.santander.application.responses;

import dio.me.desafiofinal.santander.domain.model.Appointment;

import java.util.List;

public record BarberResponseWithAppointment(
        Long id,
        String name,
        String email,
        String phone,
        List<Appointment> appointments
)  {
}
