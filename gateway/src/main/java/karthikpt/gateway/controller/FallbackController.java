package karthikpt.gateway.controller;

import karthikpt.gateway.dto.GatewayError;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {

    @RequestMapping("/gatewayError")
    public ResponseEntity<GatewayError> sendError()
    {
        GatewayError gatewayError = new GatewayError("failed","Circuit Breaker in Action, please retry later");

        return ResponseEntity
                .status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(gatewayError);

    }

}
