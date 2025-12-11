CREATE TABLE users (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    lastName VARCHAR(255),
    companyName VARCHAR(255),
    address VARCHAR(255),
    zipCode VARCHAR(20),
    city VARCHAR(100),
    country VARCHAR(100),
    phoneNumber VARCHAR(50),
    siretNumber VARCHAR(50),
    vatNumber VARCHAR(50),
    logoUrl VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255)
);
