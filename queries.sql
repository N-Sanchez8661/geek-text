-- Retrieve all reviews for a book (replace BOOK_ID with actual ID)
SELECT 
    r.id AS review_id, 
    u.username, 
    r.rating, 
    r.comment, 
    r.created_at 
FROM reviews r
JOIN users u ON r.user_id = u.id
WHERE r.book_id = 1;

