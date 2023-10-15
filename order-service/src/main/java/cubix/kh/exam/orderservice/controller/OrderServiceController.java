package cubix.kh.exam.orderservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/order")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceController {

    @GetMapping("/first")
    public String getFirstOrder(){
        return "First order";
    }

    @GetMapping("/second")
    public String getSecondOrder(){
        return "Second order";
    }
}
