package com.example.demo.data;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class VoitureTest {
    Voiture voiture = new Voiture("peugeot", 20000);

    @Test
    void creerVoiture(){
        String a =voiture.getMarque();
        assertEquals(a, "peugeot");
    }

}
