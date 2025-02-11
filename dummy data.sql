INSERT INTO Publishers (name, publisherID) VALUES ('O’Reilly Media', 1);

INSERT INTO Authors (authorID, firstName, lastName, biography, publisherID) 
VALUES (1, 'Joanne', 'Rowling', 'British author and philanthropist', 1);

INSERT INTO Books (bookID, title, authorID, publisherID, genre, publishedYear, 
ISBN, price, copiesSold, description) 
VALUES (1, 'Harry Potter and the Sorcerer’s Stone', 1, 1, 'Fantasy', 1997, 
'978-0-7475-3269-9', 11.00, 120, 
'The book is about 11 year old Harry Potter, who receives a letter saying that he is invited to attend Hogwarts, school of witchcraft and wizardry.');
