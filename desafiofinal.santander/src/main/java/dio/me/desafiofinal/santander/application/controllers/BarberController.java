package dio.me.desafiofinal.santander.application.controllers;

import dio.me.desafiofinal.santander.application.requests.BarberRequest;
import dio.me.desafiofinal.santander.application.responses.BarberResponse;
import dio.me.desafiofinal.santander.application.services.BarberService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Barber;
import dio.me.desafiofinal.santander.domain.repository.BarberRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/barber")
public class BarberController {
    private final BarberService barberService;
    private final BarberRepository barberRepository;

    public BarberController(BarberService barberService, BarberRepository barberRepository) {
        this.barberService = barberService;
        this.barberRepository = barberRepository;
    }

    @GetMapping
    ResponseEntity<?> findBarbers() {
        return ResponseEntity.ok(this.barberService.getBarbers());
    }

    @PostMapping
    ResponseEntity<Barber> createBarber(@RequestBody @Valid BarberRequest barberRequest) throws AlreadyExistsException {
        var barber = BarberRequest.toBarber(barberRequest);
        barber = this.barberService.create(barber);
        return ResponseEntity.ok(barber);
    }

    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity<?> findById(@PathVariable Long id, @RequestParam(name = "appointments", required = false) String withAppointment)
            throws NotFoundException {
        if (withAppointment != null && withAppointment.equalsIgnoreCase("true")) {
            var barber = this.barberService.findByIdWithAppointments(id);
            return ResponseEntity.ok(barber);
        }
        var barber = this.barberService.findById(id);
        return ResponseEntity.ok(new BarberResponse(barber.getId(), barber.getName(), barber.getEmail(), barber.getPhone()));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id) {
        this.barberService.deleteById(id);
    }
}
