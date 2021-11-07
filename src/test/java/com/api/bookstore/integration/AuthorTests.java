package com.api.bookstore.integration;

import com.api.bookstore.controller.impl.AuthorControllerImpl;
import com.api.bookstore.exceptionhandler.ApplicationExceptionHandler;
import com.api.bookstore.service.impl.AuthorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthorTests {

    @Mock
    private AuthorServiceImpl authorService;

    @InjectMocks
    private AuthorControllerImpl authorController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(authorController)
                .alwaysDo(print())
                .setControllerAdvice(new ApplicationExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("POST /author. Expected 201")
    public void givenPostAuthorURI_whenValidJson_thenCreated() throws Exception {
        var author = Map.of(
                "name", "Ray",
                "lastName", "Dalio"
        );
        var requestBody = new ObjectMapper().writeValueAsString(author);

        mockMvc.perform(post("/api/author")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andReturn();
    }

    @Test
    @DisplayName("POST /author. Expected 400")
    public void givenPostAuthorURI_whenInvalidJson_thenValidationErrorAndBadRequest() throws Exception {
        var author = Map.of(
                "lastName", "Dalio"
        ); // missing name
        String requestBody = new ObjectMapper().writeValueAsString(author);

        mockMvc.perform(post("/api/author")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorTitle").value("Validation error"))
                .andReturn();
    }

    @Test
    @DisplayName("GET /author/{id}. Expected 200")
    public void whenGetAuthorURI_validPathVariable_thenOk() throws Exception {
        long id = 1L;

        mockMvc.perform(get("/api/author/" + id))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("GET /author/{id}. Expected 400")
    public void whenGetAuthorURI_invalidPathVariable_thenBadRequest() throws Exception {
        mockMvc.perform(get("/api/author/" + "badId"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("PUT /author/{id}. Expected 200")
    public void whenPutAuthorURI_validJson_thenOk() throws Exception {
        long id = 1L;
        var author = Map.of(
                "name", "Ray",
                "lastName", "Dalio"
        );
        String requestBody = new ObjectMapper().writeValueAsString(author);

        mockMvc.perform(put("/api/author/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("PUT /author/{id}. Expected 400")
    public void whenPutAuthorURI_invalidJson_thenBadRequest() throws Exception {
        long id = 1L;
        var author = Map.of(
                "lastName", "Dalio"
        ); // missing name property
        String requestBody = new ObjectMapper().writeValueAsString(author);

        mockMvc.perform(put("/api/author/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorTitle").value("Validation error"))
                .andExpect(jsonPath("$.errors").hasJsonPath())
                .andReturn();
    }

    @Test
    @DisplayName("DELETE /author/{id}. Expect 204")
    public void givenDeleteAuthorURI_validPathVariable_thenNoContent() throws Exception {
        long id = 1L;

        mockMvc.perform(delete("/api/author/" + id))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    @DisplayName("DELETE /author/{id}. Expect 400")
    public void givenDeleteAuthorURI_invalidPathVariable_thenBadRequest() throws Exception {
        mockMvc.perform(delete("/api/author/" + "badId"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
