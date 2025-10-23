CREATE DATABASE librarydb;

USE librarydb;

CREATE TABLE books (
    book_id INT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    author VARCHAR(100) NOT NULL,
    status VARCHAR(20) DEFAULT 'Available',
    borrower_name VARCHAR(100)
);

INSERT INTO books (book_id, title, author, status) VALUES
(1, 'Java Programming', 'James Gosling', 'Available'),
(2, 'Database Systems', 'Elmasri & Navathe', 'Available'),
(3, 'Operating System Concepts', 'Silberschatz', 'Available');

