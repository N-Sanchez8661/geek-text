DROP DATABASE testdb;
CREATE DATABASE testdb;
USE testdb;

CREATE TABLE Users (
UserID INT PRIMARY KEY,
Username VARCHAR(50) UNIQUE NOT NULL,
UserPassword VARCHAR(255) NOT NULL,
FirstName VARCHAR(100),
LastName VARCHAR(100),
Email VARCHAR(100) UNIQUE NOT NULL,
HomeAddress TEXT
);

CREATE TABLE Wishlist (
    WishlistID INT PRIMARY KEY AUTO_INCREMENT,
    UserID INT NOT NULL,
    WishlistName VARCHAR(100) NOT NULL,
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

INSERT INTO Users (UserID, Username, UserPassword, FirstName, LastName, Email, HomeAddress)
VALUES
(1, 'john_doe', 'password123', 'John', 'Doe', 'john.doe@example.com', '123 Main St, City, State, ZIP'),
(2, 'jane_smith', 'password456', 'Jane', 'Smith', 'jane.smith@example.com', '456 Elm St, City, State, ZIP'),
(3, 'alice_wonder', 'password789', 'Alice', 'Wonder', 'alice.wonder@example.com', '789 Oak St, City, State, ZIP');

INSERT INTO Wishlist (UserID, WishlistName)
VALUES
(1, 'John\'s Tech Books'),
(1, 'John\'s Fiction Favorites'),
(2, 'Jane\'s Must-Reads'),
(3, 'Alice\'s Fantasy Picks');

-- sample query:
-- SELECT WishlistID, WishlistName
-- FROM Wishlist
-- WHERE UserID = 1;