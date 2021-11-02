INSERT INTO Author( id, name, last_name ) values ( 10, 'Mark', 'Twain' );
INSERT INTO Author( id, name, last_name ) values ( 20, 'Ray', 'Dalio' );
INSERT INTO Author( id, name, last_name ) values ( 30, 'Leo', 'Tolstoy' );
INSERT INTO Author( id, name, last_name ) values ( 40, 'Napoleon', 'Hill' );
INSERT INTO Author( id, name, last_name ) values ( 50, 'Viktor', 'Frankl' );
INSERT INTO Author( id, name, last_name ) values ( 60, 'Joshua', 'Bloch' );
INSERT INTO Author( id, name, last_name ) values ( 70, 'Bert', 'Bates' );
INSERT INTO Author( id, name, last_name ) values ( 80, 'Kathy', 'Sierra' );
INSERT INTO Author( id, name, last_name ) values ( 90, 'Andy', 'Hunt' );
INSERT INTO Author( id, name, last_name ) values ( 100, 'Dave', 'Thomas' );


INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 10, 'Principles', NOW(), 592, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 20, 'The Death of Ivan Ilyich', NOW(), 92, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 30, 'Think and Grow Rich', NOW(), 280, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 40, 'Mans Searh for Meaning', NOW(), 128, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 50, 'Head First: Java', NOW(), 420, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 60, 'Effective Java', NOW(), 328, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 70, 'Pragmatic Programmer', NOW(), 218, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 80, 'War and Peace', NOW(), 1001, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 90, 'Anna Karenina', NOW(), 492, NOW(), NOW() );
INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 100, 'The Adventures of Tom Sawyer', NOW(), 621, NOW(), NOW() );

INSERT INTO Author_Book( author_id, book_id ) values ( 10, 100 );
INSERT INTO Author_Book( author_id, book_id ) values ( 20, 10 );
INSERT INTO Author_Book( author_id, book_id ) values ( 40, 30 );
INSERT INTO Author_Book( author_id, book_id ) values ( 50, 40 );
INSERT INTO Author_Book( author_id, book_id ) values ( 60, 60 );
INSERT INTO Author_Book( author_id, book_id ) values ( 70, 50 );
INSERT INTO Author_Book( author_id, book_id ) values ( 80, 50 );
INSERT INTO Author_Book( author_id, book_id ) values ( 90, 70 );
INSERT INTO Author_Book( author_id, book_id ) values ( 100, 70 );
INSERT INTO Author_Book( author_id, book_id ) values ( 30, 20 );
INSERT INTO Author_Book( author_id, book_id ) values ( 30, 80 );
INSERT INTO Author_Book( author_id, book_id ) values ( 30, 90 );