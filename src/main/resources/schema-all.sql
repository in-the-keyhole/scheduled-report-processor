-- # TODO - REMOVE THIS FILE - ONLY HERE FOR TESTING
DROP TABLE scheduled_report IF EXISTS;

CREATE TABLE scheduled_report (
	id BIGINT IDENTITY NOT NULL PRIMARY KEY,
	name VARCHAR(20)
);

INSERT INTO scheduled_report (id, name)
	VALUES (1, 'Report 1');

INSERT INTO scheduled_report (id, name)
	VALUES (2, 'Report 2');