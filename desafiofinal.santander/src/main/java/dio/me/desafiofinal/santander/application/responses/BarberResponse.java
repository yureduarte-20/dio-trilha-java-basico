package dio.me.desafiofinal.santander.application.responses;

import dio.me.desafiofinal.santander.domain.model.Appointment;

import java.util.List;
import java.util.Optional;

public record BarberResponse(
        Long id,
        String name,
        String phone,
        String email
) {
}
