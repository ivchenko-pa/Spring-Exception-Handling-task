package de.neuefische.springexceptionhandlingtask;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionTest {


    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCar_shouldNotThrowException_whenPorsche() throws Exception {
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/porsche"))
                //THEN
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("porsche"));
    }

    @Test
    void getCar_shoulThrowException_whenNotPorsche() throws Exception {
        //WHEN
        mockMvc.perform(MockMvcRequestBuilders.get("/api/cars/ford"))
        //THEN
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().json("""
                        {
                        "message": "Only 'porsche' allowed"
                        }
                        """));
    }
}
