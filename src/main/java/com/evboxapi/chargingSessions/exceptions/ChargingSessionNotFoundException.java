package com.evboxapi.chargingSessions.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception class for incorrect retrieve operations
 * @author muratkaraman
 *
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ChargingSessionNotFoundException extends RuntimeException{

}
