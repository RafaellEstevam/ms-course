package com.devsuperior.hrpayroll.resources;

import com.devsuperior.hrpayroll.entities.Payment;
import com.devsuperior.hrpayroll.services.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payments")
public class PaymentRestController {

    @Autowired
    private PaymentService paymentService;

    @HystrixCommand(fallbackMethod = "retrievePaymentAlternative")
    @GetMapping(value = "{workerId}/days/{days}")
    public ResponseEntity<Payment> retrievePayment(@PathVariable Long workerId, @PathVariable Integer days){
        Payment payment = paymentService.getPayment(workerId, days);

        return ResponseEntity.ok(payment);
    }


    public ResponseEntity<Payment> retrievePaymentAlternative(Long workerId, Integer days){

        Payment payment = new Payment("Brann", 45.00, days);

        return ResponseEntity.ok().body(payment);
    }



}
