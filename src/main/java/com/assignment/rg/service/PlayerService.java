package com.assignment.rg.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assignment.rg.constants.Constant;
import com.assignment.rg.dto.CountryDTO;
import com.assignment.rg.dto.GameDTO;
import com.assignment.rg.dto.PlatformDTO;
import com.assignment.rg.dto.PlayerCurrencyDTO;
import com.assignment.rg.dto.PlayerLeaderBoardDTO;
import com.assignment.rg.dto.PlayerProgressDTO;
import com.assignment.rg.dto.PlayerRegistrationDTO;
import com.assignment.rg.dto.PlayerResponseDTO;
import com.assignment.rg.dto.PlayerScoreDTO;
import com.assignment.rg.dto.RankDTO;
import com.assignment.rg.dto.RewardDTO;
import com.assignment.rg.entities.Country;
import com.assignment.rg.entities.Currency;
import com.assignment.rg.entities.Game;
import com.assignment.rg.entities.GameType;
import com.assignment.rg.entities.Platform;
import com.assignment.rg.entities.Player;
import com.assignment.rg.entities.PlayerCurrency;
import com.assignment.rg.entities.PlayerRewards;
import com.assignment.rg.entities.Rank;
import com.assignment.rg.entities.Reward;
import com.assignment.rg.exception.ResourceNotFoundException;
import com.assignment.rg.repository.CountryRepository;
import com.assignment.rg.repository.CurrencyRepository;
import com.assignment.rg.repository.GameRepository;
import com.assignment.rg.repository.GameTypeRepository;
import com.assignment.rg.repository.PlatformRepository;
import com.assignment.rg.repository.PlayerCurrencyRepository;
import com.assignment.rg.repository.PlayerRepository;
import com.assignment.rg.repository.PlayerRewardsRepository;
import com.assignment.rg.repository.RankRepository;
import com.assignment.rg.repository.RewardRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class PlayerService {
	
	@Autowired
	private PlayerRepository playerRepo; 
	
	@Autowired
	private CountryRepository countryRepo;
	
	@Autowired
	private PlayerCurrencyRepository playerCurrencyRepo;
	
	@Autowired
	private CurrencyRepository currencyRepo;
	
	@Autowired
	private PlatformRepository platformRepo;
	
	@Autowired
	private RankRepository rankRepo;
	
	@Autowired
	private RewardRepository rewardRepo;
	
	@Autowired
	private PlayerRewardsRepository playerRewardRepo;
	
	@Autowired
	private GameRepository gameRepo;
	
	@Autowired
	private GameTypeRepository gameTypeRepo;
	
	@Transactional
	public PlayerResponseDTO registerPlayer(PlayerRegistrationDTO playerRegistrationDTO) {
		try {
			Platform platfrom = platformRepo.findById(playerRegistrationDTO.getPlatformId())
					.orElseThrow(() -> new ResourceNotFoundException("Platform not found."));
			
			Country country = countryRepo.findById(playerRegistrationDTO.getCountryCd())
					.orElseThrow(() -> new ResourceNotFoundException("Country not found."));
			
			Rank rank = rankRepo.findById(Constant.DIGIT_ONE)
					.orElseThrow(()-> new ResourceNotFoundException("Rank not found."));
			
			Player player = Player.builder()
					.deviceId(playerRegistrationDTO.getDeviceId())
					.username(playerRegistrationDTO.getUsername())
					.platformId(platfrom)
					.country(country)
					.level(Constant.DIGIT_ONE)
					.rank(rank)
					.active(Constant.YES)
					.lastActive(LocalDateTime.now())
					.build();
			
			addDefaultCurrenciesForCurrentLevel(player);
			
			playerRepo.save(player);
			
			return buildPlayerResponseDTO(player);
		} catch (Exception e) {
			log.debug("Exception in  registerPlayer : "+ e.getMessage());
			throw e ; 
		}
	}
	
	@Transactional
	public PlayerResponseDTO updatePlayerProgress(PlayerProgressDTO playerProgressDto) {
		try {
			Player player = playerRepo.findById(playerProgressDto.getPlayerId())
					.orElseThrow(()-> new ResourceNotFoundException("No such plyer found"));
			
			if(playerProgressDto.getCountryCd() != null) {
				Country country = countryRepo.findById(playerProgressDto.getCountryCd())
						.orElseThrow(() -> new ResourceNotFoundException("No such country."));
				player.setCountry(country);
			}
			if(playerProgressDto.getLevel() != null) {
				player.setLevel(playerProgressDto.getLevel());
			}
			if(playerProgressDto.getRankId() != null) {
				Rank rank = rankRepo.findById(playerProgressDto.getRankId())
						.orElseThrow(() -> new ResourceNotFoundException("No such rank."));
				player.setRank(rank);
			}
			if(playerProgressDto.getLastActive() != null) {
				player.setLastActive(playerProgressDto.getLastActive());
			}
			playerRepo.save(player);
			
			List<PlayerCurrencyDTO> updatedCurrency = playerProgressDto.getCurrencies();
			if(updatedCurrency != null && !updatedCurrency.isEmpty()) {
				updatePlayerCurrency(player,updatedCurrency);
			}
			
			List<Long> updatedReward = playerProgressDto.getRewardIds();
			if(updatedReward != null && !updatedReward.isEmpty()) {
				updatedPlayerRewards(player,updatedReward);
			}
			
			return buildPlayerResponseDTO(player);
		} catch (Exception e) {
			log.debug("Exception in  updatePlayerProgress : "+ e.getMessage());
			throw e ; 
		}
		
	}

	protected void addDefaultCurrenciesForCurrentLevel(Player player) {
		try {
			List<Currency> newCurrencies = currencyRepo.findAll().stream()
					.filter(currency -> currency.getMinLevelRequired() == player.getLevel())
					.filter(currency -> currency.getActive() == Constant.YES)
					.collect(Collectors.toList());
			List<PlayerCurrency> playerCurrencies = newCurrencies.stream()
					.map(currency -> PlayerCurrency.builder()
							.player(player)
							.currency(currency)
							.amount(currency.getDefaultAmount())
							.build())
					.collect(Collectors.toList());
			
			playerCurrencyRepo.saveAll(playerCurrencies);
		} catch (Exception e) {
			log.debug("Exception in  addDefaultCurrenciesForCurrentLevel : "+ e.getMessage());
			throw e ; 
		}
		
	}

	private void updatedPlayerRewards(Player player, List<Long> updatedReward) {
		try {
			for (Long rewardId : updatedReward) { 
			    Reward reward = rewardRepo.findById(rewardId)
			            .orElseThrow(() -> new ResourceNotFoundException("No such reward found with ID: " + rewardId));
		
				PlayerRewards playerReward = PlayerRewards.builder()
						.player(player)
						.reward(reward)
						.active(Constant.YES)
						.build();
				
				playerRewardRepo.save(playerReward);
			}
		} catch (Exception e) {
			log.debug("Exception in  updatedPlayerRewards : "+ e.getMessage());
			throw e ; 
		}
		
	}

	private void updatePlayerCurrency(Player player, List<PlayerCurrencyDTO> updatedCurrency) {
		try {
			for(PlayerCurrencyDTO curr : updatedCurrency) {
				Currency currency = currencyRepo.findById(curr.getCurrencyId())
						.orElseThrow(() -> new ResourceNotFoundException("No currency found"));
				
				PlayerCurrency playerCurrency = playerCurrencyRepo.findByPlayerAndCurrency(player,currency)
						.orElse(PlayerCurrency.builder()
								.player(player)
								.currency(currency)
								.amount(currency.getDefaultAmount())
								.active("Y")
								.build());
				playerCurrency.setAmount(curr.getAmount());
				
				playerCurrencyRepo.save(playerCurrency);
			}
		} catch (Exception e) {
			log.debug("Exception in  updatePlayerCurrency : "+ e.getMessage());
			throw e ; 
		}
	}

	@Transactional
	public GameDTO updatePlayerScore(PlayerScoreDTO playerScoreDto) {
		try {
			Player player = playerRepo.findById(playerScoreDto.getPlayerId())
					.orElseThrow(() -> new ResourceNotFoundException("No such player found"));
			GameType gameType = gameTypeRepo.findById(playerScoreDto.getGameTypeId())
					.orElseThrow(() -> new ResourceNotFoundException("No such game type found"));
			
			Game game = Game.builder().player(player)
					.gameType(gameType)
					.score(playerScoreDto.getScore())
					.createdAt(playerScoreDto.getTimestamp())
					.build();
			
			gameRepo.save(game);
			
			return GameDTO.builder()
					.gameId(game.getGameId())
					.playerId(player.getId())
					.gameTypeId(gameType.getId())
					.score(playerScoreDto.getScore())
					.build();
		} catch (Exception e) {
			log.debug("Exception in  updatePlayerScore : "+ e.getMessage());
			throw e ; 
		}
	}
	
	public List<PlayerLeaderBoardDTO> getLeaderboard(String countryCd, int limit, Long gameTypeId){
		List<Game> games ; 
		try {
			if(countryCd != null && countryCd.trim().length() > 0 ) {
				games = gameRepo.findCountryWiseTopPlayer(countryCd,limit,gameTypeId);
			} else {
				games = gameRepo.findTopPlayers(limit,gameTypeId);
			}
			
			return games.stream()
					.map(game -> PlayerLeaderBoardDTO.builder().game(game).countryCd(countryCd).build())
					.collect(Collectors.toList());
		} catch (Exception e) {
			log.debug("Exception in  getLeaderboard : "+ e.getMessage());
			throw e ; 
		}
	}

	private PlayerResponseDTO buildPlayerResponseDTO(Player player) {
		try {
			List<PlayerCurrencyDTO> playerCurrencies = playerCurrencyRepo.findByPlayer(player).stream()
		            .map(pc -> PlayerCurrencyDTO.builder()
		                    .currencyId(pc.getCurrency().getCurrencyId())
		                    .currencyName(pc.getCurrency().getCurrencyName())
		                    .amount(pc.getAmount())
		                    .build())
		            .toList();

		    List<RewardDTO> playerRewards = playerRewardRepo.findByPlayer(player).stream()
		            .map(pr -> RewardDTO.builder()
		                    .rewardId(pr.getReward().getRewardId())
		                    .rewardName(pr.getReward().getRewardName())
		                    .build())
		            .toList();
		    
		    List<Game> games = gameRepo.findByPlayer(player);

		    return PlayerResponseDTO.builder()
		            .playerId(player.getId())
		            .username(player.getUsername())
		            .level(player.getLevel())
		            .rank(RankDTO.builder()
		                    .rankId(player.getRank().getRankId())
		                    .rankName(player.getRank().getRankName())
		                    .build())
		            .platform(PlatformDTO.builder()
		                    .platformId(player.getPlatformId().getPlatformId())
		                    .platformName(player.getPlatformId().getPlatformName())
		                    .build())
		            .country(CountryDTO.builder()
		                    .countryCd(player.getCountry().getCountryCd())
		                    .countryName(player.getCountry().getCountryName())
		                    .build())
		            .active(player.getActive())
		            .lastActive(player.getLastActive())
		            .modifiedDate(player.getModifiedDate())
		            .creationDate(player.getCreationDate())
		            .currencies(playerCurrencies)
		            .rewards(playerRewards)
		            .games(games)
		            .build();
		} catch (Exception e) {
			log.debug("Exception in  buildPlayerResponseDTO : "+ e.getMessage());
			throw e ; 
		}
	    
	}

}
