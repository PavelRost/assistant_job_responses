package my.jobs.search_job.unit_tests.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.jobs.search_job.controller.IndexController;
import my.jobs.search_job.model.Response;
import my.jobs.search_job.service.ResponseService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IndexController.class)
public class IndexControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResponseService responseService;

    @Test
    public void whenGetDifferentResponseThenStatus200() throws Exception {
        List<Response> responseFalseFalse = List.of(new Response("FalseFalse"));
        List<Response> responseTrueFalse = List.of(new Response("TrueFalse"));
        List<Response> responseFalseTrue = List.of(new Response("FalseTrue"));
        Mockito.when(responseService.findRespDoneArchiveFalse()).thenReturn(responseFalseFalse);
        Mockito.when(responseService.findRespDoneTrueArchiveFalse()).thenReturn(responseTrueFalse);
        Mockito.when(responseService.findRespDoneFalseArchiveTrue()).thenReturn(responseFalseTrue);

        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("responseDoneFalseArchiveFalse"))
                .andExpect(model().attributeExists("responseDoneTrueArchiveFalse"))
                .andExpect(model().attributeExists("responseDoneFalseArchiveTrue"))
                .andExpect(model().attribute("responseDoneFalseArchiveFalse", responseFalseFalse))
                .andExpect(model().attribute("responseDoneTrueArchiveFalse", responseTrueFalse))
                .andExpect(model().attribute("responseDoneFalseArchiveTrue", responseFalseTrue));
    }



}
