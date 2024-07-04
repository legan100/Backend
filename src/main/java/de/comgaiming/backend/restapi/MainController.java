package de.comgaiming.backend.restapi;

import de.comgaiming.backend.services.exampleService;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@NoArgsConstructor
@RestController
public class MainController {

    private final exampleService service = new exampleService();


    @GetMapping("/hello")
    public String helloWorld() {
        return "hello";
    }

    @PostMapping("/example")
    public ResponseEntity<?> example() {
        return ResponseEntity.ok().body("Eine nachricht");
    }


    @GetMapping("/mathe")
    public int mathe(@PathVariable int zahl) {

        return service.add(zahl, 1);
    }
}
