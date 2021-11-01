INSERT INTO Author( id, name, last_name ) values ( 1, 'Mark', 'Twain' );
INSERT INTO Author( id, name, last_name ) values ( 2, 'Ray', 'Dalio' );
INSERT INTO Author( id, name, last_name ) values ( 3, 'Leo', 'Tolstoy' );
INSERT INTO Author( id, name, last_name ) values ( 4, 'Napoleon', 'Hill' );
INSERT INTO Author( id, name, last_name ) values ( 5, 'Viktor', 'Frankl' );
INSERT INTO Author( id, name, last_name ) values ( 6, 'Joshua', 'Bloch' );
INSERT INTO Author( id, name, last_name ) values ( 7, 'Bert', 'Bates' );
INSERT INTO Author( id, name, last_name ) values ( 8, 'Kathy', 'Sierra' );
INSERT INTO Author( id, name, last_name ) values ( 9, 'Andy', 'Hunt' );
INSERT INTO Author( id, name, last_name ) values ( 10, 'Dave', 'Thomas' );


INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 1, 'Principles', NOW(), 592, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 2, 'The Death of Ivan Ilyich', NOW(), 92, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 3, 'Think and Grow Rich', NOW(), 280, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 4, 'Mans Searh for Meaning', NOW(), 128, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 5, 'Head First: Java', NOW(), 420, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 6, 'Effective Java', NOW(), 328, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 7, 'Pragmatic Programmer', NOW(), 218, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 8, 'War and Peace', NOW(), 1001, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 9, 'Anna Karenina', NOW(), 492, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 10, 'The Adventures of Tom Sawyer', NOW(), 621, NOW(), NOW() );

INSERT INTO Author_Book( author_id, book_id ) values ( 1, 10 );
INSERT INTO Author_Book( author_id, book_id ) values ( 2, 1 );
INSERT INTO Author_Book( author_id, book_id ) values ( 4, 3 );
INSERT INTO Author_Book( author_id, book_id ) values ( 5, 4 );
INSERT INTO Author_Book( author_id, book_id ) values ( 6, 6 );
INSERT INTO Author_Book( author_id, book_id ) values ( 7, 5 );
INSERT INTO Author_Book( author_id, book_id ) values ( 8, 5 );
INSERT INTO Author_Book( author_id, book_id ) values ( 9, 7 );
INSERT INTO Author_Book( author_id, book_id ) values ( 10, 7 );
INSERT INTO Author_Book( author_id, book_id ) values ( 3, 2 );
INSERT INTO Author_Book( author_id, book_id ) values ( 3, 8 );
INSERT INTO Author_Book( author_id, book_id ) values ( 3, 9 );