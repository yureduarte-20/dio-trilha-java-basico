package dio.me.desafiofinal.santander.application.services.implementations;

import dio.me.desafiofinal.santander.application.services.CustomerService;
import dio.me.desafiofinal.santander.application.services.exceptions.AlreadyExistsException;
import dio.me.desafiofinal.santander.application.services.exceptions.NotFoundException;
import dio.me.desafiofinal.santander.domain.model.Customer;
import dio.me.desafiofinal.santander.domain.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerServiceConcrete implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerServiceConcrete(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) throws AlreadyExistsException {
        if (customer.getId() != null && customerRepository.existsById(customer.getId())) {
            throw new AlreadyExistsException(Customer.class.getName(),"ID Já cadastrado.");
        }
        return this.customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) throws NotFoundException {
        var customer = this.customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Customer.class.getName(), "Cliente Não encontrado"));
        customer.getAppointments().forEach(appointment -> {
            System.out.println(appointment.getDate());
        });
        return customer;
    }

    @Override
    public void deleteById(Long id) {
        this.customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return this.customerRepository.findAll();
    }
}
