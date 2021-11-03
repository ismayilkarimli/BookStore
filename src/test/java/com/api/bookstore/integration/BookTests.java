package com.api.bookstore.integration;

import com.api.bookstore.controller.impl.BookControllerImpl;
import com.api.bookstore.exceptionhandler.ApplicationExceptionHandler;
import com.api.bookstore.service.impl.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookTests {

    @Mock
    private BookServiceImpl bookService;

    @InjectMocks
    private BookControllerImpl bookController;

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(bookController)
                .alwaysDo(print())
                .setControllerAdvice(new ApplicationExceptionHandler())
                .build();
    }

    @Test
    @DisplayName("POST /book. Expected 201")
    public void givenAddBookURI_whenValidJson_thenCreated() throws Exception {
        var book = Map.of(
                "isbn", "01344-39023",
                "title", "Rich Dad Poor Dad",
                "releaseDate", "01-10-1999",
                "pageCount", "120"
        );
        var requestBody = new ObjectMapper().writeValueAsString(book);

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").hasJsonPath())
                .andReturn();
    }

    @Test
    @DisplayName("POST /book. Expected 400")
    public void givenPostBookURI_whenInvalidJson_thenValidationErrorAndBadRequest() throws Exception {
        var book = Map.of(
                "isbn", "01344-39023",
                "releaseDate", "01-10-1999",
                "pageCount", "120"
        ); // missing title property
        String requestBody = new ObjectMapper().writeValueAsString(book);

        mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorTitle").value("Validation error"))
                .andReturn();
    }

    @Test
    @DisplayName("GET /book/{id}. Expected 200")
    public void whenGetBookURI_validPathVariable_thenOk() throws Exception {
        long id = 1L;

        mockMvc.perform(get("/book/" + id))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("GET /book/{id}. Expected 400")
    public void whenGetBookURI_invalidPathVariable_thenBadRequest() throws Exception {
        mockMvc.perform(get("/book/" + "badId"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    @DisplayName("PUT /book/{id}. Expected 200")
    public void whenUpdateBookURI_validJson_thenOk() throws Exception {
        long id = 1L;
        var book = Map.of(
                "isbn", "01344-39023",
                "title", "Rich Dad Poor Dad",
                "releaseDate", "01-10-1999",
                "pageCount", "120"
        );
        String requestBody = new ObjectMapper().writeValueAsString(book);

        mockMvc.perform(put("/book/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    @DisplayName("PUT /book/{id}. Expected 400")
    public void whenUpdateBookURI_invalidJson_thenBadRequest() throws Exception {
        long id = 1L;
        var book = Map.of(
                "isbn", "01344-39023",
                "releaseDate", "01-10-1999",
                "pageCount", "120"
        ); // missing title property
        String requestBody = new ObjectMapper().writeValueAsString(book);

        mockMvc.perform(put("/book/" + id)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errorTitle").value("Validation error"))
                .andExpect(jsonPath("$.errors").hasJsonPath())
                .andReturn();
    }

    @Test
    @DisplayName("DELETE /book/{id}. Expect 204")
    public void givenDeleteBookURI_validPathVariable_thenNoContent() throws Exception {
        long id = 1L;

        mockMvc.perform(delete("/book/" + id))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    @DisplayName("DELETE /book/{id}. Expect 400")
    public void givenDeleteBookURI_invalidPathVariable_thenBadRequest() throws Exception {
        mockMvc.perform(delete("/book/" + "badId"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}
