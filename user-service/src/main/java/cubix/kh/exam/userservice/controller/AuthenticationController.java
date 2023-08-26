package cubix.kh.exam.userservice.controller;

import cubix.kh.exam.userservice.dto.AuthenticationRequestDTO;
import cubix.kh.exam.userservice.dto.AuthenticationResponseDTO;
import cubix.kh.exam.userservice.dto.RegisterUserRequestDTO;
import cubix.kh.exam.userservice.service.AuthenticationService;
import cubix.kh.exam.userservice.service.RegisterService;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/authentication")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponseDTO> authenticate(
            @RequestBody
            @Valid
            final AuthenticationRequestDTO request
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                             .body(authenticationService.authenticate(request));
    }
}
