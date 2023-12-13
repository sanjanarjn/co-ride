CREATE TABLE your_table_name (
    id SERIAL PRIMARY KEY,
    start GEOMETRY(Point, 4326),
    end GEOMETRY(Point, 4326),
    owner BIGINT,
    startTime TIMESTAMP
);


