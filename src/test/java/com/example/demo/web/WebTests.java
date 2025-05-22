package com.example.demo.web;

import com.example.demo.data.Voiture;
import com.example.demo.service.Echantillon;
import com.example.demo.service.Statistique;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebTests {

    @MockBean
    Statistique statistique; // C'est une interface, comme dans ton contrôleur

    @Autowired
    MockMvc mockMvc;

    @Test
    void testGetStatistiquesOK() throws Exception {
        when(statistique.prixMoyen()).thenReturn(new Echantillon(2, 16000));

        mockMvc.perform(get("/statistique"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreDeVoitures").value(2))
                .andExpect(jsonPath("$.prixMoyen").value(16000));
    }

    @Test
    void testCreerVoitureOK() throws Exception {
        String voitureJson = "{\"marque\":\"Peugeot\",\"prix\":12000}";

        mockMvc.perform(post("/voiture")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(voitureJson))
                .andExpect(status().isOk());

        verify(statistique, times(1)).ajouter(any(Voiture.class));
    }
}
