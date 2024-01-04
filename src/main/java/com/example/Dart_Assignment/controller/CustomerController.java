import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController<CustomerRepository> {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public Iterable<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer updatedCustomer) {
        Customer customer = customerRepository.findById(id).orElse(null);

        if (customer != null) {
            customer.setName(updatedCustomer.getName());
            customer.setAge(updatedCustomer.getAge());
            customer.setAddress(updatedCustomer.getAddress());
            return customerRepository.save(customer);
        }
//a
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerRepository.deleteById(id);
    }
}
