package com.evboxapi.chargingSessions.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.evboxapi.chargingSessions.models.ChargingSession;
import com.evboxapi.chargingSessions.models.StatusEnum;

@Service
public class ChargingSessionServiceImpl implements ChargingSessionService{
	
	private final HashMap<UUID, ChargingSession> chargingSesssions = new HashMap<UUID, ChargingSession>();
	
	@Autowired
	private DynamoDBMapper mapper;
	
	
	@Override
	public List<ChargingSession> findAll() {
		List<ChargingSession> tempList = new ArrayList<ChargingSession>(this.chargingSesssions.values());
		return tempList;
		
	}

	@Override
	public ChargingSession save(ChargingSession chargingSession) {
		mapper.save(chargingSession);
		return chargingSession;
	}

	@Override
	public Optional<ChargingSession> update(UUID id) {
		ChargingSession chargingSession = this.chargingSesssions.get(id);
		if(chargingSession == null) {
			return Optional.empty();
		} else {
			//chargingSession.setStatus(StatusEnum.FINISHED);
			return Optional.of(chargingSession);
		}
				
	}

	@Override
	public Optional<ChargingSession> findById(String id) {
		ChargingSession cs = mapper.load(ChargingSession.class, id);
		return Optional.of(cs);
	}

}
