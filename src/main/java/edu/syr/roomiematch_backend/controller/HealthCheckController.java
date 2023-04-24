package edu.syr.roomiematch_backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.syr.roomiematch_backend.api.HealthCheckApi;
import edu.syr.roomiematch_backend.model.HealthcheckResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class HealthCheckController implements HealthCheckApi {
    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<HealthcheckResponse> healthcheckGet() {
        HealthcheckResponse healthcheckResponse = new HealthcheckResponse();
        healthcheckResponse.setHealthcheck(HealthcheckResponse.HealthcheckEnum.SUCCESS);

        return ResponseEntity.accepted().body(healthcheckResponse);
    }
}
