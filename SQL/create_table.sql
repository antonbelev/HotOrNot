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
     hits NVARCHAR(20),
     PRIMARY KEY (name, type, weekday)
);


CREATE TABLE VenueHits (
     name NVARCHAR(150),
     type NVARCHAR(50),
     total_hits NVARCHAR(20),
     celebrity_hits NVARCHAR(20),
     PRIMARY KEY (name, type)
);


