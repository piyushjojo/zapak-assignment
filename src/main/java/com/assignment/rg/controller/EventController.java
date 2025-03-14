package com.assignment.rg.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.rg.dto.ApiResponse;
import com.assignment.rg.dto.GameEventDTO;
import com.assignment.rg.dto.GameEventResponseDTO;
import com.assignment.rg.service.EventService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Slf4j
public class EventController {

    private final EventService eventService;

    
    @PostMapping("/schedule")
    public ResponseEntity<?> scheduleGameEvent(@RequestBody GameEventDTO eventDTO) {
    	GameEventResponseDTO gameEventResponseDTO = null ; 
    	try {
			gameEventResponseDTO = eventService.scheduleGameEvent(eventDTO) ; 
			return new ResponseEntity<GameEventResponseDTO>(gameEventResponseDTO,HttpStatus.OK);
		} catch (Exception e) {
			log.debug("Exception in scheduleGameEvent "+ e.getMessage());
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
    }

    @PutMapping("/update/{eventId}")
    public ResponseEntity<?> updateGameEvent(
            @PathVariable("eventId") Long eventId, @RequestBody GameEventDTO eventDTO) {
    	GameEventResponseDTO gameEventResponseDTO = null ; 
    	try {
			gameEventResponseDTO = eventService.updateGameEvent(eventId, eventDTO); 
			return new ResponseEntity<GameEventResponseDTO>(gameEventResponseDTO,HttpStatus.OK);
		} catch (Exception e) {
			log.debug("Exception in updateGameEvent "+ e.getMessage());
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
    }

    @GetMapping("/availableEvents")
    public ResponseEntity<?> getAvailableEvents() {
    	List<GameEventResponseDTO> gameEventResponseDtoList = null ; 
    	try {
    		gameEventResponseDtoList = eventService.getAvailableEvents(); 
			return new ResponseEntity<List<GameEventResponseDTO>>(gameEventResponseDtoList,HttpStatus.OK);
		} catch (Exception e) {
			log.debug("Exception in getAvailableEvents "+ e.getMessage());
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
		}
    }
}
