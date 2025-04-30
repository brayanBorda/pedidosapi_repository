package com.virtualcoffee.pedidosapi;

import com.virtualcoffee.pedidosapi.service.BeverageClient;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.context.TestConfiguration;

@SpringBootTest
class PedidosapiApplicationTests {

    @Test
    void contextLoads() {
    }

    @TestConfiguration
    static class MockConfig {
        @Bean
        public BeverageClient beverageClient() {
            return name -> true; // Simula que siempre hay disponibilidad
        }
    }
}
