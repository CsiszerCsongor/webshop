package cubix.kh.exam.userservice.security.service;

import java.util.Date;

public record TokenInformation(String token, Date expirationTime) {

}
