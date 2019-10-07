package com.evboxapi.chargingSessions.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.evboxapi.chargingSessions.models.ChargingSession;

/**
 * Interface for charging session operations
 * 
 * @author muratkaraman
 *
 */
public interface ChargingSessionService {
	
	/**
	 * Retrieves all charging sessions.
	 * 
	 * @return List<CharginSession>
	 */
	List<ChargingSession> findAll();
	
	/**
	 * Saves a new charging session with unique id
	 * 
	 * @param chargingSession
	 * @return ChargingSession
	 */
	ChargingSession save(ChargingSession chargingSession);
	
	/**
	 * Updates the session by changing its status to 'FINISHED' with the given id
	 * 
	 * @param id
	 * @return
	 */
	Optional<ChargingSession> update(UUID id);
	
	Optional<ChargingSession> findById(String id);
	
}
