package com.assignment.rg.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.rg.dto.EventConfigDTO;
import com.assignment.rg.dto.GameEventDTO;
import com.assignment.rg.dto.GameEventResponseDTO;
import com.assignment.rg.entities.EventConfig;
import com.assignment.rg.entities.GameEvent;
import com.assignment.rg.entities.GameType;
import com.assignment.rg.entities.Reward;
import com.assignment.rg.repository.EventConfigRepository;
import com.assignment.rg.repository.GameEventRepository;
import com.assignment.rg.repository.GameTypeRepository;
import com.assignment.rg.repository.RewardRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EventService {

	@Autowired
	private  GameEventRepository gameEventRepo;
	
	@Autowired
    private  EventConfigRepository eventConfigRepo;
	
	@Autowired
    private  GameTypeRepository gameTypeRepo;
	
	@Autowired
	private RewardRepository rewardRepo; 
	
	//to schedule a game event. 
	@Transactional
    public GameEventResponseDTO scheduleGameEvent(GameEventDTO gameEvenntDto) {
		try {
			GameType gameType = gameTypeRepo.findById(gameEvenntDto.getGameTypeId())
	                .orElseThrow(() -> new RuntimeException("GameType not found"));

	        GameEvent gameEvent = GameEvent.builder()
	                .eventName(gameEvenntDto.getEventName())
	                .startTime(gameEvenntDto.getStartTime())
	                .endTime(gameEvenntDto.getEndTime())
	                .status("SCHEDULED")
	                .gameType(gameType)
	                .build();
	        
	        GameEvent savedEvent = gameEventRepo.save(gameEvent);
	        
	        List<Reward> rewards = gameEvenntDto.getEventConfig().getRewards().stream()
	                .map(reward -> rewardRepo.save(reward))
	                .collect(Collectors.toList());

	        EventConfig eventConfig = EventConfig.builder()
	                .gameEvent(savedEvent)
	                .rewards(rewards)
	                .minLevelRequired(gameEvenntDto.getEventConfig().getMinLevelRequired())
	        		.minRankRequired(gameEvenntDto.getEventConfig().getMinLevelRequired())
	        		.maxParticipants(gameEvenntDto.getEventConfig().getMaxParticipants())
	                .build();
	        
	        eventConfigRepo.save(eventConfig);

	        return getGameEventResponseDTO(savedEvent, eventConfig);
		} catch (Exception e) {
			log.debug("exception in scheduleGameEvent : " + e.getMessage());
			throw e; 
		}
    }

    // to update a game evenet. 
    @Transactional
    public GameEventResponseDTO updateGameEvent(Long eventId, GameEventDTO gameEventDto) {
    	try {
    		GameEvent gameEvent = gameEventRepo.findById(eventId)
                    .orElseThrow(() -> new RuntimeException("Game Event not found"));

            gameEvent.setEventName(gameEventDto.getEventName());
            gameEvent.setStartTime(gameEventDto.getStartTime());
            gameEvent.setEndTime(gameEventDto.getEndTime());
            gameEvent.setStatus(gameEventDto.getStatus());

            GameEvent updatedEvent = gameEventRepo.save(gameEvent);

            EventConfig eventConfig = eventConfigRepo.findByGameEventId(updatedEvent.getId())
                    .orElseThrow(() -> new RuntimeException("EventConfig not found"));

            eventConfig.setRewards(gameEventDto.getEventConfig().getRewards());
            eventConfig.setMinLevelRequired(gameEventDto.getEventConfig().getMinLevelRequired());
            eventConfig.setMinRankRequired(gameEventDto.getEventConfig().getMinRankRequired());
            eventConfig.setMaxParticipants(gameEventDto.getEventConfig().getMaxParticipants());

            eventConfigRepo.save(eventConfig);

            return getGameEventResponseDTO(updatedEvent, eventConfig);

		} catch (Exception e) {
			log.debug("exception in scheduleGameEvent : " + e.getMessage());
			throw e; 
		}
    }

    
    //get all available evenets
    public List<GameEventResponseDTO> getAvailableEvents() {
    	try {
    		return gameEventRepo.findValidEvents(LocalDateTime.now()).stream()
                    .map(event -> getGameEventResponseDTO(event, eventConfigRepo.findByGameEventId(event.getId())
                            .orElseThrow(() -> new RuntimeException("EventConfig not found"))))
                    .collect(Collectors.toList());
		} catch (Exception e) {
			log.debug("exception in scheduleGameEvent : " + e.getMessage());
			throw e; 
		}
        
    }

    private GameEventResponseDTO getGameEventResponseDTO(GameEvent event, EventConfig config) {
        return GameEventResponseDTO.builder()
                .id(event.getId())
                .eventName(event.getEventName())
                .startTime(event.getStartTime())
                .endTime(event.getEndTime())
                .status(event.getStatus())
                .gameTypeName(event.getGameType().getName())
                .eventConfig(EventConfigDTO.builder()
                		.id(config.getId())
                		.rewards(config.getRewards())
                		.minLevelRequired(config.getMinLevelRequired())
                		.minRankRequired(config.getMinLevelRequired())
                		.maxParticipants(config.getMaxParticipants())
                		.build())
                .build();
    }
    
    
    
}
