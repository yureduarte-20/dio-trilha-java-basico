package dio.me.desafiofinal.santander.application.controllers;

import dio.me.desafiofinal.santander.application.requests.BarberRequest;
import dio.me.desafiofinal.santander.application.responses.BarberResponse;
import dio.me.desafiofinal.santander.application.responses.BarberResponseWithAppointment;
import dio.me.desafiofinal.santander.application.responses.BarberResponseWithCount;
import dio.me.desafiofinal.santander.application.services.BarberService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Barber;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/barber")
public class BarberController {
    private final BarberService barberService;
    public BarberController(BarberService barberService) {
        this.barberService = barberService;
    }
    @GetMapping
    ResponseEntity<?> findBarbers(){
        return ResponseEntity.ok(this.barberService.getBarbers()) ;
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
        try{
            if(withAppointment != null && withAppointment.equalsIgnoreCase("true")){
                var barber = this.barberService.findById(id, true);
                return ResponseEntity.ok(new BarberResponseWithAppointment(barber.getId(), barber.getName(), barber.getEmail(), barber.getPhone(),
                        barber.getAppointments()));
            }
            var barber = this.barberService.findById(id, false);
            return ResponseEntity.ok(new BarberResponse(barber.getId(), barber.getName(), barber.getEmail(), barber.getPhone() ));

        } catch (NoSuchElementException ignored){
            return ResponseEntity.notFound().build();
        }

    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id){
        this.barberService.deleteById(id);
    }
}
