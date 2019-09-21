package com.evboxapi.chargingSessions.servicesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

import com.evboxapi.chargingSessions.models.ChargingSession;
import com.evboxapi.chargingSessions.models.StatusEnum;
import com.evboxapi.chargingSessions.services.ChargingSessionService;
import com.evboxapi.chargingSessions.services.ChargingSessionServiceImpl;

@RunWith(SpringRunner.class)
class ChargingSessionServiceImplTest {
	
	
	private ChargingSessionService chargingSessionService = new ChargingSessionServiceImpl();
	


	@Test
	public void testSave() {
		ChargingSession session = new ChargingSession();
		session.setStartedAt(LocalDateTime.now());
		session.setStationId("ABC-12345");
		
		ChargingSession returnSession = chargingSessionService.save(session);
		assertNotNull(returnSession);
		assertEquals(StatusEnum.IN_PROGRESS, returnSession.getStatus());
		
	}
	
	@Test
	public void testUpdate() {
		ChargingSession session = new ChargingSession();
		session.setStartedAt(LocalDateTime.now());
		session.setStationId("ABC-12345");
		
		ChargingSession returnSession = chargingSessionService.save(session);
		assertNotNull(returnSession);
		
		Optional<ChargingSession> returnSession2 = chargingSessionService.update(returnSession.getId());
		assertNotNull(returnSession2);
		assertEquals(StatusEnum.FINISHED, returnSession2.get().getStatus());
	}
	
	@Test
	public void testFindAll() {
		ChargingSession session = new ChargingSession();
		session.setStartedAt(LocalDateTime.now());
		session.setStationId("ABC-12345");
		
		ChargingSession returnSession = chargingSessionService.save(session);
		assertNotNull(returnSession);
		
		ChargingSession session2 = new ChargingSession();
		session.setStartedAt(LocalDateTime.now());
		session.setStationId("ABC-12346");
		
		ChargingSession returnSession2 = chargingSessionService.save(session2);
		assertNotNull(returnSession2);
		
		List<ChargingSession> sessions = chargingSessionService.findAll();
		assertEquals(sessions.size(),2);
	}

}
