CREATE TABLE clothes_store (
   id INTEGER PRIMARY KEY,
   type VARCHAR(50) NOT NULL,
   name VARCHAR(255) NOT NULL,
   size VARCHAR(50) NOT NULL,
   price DOUBLE PRECISION NOT NULL,
   quantity INTEGER NOT NULL,
   material VARCHAR(255),
   sleeve_type VARCHAR(255),
   fit_type VARCHAR(255),
   ripped BOOLEAN,
   print_type VARCHAR(255),
   sports_style BOOLEAN
);