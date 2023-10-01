package dio.me.desafiofinal.santander.application.controllers;

import dio.me.desafiofinal.santander.application.requests.ServiceRequest;
import dio.me.desafiofinal.santander.application.services.ServicesService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Service;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/service")
public class ServicesController {
    final String PATH = "/service";
    private ServicesService servicesService;

    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;

    }
    @GetMapping("")
    List <Service> findAll(){
        return this.servicesService.getServices();
    }
    @GetMapping("/{id}")
    ResponseEntity<Service> findById(@PathVariable("id") Long id) throws NotFoundException {
        return  ResponseEntity.ok(this.servicesService.findById(id));
    }
    @PostMapping("")
    ResponseEntity<Service> create(@RequestBody @Valid ServiceRequest serviceRequest) throws AlreadyExistsException {
        var service = this.servicesService.create( ServiceRequest.toService(serviceRequest) );
        return ResponseEntity.created(URI.create(PATH + "/" +service.getId() )).body(service);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    void deleteById(@PathVariable Long id){
        this.servicesService.deleteById(id);
    }



}
