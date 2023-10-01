package dio.me.desafiofinal.santander.application.controllers;

import dio.me.desafiofinal.santander.application.requests.CustomerRequest;
import dio.me.desafiofinal.santander.application.services.CustomerService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Appointment;
import dio.me.desafiofinal.santander.domain.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping(path = "/customer")

public class CustomerController {
    static final String PATH = "/customer";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    ResponseEntity<List<Customer>> getCustomers() {
        return ResponseEntity.ok(this.customerService.getCustomers());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    ResponseEntity<Customer> create(@RequestBody @Valid CustomerRequest customerRequest) throws AlreadyExistsException  {
        var customer = this.customerService.create(CustomerRequest.toCustumer(customerRequest));
        return ResponseEntity
                .created(URI.create(PATH + "/" + customer.getId()))
                .body(customer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") Long id) {
        this.customerService.deleteById(id);
    }

    @GetMapping("/{id}")
    ResponseEntity<Customer> findById(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(this.customerService.findById(id));
    }


}
