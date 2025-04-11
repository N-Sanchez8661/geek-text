-- Insert users
INSERT INTO "User" (username, password) VALUES ('john_doe', 'password1');
INSERT INTO "User" (username, password) VALUES ('jane_smith', 'password2');
INSERT INTO "User" (username, password) VALUES ('alice_johnson', 'password3');

-- Insert books
INSERT INTO books (title, price, genre, publishedYear, ISBN, CopiesSold, Publisher, authorID)
VALUES
('The Hitchhiker''s Guide to the Galaxy', 7.99, 'Sci-Fi', 1979, '9780345391803', 1000, 'Pan Books', 1),
('Dune', 12.50, 'Sci-Fi', 1965, '9780441172719', 900, 'Chilton Books', 1),
('The Lord of the Rings', 15.99, 'Fantasy', 1954, '9780618640157', 800, 'Allen & Unwin', 2),
('Pride and Prejudice', 9.99, 'Romance', 1813, '9780141199078', 700, 'T. Egerton', 3),
('1984', 11.00, 'Dystopian', 1949, '9780451524935', 600, 'Secker & Warburg', 3);

-- Insert shopping carts
INSERT INTO "ShoppingCart" (userId) VALUES (1);
INSERT INTO "ShoppingCart" (userId) VALUES (2);
INSERT INTO "ShoppingCart" (userId) VALUES (3);

-- Insert shopping cart items
-- User 1 (John Doe) has books 1 and 2
INSERT INTO "ShoppingCartItem" (cartId, bookId, quantity) VALUES (1, 1, 1);
INSERT INTO "ShoppingCartItem" (cartId, bookId, quantity) VALUES (1, 2, 1);

-- User 2 (Jane Smith) has book 3
INSERT INTO "ShoppingCartItem" (cartId, bookId, quantity) VALUES (2, 3, 1);

-- User 3 (Alice Johnson) has books 4 and 5
INSERT INTO "ShoppingCartItem" (cartId, bookId, quantity) VALUES (3, 4, 1);
INSERT INTO "ShoppingCartItem" (cartId, bookId, quantity) VALUES (3, 5, 1);
