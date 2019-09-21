package com.evboxapi.chargingSessions.controllersTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.evboxapi.chargingSessions.models.ChargingSession;
import com.evboxapi.chargingSessions.models.StatusEnum;
import com.evboxapi.chargingSessions.services.ChargingSessionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@WebMvcTest
public class ChargingSessionControllerTest {
	 	@Autowired
	    private MockMvc mockMvc;

	    @MockBean
	    private ChargingSessionService sessionService;
	    
	    @Autowired
	    private ObjectMapper objectMapper;

	    @Test
	    public void findAll() throws Exception {
	        // given
	        ChargingSession session = new ChargingSession();
	        session.setId();
	        session.setStartedAt(LocalDateTime.now());
	        session.setStationId("ABC-1234");
	        session.setStatus(StatusEnum.IN_PROGRESS);

	        List<ChargingSession> sessions = Arrays.asList(session);
	        given(sessionService.findAll()).willReturn(sessions);

	        String URI = "/chargingSessions";
			RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					URI).accept(
					MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();

			String expectedJson = this.mapToJson(sessions).toString();
			String outputInJson = result.getResponse().getContentAsString();
			assertThat(outputInJson).isEqualTo(expectedJson);
	    }
	    
	    @Test
	    public void addChargingSessionTest() throws Exception {
	    	ChargingSession session = new ChargingSession();
	        session.setStartedAt(LocalDateTime.now());
	        session.setStationId("ABC-1234");
	        session.setStatus(StatusEnum.IN_PROGRESS);
			
			String inputInJson = this.mapToJson(session);
			
			String URI = "/chargingSessions";
			
			Mockito.when(sessionService.save(Mockito.any(ChargingSession.class))).thenReturn(session);
			
			RequestBuilder requestBuilder = MockMvcRequestBuilders
					.post(URI)
					.accept(MediaType.APPLICATION_JSON).content(inputInJson)
					.contentType(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			
			
			String outputInJson = response.getContentAsString();

			String expectedJson = this.mapToJson(session);
			
			
			assertThat(outputInJson).isEqualTo(expectedJson);
			assertEquals(HttpStatus.OK.value(), response.getStatus());
	    }
	    
	    @Test
	    public void updateChargingSessionTest() throws Exception {
	    	ChargingSession session = new ChargingSession();
	    	session.setId();
	        session.setStartedAt(LocalDateTime.now());
	        session.setStationId("ABC-1234");
	        session.setStatus(StatusEnum.IN_PROGRESS);
	        
	        ChargingSession updatedSession = session;
	        updatedSession.setStatus(StatusEnum.FINISHED);
			
	        String URI = "/chargingSessions/" + session.getId() ;
			
	        Mockito.when(sessionService.update(session.getId())).thenReturn(Optional.of(updatedSession));
	        
	        RequestBuilder requestBuilder = MockMvcRequestBuilders
					.put(URI)
					.accept(MediaType.APPLICATION_JSON);

			MvcResult result = mockMvc.perform(requestBuilder).andReturn();
			MockHttpServletResponse response = result.getResponse();
			String outputInJson = response.getContentAsString();

			String expectedJson = this.mapToJson(updatedSession);


			
			assertThat(outputInJson).isEqualTo(expectedJson);
			assertEquals(HttpStatus.OK.value(), response.getStatus());
			
	        
	    }
	    
	    private String mapToJson(Object object) throws JsonProcessingException {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			return objectMapper.writeValueAsString(object);
		}

}
