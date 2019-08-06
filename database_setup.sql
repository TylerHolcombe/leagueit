CREATE DATABASE leagueit;
USE leagueit;

CREATE TABLE application_user (
	application_user_id	BIGINT NOT NULL UNIQUE,
    username VARCHAR(255),
    password VARCHAR(255),
    PRIMARY KEY (application_user_id)
);

CREATE TABLE league (
    league_id BIGINT NOT NULL UNIQUE,
    league_name VARCHAR(255),
    team_size INT,
    rating_strategy INT,
    PRIMARY KEY (league_id)
);

CREATE TABLE game (
	game_id BIGINT NOT NULL UNIQUE,
    league_id BIGINT NOT NULL,
    PRIMARY KEY (game_id),
    FOREIGN KEY (league_id) REFERENCES league(league_id)
);

CREATE TABLE player (
	player_id BIGINT NOT NULL UNIQUE,
    rating INT,
    wins INT,
    losses INT,
    ties INT,
    application_user_id BIGINT NOT NULL,
    league_id BIGINT NOT NULL,
    PRIMARY KEY (player_id),
    FOREIGN KEY (application_user_id) REFERENCES application_user(application_user_id),
    FOREIGN KEY (league_id) REFERENCES league(league_id)
);

CREATE TABLE game_result (
	game_result_id BIGINT NOT NULL UNIQUE,
    rating_before INT,
    is_winner BOOL,
    rating_after INT,
    player_id BIGINT NOT NULL,
    game_id BIGINT NOT NULL,
    PRIMARY KEY (game_result_id),
    FOREIGN KEY (player_id) REFERENCES player(player_id),
    FOREIGN KEY (game_id) REFERENCES game(game_id)
);