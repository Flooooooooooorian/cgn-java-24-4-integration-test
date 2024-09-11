package de.neuefische.cgnjava244mocking;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CatControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CatRepository catRepository;

    @Test
    @DirtiesContext
    void testGetAllCats_returnsStatus200WithOneCat() throws Exception {
        //GIVEN
        catRepository.save(new Cat("123", "Katze", "Orange"));

        //WHEN
        mockMvc.perform(get("/api/cats"))

        //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                          {
                          "id": "123",
                          "name":  "Katze",
                          "color":  "Orange"
                          }
                        ]
                        """));
    }

    @Test
    @DirtiesContext
    void testPostCat_returnsStatus200WithSavedCat() throws Exception {
        //GIVEN

        //WHEN
        mockMvc.perform(post("/api/cats")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "name": "Florian",
                                  "color": "schwarz"
                                }
                                """))

                //THEN
                .andExpect(status().isOk())
                .andExpect(content().json("""
                          {
                          "name":  "Florian",
                          "color":  "schwarz"
                          }
                          """))
                .andExpect(jsonPath("$.id").isNotEmpty());
    }
}
