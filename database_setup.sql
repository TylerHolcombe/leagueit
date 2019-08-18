#DROP DATABASE leagueit;
CREATE DATABASE leagueit;
USE leagueit;

CREATE TABLE application_user (
	application_user_id	BIGINT AUTO_INCREMENT NOT NULL UNIQUE,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    PRIMARY KEY (application_user_id)
);

CREATE TABLE league (
    league_id BIGINT AUTO_INCREMENT NOT NULL UNIQUE,
    owner_id BIGINT NOT NULL,
    league_name VARCHAR(255),
    team_size INT,
    rating_strategy INT,
    PRIMARY KEY (league_id),
    UNIQUE KEY unique_league_name (owner_id, league_name)
);

CREATE TABLE game (
	game_id BIGINT AUTO_INCREMENT NOT NULL UNIQUE,
    league_id BIGINT NOT NULL,
    PRIMARY KEY (game_id),
    FOREIGN KEY (league_id) REFERENCES league(league_id)
);

CREATE TABLE player (
	player_id BIGINT AUTO_INCREMENT NOT NULL UNIQUE,
    rating DOUBLE,
    wins INT,
    losses INT,
    ties INT,
    application_user_id BIGINT NOT NULL,
    league_id BIGINT NOT NULL,
    PRIMARY KEY (player_id),
    FOREIGN KEY (application_user_id) REFERENCES application_user(application_user_id),
    FOREIGN KEY (league_id) REFERENCES league(league_id),
    UNIQUE KEY unique_league_player (application_user_id, league_id)
);

CREATE TABLE game_result (
	game_result_id BIGINT AUTO_INCREMENT NOT NULL UNIQUE,
    rating_before DOUBLE,
    is_winner BOOL,
    rating_after DOUBLE,
    player_id BIGINT NOT NULL,
    game_id BIGINT NOT NULL,
    PRIMARY KEY (game_result_id),
    FOREIGN KEY (player_id) REFERENCES player(player_id),
    FOREIGN KEY (game_id) REFERENCES game(game_id),
    UNIQUE KEY unique_game_player (player_id, game_id)
);