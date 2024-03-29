package com.koblev.fraud;

import com.koblev.clients.fraud.FraudCheckResponse;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/fraud-check")
@AllArgsConstructor
@Slf4j
public class FraudController {

    private final FraudCheckService fraudCheckService;

//    public FraudController(FraudCheckService fraudCheckService) {
//        this.fraudCheckService = fraudCheckService;
//    }


    @GetMapping(path = "{customerId}")
    public FraudCheckResponse idFraudster(
            @PathVariable("customerId") Integer customerId) {
        boolean isFraudulentCustomer =  fraudCheckService
                .isFraudulentCustomer(customerId);
        log.info("fraud check request for customer {}", customerId);

        return new FraudCheckResponse(isFraudulentCustomer);
    }
}
