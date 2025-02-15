USE geektext;

-- Insert Users
INSERT INTO users (username, email) VALUES 
('bruno_s', 'bruno@example.com'),
('sam123', 'samantha@example.com');

-- Insert Books
INSERT INTO books (title, author, genre, published_year) VALUES
('Clean Code', 'Robert C. Martin', 'Programming', 2008),
('The Pragmatic Programmer', 'Andrew Hunt', 'Programming', 1999);

-- Insert Reviews
INSERT INTO reviews (book_id, user_id, rating, comment) VALUES
(1, 1, 5.0, 'Amazing book! A must-read for developers.'),
(2, 2, 4.5, 'Great insights into software engineering.');
