-- Create Country Table
CREATE TABLE IF NOT EXISTS country (
    country_cd VARCHAR(5) PRIMARY KEY,
    country_name VARCHAR(100) NOT NULL UNIQUE,
    active CHAR(1) NOT NULL CHECK (active IN ('Y', 'N'))
);

-- Create Platform Table
CREATE TABLE IF NOT EXISTS platform (
    platform_id SERIAL PRIMARY KEY,
    platform_name VARCHAR(50) NOT NULL UNIQUE,
    active CHAR(1) NOT NULL CHECK (active IN ('Y', 'N'))
);

-- Create Currency Table
CREATE TABLE IF NOT EXISTS currency (
    currency_id SERIAL PRIMARY KEY,
    currency_name VARCHAR(50) NOT NULL UNIQUE,
    min_level_required INT NOT NULL DEFAULT 1,
    default_amount BIGINT NOT NULL DEFAULT 0,
    active CHAR(1) NOT NULL CHECK (active IN ('Y', 'N'))
);


-- Create Rank Table
CREATE TABLE IF NOT EXISTS rank (
    rank_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    rank_name VARCHAR(255) NOT NULL UNIQUE,
    active VARCHAR(10) NOT NULL
);

-- Create Rank Table
CREATE TABLE IF NOT EXISTS Reward (
    reward_id INT AUTO_INCREMENT PRIMARY KEY,
    reward_name VARCHAR(100) NOT NULL UNIQUE,
    active CHAR(1) DEFAULT 'Y' NOT NULL
);


CREATE TABLE IF NOT EXISTS Game_Type (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);