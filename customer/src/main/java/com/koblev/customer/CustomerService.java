package com.koblev.customer;


import com.koblev.clients.fraud.FraudCheckResponse;
import com.koblev.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraoudClient;
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo: check validation and store in db
        customerRepository.saveAndFlush(customer);
        //todo: check if fraudster
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
//                "http://FRAUD/api/v1/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId()
//        );
//
//       instead of all of this (above) ==> we just call next:
        FraudCheckResponse fraudCheckResponse =
                fraoudClient.idFraudster(customer.getId());

        assert fraudCheckResponse != null;
        if (fraudCheckResponse.isFraudster()){
            throw new IllegalStateException("fraudster");
        }

        //todo: send notification
    }
}
