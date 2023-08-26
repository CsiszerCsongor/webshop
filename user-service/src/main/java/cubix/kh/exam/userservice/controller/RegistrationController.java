package cubix.kh.exam.userservice.controller;

import cubix.kh.exam.userservice.dto.RegisterUserRequestDTO;
import cubix.kh.exam.userservice.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegistrationController {
    private final RegisterService registerService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(
            @RequestBody
            @Valid
            final RegisterUserRequestDTO request
    ) {
        registerService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
