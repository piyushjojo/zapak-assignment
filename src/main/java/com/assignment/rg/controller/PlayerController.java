package com.assignment.rg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.rg.dto.ApiResponse;
import com.assignment.rg.dto.PlayerProgressDTO;
import com.assignment.rg.dto.PlayerRegistrationDTO;
import com.assignment.rg.dto.PlayerResponseDTO;
import com.assignment.rg.entities.Player;
import com.assignment.rg.service.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
	
	@Autowired
	public PlayerService playerService;
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody PlayerRegistrationDTO playerRegDto){
		PlayerResponseDTO player = null ; 
		try {
			player = playerService.registerPlayer(playerRegDto);
			return new ResponseEntity<PlayerResponseDTO>(player, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	@PutMapping("/updateProgress")
	public ResponseEntity<?> updatePlayerProgress(@RequestBody PlayerProgressDTO playerProgressDto){
		PlayerResponseDTO player = null ; 
		try {
			player = playerService.updatePlayerProgress(playerProgressDto);
			return new ResponseEntity<PlayerResponseDTO>(player, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ApiResponse(e.getMessage()), HttpStatus.EXPECTATION_FAILED);
		}
		
	}

}
