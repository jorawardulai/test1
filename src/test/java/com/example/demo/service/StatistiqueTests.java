package com.example.demo.service;

import com.example.demo.data.Voiture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class StatistiqueTests {

    private StatistiqueImpl statistique;

    @BeforeEach
    void setUp() {
        statistique = new StatistiqueImpl();
    }

    @Test
    void testPrixMoyenAvecDeuxVoitures() {
        Voiture v1 = Mockito.mock(Voiture.class);
        Voiture v2 = Mockito.mock(Voiture.class);

        Mockito.when(v1.getPrix()).thenReturn(10000);
        Mockito.when(v2.getPrix()).thenReturn(20000);

        statistique.ajouter(v1);
        statistique.ajouter(v2);

        Echantillon result = statistique.prixMoyen();

        assertEquals(2, result.getNombreDeVoitures());
        assertEquals(15000, result.getPrixMoyen());
    }

    @Test
    void testPrixMoyenListeVide_throwsException() {
        assertThrows(ArithmeticException.class, () -> {
            statistique.prixMoyen();
        });
    }
}