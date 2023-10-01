package dio.me.desafiofinal.santander.application.responses;

import dio.me.desafiofinal.santander.domain.model.Appointment;

public record BarberResponseWithCount(
        Long id,
        String name,
        String phone,
        String email,
        Long appointments
) {
}
