CREATE TABLE vacancy (
	id BIGINT GENERATED BY DEFAULT AS IDENTITY,
	name varchar(255) not null,
	salary INT,
	experience varchar(255),
	city varchar(255)
);
