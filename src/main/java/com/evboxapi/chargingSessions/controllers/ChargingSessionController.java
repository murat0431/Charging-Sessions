package com.evboxapi.chargingSessions.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.evboxapi.chargingSessions.exceptions.ChargingSessionNotFoundException;
import com.evboxapi.chargingSessions.models.ChargingSession;
import com.evboxapi.chargingSessions.services.ChargingSessionService;

/**
 * Controller class which accepts REST requests and redirect them to the service layer.
 * @author muratkaraman
 *
 */

@RestController
public class ChargingSessionController {
	
	@Autowired
	ChargingSessionService chargingSessionService;
	
	/**
	 * Calls the service to retrieve ChargingSessions
	 * 
	 * @return ResponseEntity<List<ChargingSession>>
	 */
	@GetMapping("/chargingSessions")
	public ResponseEntity<List<ChargingSession>> getChargingSessions() {
		return ResponseEntity.ok(this.chargingSessionService.findAll());
	}
	
	/**
	 * Saves a new ChargingSession by calling the service. Takes the requestbody from the requestç
	 * 
	 * @param chargingSession
	 * @return ResponseEntity<ChargingSession>
	 */
	
	@PostMapping("/chargingSessions")
	public ResponseEntity<ChargingSession> addChargingSession(@RequestBody ChargingSession chargingSession) {
		return ResponseEntity.ok(this.chargingSessionService.save(chargingSession));
	} 
	
	/**
	 * Tries to find the charging session with the given id.
	 * If not founds throws an exception.
	 * @param id
	 * @return ResponseEntity
	 */
	
	@PutMapping("/chargingSessions/{id}")
	public ResponseEntity<ChargingSession> updateChargingSession(@PathVariable UUID id) {
		Optional<ChargingSession> ch = this.chargingSessionService.update(id);
		if(!ch.isPresent()) {
			throw new ChargingSessionNotFoundException();
		}
		return ResponseEntity.ok(ch.get());
	}
}
