package com.evboxapi.chargingSessions.models;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Data model for charging session entities
 * @author muratkaraman
 *
 */
public class ChargingSession {
	
	private UUID id;
	
	private String stationId;
	
	private LocalDateTime startedAt;
	
	private StatusEnum status;

	public UUID getId() {
		return id;
	}

	public void setId() {
		this.id = UUID.randomUUID();
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}

	public LocalDateTime getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(LocalDateTime startedAt) {
		this.startedAt = startedAt;
	}

	public StatusEnum getStatus() {
		return status;
	}

	public void setStatus(StatusEnum status) {
		this.status = status;
	}
	
	
}
