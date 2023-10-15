package cubix.kh.exam.catalogservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/catalog")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CatalogServiceController {

    @GetMapping("/first")
    public String getFirstCatalog() {
        return "First catalog";
    }

    @GetMapping("/second")
    public String getSecondCatalog() {
        return "Second catalog";
    }

}
