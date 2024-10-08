-- Create Product Table
CREATE TABLE Product (
                         id BIGSERIAL PRIMARY KEY,  -- Automatically generates ID
                         name VARCHAR(255) NOT NULL,
                         brand VARCHAR(255),
                         imageUrl VARCHAR(255),
                         price DOUBLE PRECISION NOT NULL,
                         weight DOUBLE PRECISION NOT NULL,
                         productComposition TEXT,
                         categoryName VARCHAR(255) NOT NULL
);

-- Create ProductInventory Table
CREATE TABLE ProductInventory (
                                  id BIGSERIAL PRIMARY KEY,  -- Automatically generates ID
                                  productId BIGINT NOT NULL,
                                  availableQuantity INT,
                                  FOREIGN KEY (productId) REFERENCES Product(id)
);
