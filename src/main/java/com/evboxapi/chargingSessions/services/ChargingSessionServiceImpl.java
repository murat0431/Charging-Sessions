package com.evboxapi.chargingSessions.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.evboxapi.chargingSessions.models.ChargingSession;
import com.evboxapi.chargingSessions.models.StatusEnum;

@Service
public class ChargingSessionServiceImpl implements ChargingSessionService{
	
	private final HashMap<UUID, ChargingSession> chargingSesssions = new HashMap<UUID, ChargingSession>();
	
	@Override
	public List<ChargingSession> findAll() {
		List<ChargingSession> tempList = new ArrayList<ChargingSession>(this.chargingSesssions.values());
		return tempList;
	}

	@Override
	public ChargingSession save(ChargingSession chargingSession) {
		chargingSession.setId();
		chargingSession.setStatus(StatusEnum.IN_PROGRESS);
		chargingSesssions.put(chargingSession.getId(), chargingSession);
		return chargingSession;
	}

	@Override
	public Optional<ChargingSession> update(UUID id) {
		ChargingSession chargingSession = this.chargingSesssions.get(id);
		if(chargingSession == null) {
			return Optional.empty();
		} else {
			chargingSession.setStatus(StatusEnum.FINISHED);
			return Optional.of(chargingSession);
		}
				
	}

}
