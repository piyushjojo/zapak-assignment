INSERT INTO country (country_cd, country_name, active) VALUES ('US', 'United States', 'Y');
INSERT INTO country (country_cd, country_name, active) VALUES ('IN', 'India', 'Y');
INSERT INTO country (country_cd, country_name, active) VALUES ('UK', 'United Kingdom', 'Y');
INSERT INTO country (country_cd, country_name, active) VALUES ('CA', 'Canada', 'Y');
INSERT INTO country (country_cd, country_name, active) VALUES ('AU', 'Australia', 'Y');
INSERT INTO country (country_cd, country_name, active) VALUES ('FR', 'France', 'Y');
INSERT INTO country (country_cd, country_name, active) VALUES ('DE', 'Germany', 'Y');
INSERT INTO country (country_cd, country_name, active) VALUES ('JP', 'Japan', 'Y');
INSERT INTO country (country_cd, country_name, active) VALUES ('BR', 'Brazil', 'Y');
INSERT INTO country (country_cd, country_name, active) VALUES ('ZA', 'South Africa', 'Y');

INSERT INTO platform (platform_name, active) VALUES ('Android', 'Y');
INSERT INTO platform (platform_name, active) VALUES ('iOS', 'Y');
INSERT INTO platform (platform_name, active) VALUES ('Windows', 'Y');

INSERT INTO currency (currency_name, min_level_required, default_amount, active) 
VALUES ('Gold', 1, 1000, 'Y');
INSERT INTO currency (currency_name, min_level_required, default_amount, active) 
VALUES ('Gem', 5, 500, 'Y');
INSERT INTO currency (currency_name, min_level_required, default_amount, active) 
VALUES ('Cash', 3, 2000, 'Y');


INSERT INTO rank (rank_name, active) VALUES ('Beginner', 'Y');
INSERT INTO rank (rank_name, active) VALUES ('Intermediate', 'Y');
INSERT INTO rank (rank_name, active) VALUES ('Advanced', 'N');
INSERT INTO rank (rank_name, active) VALUES ('Expert', 'Y');
INSERT INTO rank (rank_name, active) VALUES ('Master', 'N');
INSERT INTO rank (rank_name, active) VALUES ('Grandmaster', 'Y');
INSERT INTO rank (rank_name, active) VALUES ('Legendary', 'Y');
INSERT INTO rank (rank_name, active) VALUES ('Elite', 'N');
INSERT INTO rank (rank_name, active) VALUES ('Champion', 'Y');
INSERT INTO rank (rank_name, active) VALUES ('Ultimate', 'N');

INSERT INTO Reward (reward_name, active) VALUES 
('Gold Bonus', 'Y'),
('Extra Life', 'Y'),
('Mystery Box', 'Y');

