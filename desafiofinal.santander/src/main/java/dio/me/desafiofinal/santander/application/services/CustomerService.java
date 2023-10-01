package dio.me.desafiofinal.santander.application.services;

import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Appointment;
import dio.me.desafiofinal.santander.domain.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

public interface CustomerService {
    Customer create(Customer customer) throws AlreadyExistsException;
    Customer findById(Long id) throws NotFoundException;
    void deleteById(Long id);
    List<Customer> getCustomers();
}
