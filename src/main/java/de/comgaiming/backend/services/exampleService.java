package de.comgaiming.backend.services;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class exampleService {

    public int add(int number, int anotherNumber) {
        return number + anotherNumber;
    }

}
