CREATE TABLE User (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    userName VARCHAR(30) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    address TEXT
);
CREATE TABLE CreditCard (
    CardID INT AUTO_INCREMENT PRIMARY KEY,
    CardNumber VARCHAR(16) NOT NULL,
    ExpiryDate DATE NOT NULL,
    CVV VARCHAR(4) NOT NULL,
    userName VARCHAR(30) NOT NULL,
    FOREIGN KEY (userName) REFERENCES User(userName) ON DELETE CASCADE
);