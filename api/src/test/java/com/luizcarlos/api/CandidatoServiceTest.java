package com.luizcarlos.api;

import com.luizcarlos.api.repository.CandidatoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CandidatoServiceTest {

    @Autowired
    CandidatoRepository candidatoRepository;

    @Test
    public void candidatoTest(){
        /*System.out.println(candidatoRepository.findCandidatoByNomeStartingWith("Ja"));*/
        System.out.println(candidatoRepository.findByNomeStartingWithAndLegendaStartingWith("K","P"));
    }
}
