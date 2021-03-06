CREATE TABLE VenueCategoryCount (
     id INT NOT NULL AUTO_INCREMENT,
     Name NVARCHAR(150),
	 Hits NVARCHAR(20),
	 Category NVARCHAR(50),
     PRIMARY KEY (id)
)


CREATE TABLE VenueWeekDayCat (
     id INT NOT NULL AUTO_INCREMENT,
     Name NVARCHAR(150),
	 WeekDay NVARCHAR(10),
	 Hits NVARCHAR(20),
	 Category NVARCHAR(50),
     PRIMARY KEY (id)
);

CREATE TABLE VenueYearCat (
     id INT NOT NULL AUTO_INCREMENT,
     Name NVARCHAR(150),
	 Year NVARCHAR(10),
	 Hits NVARCHAR(20),
	 Category NVARCHAR(50),
     PRIMARY KEY (id)
);

CREATE TABLE VenueDayCat (
     id INT NOT NULL AUTO_INCREMENT,
     Name NVARCHAR(150),
	 Day NVARCHAR(10),
	 Hits NVARCHAR(20),
	 Category NVARCHAR(50),
     PRIMARY KEY (id)
);

CREATE TABLE VenueType (
     id INT NOT NULL AUTO_INCREMENT,
     Name NVARCHAR(150),
	 Category NVARCHAR(50),
     PRIMARY KEY (id)
);


CREATE TABLE DayOfWeekHits (
     name NVARCHAR(150),
     type NVARCHAR(50),
     weekday NVARCHAR(20),
     hits int default 0,
     PRIMARY KEY (name, type, weekday)
);


CREATE TABLE VenueHits (
     name NVARCHAR(150),
     type NVARCHAR(50),
     total_hits int default 0,
     celebrity_hits int default 0,
     PRIMARY KEY (name, type)
);

CREATE TABLE DayOfWeekHits2 (
     name NVARCHAR(150),
     type NVARCHAR(50),
     weekday NVARCHAR(20),
     hits int default 0,
     PRIMARY KEY (name, type, weekday)
);


CREATE TABLE VenueHits2 (
     name NVARCHAR(150),
     type NVARCHAR(50),
     total_hits int default 0,
     celebrity_hits int default 0,
     PRIMARY KEY (name, type)
);


CREATE TABLE VenueCompleteInformation (
     name NVARCHAR(150),
     type NVARCHAR(50),
	 latitude double default 0,
	 longitude double default 0,
     city NVARCHAR(50),
	 country NVARCHAR(50),
     PRIMARY KEY (name, type)
);


