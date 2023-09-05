package com.PLCompanyAccountingBackend.controllers_tests;

import com.PLCompanyAccountingBackend.controllers.BusinessContractorController;
import com.PLCompanyAccountingBackend.models.BusinessContractor;
import com.PLCompanyAccountingBackend.repository.BusinessContractorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(controllers = BusinessContractorController.class)
@Import(BusinessContractorController.class)
public class BusinessContractorControllerTests {


    @MockBean
    BusinessContractorRepository businessContractorRepository;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void getAllBusinessContractors_returnsOkStatus() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v_1/getAllBusinessContractors")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void getBusinessContractorById_returnsBusinessContractor() throws Exception {

        BusinessContractor mockBusinessContractor = BusinessContractor.builder().build();
        doReturn(Optional.of(mockBusinessContractor)).when(businessContractorRepository).findById(1L);

        ArgumentCaptor<Long> contractorIdArgumentCaptor = ArgumentCaptor.forClass(Long.class);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/v_1/getBusinessContractor/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        verify(businessContractorRepository, times(1)).findById(contractorIdArgumentCaptor.capture());

        assertThat(contractorIdArgumentCaptor.getValue()).isEqualTo(1L);
        assertThat(actualResponseBody).isEqualToIgnoringWhitespace(objectMapper.writeValueAsString(mockBusinessContractor));

    }

    @Test
    public void addBusinessContractor_callsSaveOnRepository() throws Exception {
        BusinessContractor mockBusinessContractor = BusinessContractor.builder()
                .businessContractorName("testName")
                .businessContractorAddress("testAddress")
                .build();


        mockMvc.perform(MockMvcRequestBuilders.post("/api/v_1/addBusinessContractor")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mockBusinessContractor)))
                .andExpect(status().isOk());

        ArgumentCaptor<BusinessContractor> contractorArgumentCapture = ArgumentCaptor.forClass(BusinessContractor.class);

        verify(businessContractorRepository, times(1)).save(contractorArgumentCapture.capture());

        assertThat(contractorArgumentCapture.getValue().getId()).isEqualTo(0);
        assertThat(contractorArgumentCapture.getValue().getBusinessContractorName()).isEqualTo("testName");
        assertThat(contractorArgumentCapture.getValue().getBusinessContractorAddress()).isEqualTo("testAddress");
    }

    @Test
    public void deleteBusinessContractor_invalidId_throwsException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v_1/deleteBusinessContractor/{id}", 1L)
                        .contentType("application/json"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteBusinessContractor_validId_callsDelete() throws Exception {
        doReturn(true).when(businessContractorRepository).existsById(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v_1/deleteBusinessContractor/{id}", 1L)
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    public void editBusinessContractor_invalidId_throwsException() throws Exception {
        BusinessContractor mockBusinessContractor = BusinessContractor.builder()
                .id(1L)
                .businessContractorName("testName")
                .businessContractorAddress("testAddress")
                .build();

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v_1/editBusinessContractor/{id}", 1L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mockBusinessContractor)))
                .andExpect(status().isNotFound());
    }

    @Test
    public void editBusinessContractor_validId_callsSaveWithCorrectId() throws Exception {
        BusinessContractor mockBusinessContractor = BusinessContractor.builder()
                .id(1L)
                .businessContractorName("testName")
                .businessContractorAddress("testAddress")
                .build();

        doReturn(Optional.of(mockBusinessContractor)).when(businessContractorRepository).findById(1L);
        doReturn(mockBusinessContractor).when(businessContractorRepository).save(mockBusinessContractor);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v_1/editBusinessContractor/{id}", 1L)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(mockBusinessContractor)))
                .andExpect(status().isOk());

        verify(businessContractorRepository, times(1)).save(mockBusinessContractor);
    }

}
