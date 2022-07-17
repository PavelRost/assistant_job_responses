package my.jobs.search_job.unit_tests.controllers;

import my.jobs.search_job.controller.ResponseController;
import my.jobs.search_job.model.Response;
import my.jobs.search_job.service.ResponseService;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;


@WebMvcTest(ResponseController.class)
public class ResponseControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ResponseService responseService;

    @Test
    public void whenRedirectCreatePageThenSuccess() throws Exception {
        mockMvc.perform(get("/create"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/create.jsp"));
    }


    @Test
    public void whenSaveResponseThenStatus200() throws Exception {
        Response response = new Response("Test");

        mockMvc.perform(post("/save")
                .param("name", "NEW")
                .flashAttr("response", response)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertEquals("NEW", response.getName());
    }

    @Test
    public void whenSetStatusDoneTrueAndRedirectIndexThenSuccess() throws Exception {
        mockMvc.perform(get("/setStatusDone")
                        .param("id", "0")
                .header("referer", "/index")

        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void whenSetStatusDoneTrueAndRedirectSearchThenSuccess() throws Exception {
        mockMvc.perform(get("/setStatusDone")
                        .param("id", "0")
                        .header("referer", "/findName")

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/search"));
    }

    @Test
    public void whenSetStatusArchiveTrueAndRedirectIndexThenSuccess() throws Exception {
        mockMvc.perform(get("/setStatusDone")
                        .param("id", "0")
                        .header("referer", "/index")

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void whenSetStatusArchiveTrueAndRedirectSearchThenSuccess() throws Exception {
        mockMvc.perform(get("/setStatusDone")
                        .param("id", "0")
                        .header("referer", "/findName")

                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/search"));
    }

    @Test
    public void whenGetResponseForUpdateThenStatus200() throws Exception {
        Response response = new Response("TEST");
        when(responseService.findById(response.getId())).thenReturn(response);

        mockMvc.perform(get("/updateRespDescGet")
                .param("id", String.valueOf(response.getId()))
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("response"))
                .andExpect(model().attribute("response", response))
                .andExpect(forwardedUrl("/WEB-INF/views/update.jsp"));

        verify(responseService).findById(response.getId());
    }

    @Test
    public void whenUpdateDescriptionResponseThenSuccess() throws Exception {
        Response response = new Response("OLD");
        when(responseService.findById(response.getId())).thenReturn(response);

        mockMvc.perform(post("/updateRespDescPost")
                .param("id", String.valueOf(response.getId()))
                .param("name", "NEW")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));

        assertEquals("NEW", response.getName());
        verify(responseService).findById(response.getId());
    }

    @Test
    public void whenRedirectSearchPageThenSuccess() throws Exception {
        mockMvc.perform(get("/search"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/views/search.jsp"));
    }

    @Test
    public void whenFindResponseByNameThenStatus200() throws Exception {
        List<Response> responseList = mock(List.class);
        when(responseService.findResponseByName("test")).thenReturn(responseList);

        mockMvc.perform(post("/findName")
                .param("name", "test")
        )
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("response"))
                .andExpect(model().attribute("response", responseList))
                .andExpect(forwardedUrl("/WEB-INF/views/search.jsp"));

        verify(responseService).findResponseByName("test");
    }

    @Test
    public void whenDeleteResponseThenSuccess() throws Exception {
        mockMvc.perform(get("/delete")
                .param("id", "0")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void whenDeleteAllResponseThenSuccess() throws Exception {
        mockMvc.perform(get("/deleteAll"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void whenGetResponseArchiveThenStatus200() throws Exception {
        List<Response> responseList = mock(List.class);
        when(responseService.findRespDoneFalseArchiveTrue()).thenReturn(responseList);

        mockMvc.perform(get("/archive"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("archiveResponse"))
                .andExpect(model().attribute("archiveResponse", responseList))
                .andExpect(forwardedUrl("/WEB-INF/views/archive/indexArchive.jsp"));
    }





}
