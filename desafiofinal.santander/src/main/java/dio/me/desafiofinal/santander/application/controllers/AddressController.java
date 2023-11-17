package dio.me.desafiofinal.santander.application.controllers;

import dio.me.desafiofinal.santander.application.requests.AddressRequest;
import dio.me.desafiofinal.santander.application.services.AddressService;
import dio.me.desafiofinal.santander.application.services.BarberService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Address;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressService addressService;
    private final BarberService barberService;

    public AddressController(AddressService addressService, BarberService barberService) {
        this.addressService = addressService;
        this.barberService = barberService;
    }

    @PostMapping("")
    public ResponseEntity<Address> create(@RequestBody @Valid AddressRequest addressRequest)
            throws NotFoundException, AlreadyExistsException {
        var barber = this.barberService.findById(addressRequest.barberID());
        var address = AddressRequest.toAddress(barber, addressRequest);
        address = this.addressService.create(address);
        return ResponseEntity.created(URI.create("/address" + address.getId())).body(address);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> findById(@PathVariable("id") Long id) throws NotFoundException{
        return ResponseEntity.ok(this.addressService.findById(id));
    }
}
