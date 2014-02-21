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
