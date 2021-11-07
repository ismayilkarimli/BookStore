INSERT INTO Author( id, name, last_name, created_at, updated_at ) values ( 10, 'Mark', 'Twain', NOW(), NOW() );
INSERT INTO Author( id, name, last_name, created_at, updated_at ) values ( 20, 'Ray', 'Dalio', NOW(), NOW() );
INSERT INTO Author( id, name, last_name, created_at, updated_at ) values ( 30, 'Leo', 'Tolstoy', NOW(), NOW() );
INSERT INTO Author( id, name, last_name, created_at, updated_at ) values ( 40, 'Napoleon', 'Hill', NOW(), NOW() );
INSERT INTO Author( id, name, last_name, created_at, updated_at ) values ( 50, 'Viktor', 'Frankl', NOW(), NOW() );
INSERT INTO Author( id, name, last_name, created_at, updated_at ) values ( 60, 'Joshua', 'Bloch', NOW(), NOW() );
INSERT INTO Author( id, name, last_name, created_at, updated_at ) values ( 70, 'Bert', 'Bates', NOW(), NOW() );
INSERT INTO Author( id, name, last_name, created_at, updated_at ) values ( 80, 'Kathy', 'Sierra', NOW(), NOW() );
INSERT INTO Author( id, name, last_name, created_at, updated_at ) values ( 90, 'Andy', 'Hunt', NOW(), NOW() );
INSERT INTO Author( id, name, last_name, created_at, updated_at ) values ( 100, 'Dave', 'Thomas', NOW(), NOW() );


INSERT INTO Book( id, isbn, title,release_date, page_count, created_at, updated_at ) values ( 10, '0-545-01022-1', 'Principles', '2017-09-19', 592, NOW(), NOW() );
INSERT INTO Book( id, isbn, title,release_date, page_count, created_at, updated_at ) values ( 20, '0-545-01022-2', 'The Death of Ivan Ilyich', '1986-01-01', 92, NOW(), NOW() );
INSERT INTO Book( id, isbn, title,release_date, page_count, created_at, updated_at ) values ( 30, '0-545-01022-3', 'Think and Grow Rich', '1937-01-01', 238, NOW(), NOW() );
INSERT INTO Book( id, isbn, title,release_date, page_count, created_at, updated_at ) values ( 40, '0-545-01022-4', 'Mans Searh for Meaning', '1946-01-01', 200, NOW(), NOW() );
INSERT INTO Book( id, isbn, title,release_date, page_count, created_at, updated_at ) values ( 50, '0-545-01022-5', 'Head First: Java', '2003-01-01', 722, NOW(), NOW() );
INSERT INTO Book( id, isbn, title,release_date, page_count, created_at, updated_at ) values ( 60, '0-545-01022-6', 'Effective Java', '2001-01-01', 328, NOW(), NOW() );
INSERT INTO Book( id, isbn, title,release_date, page_count, created_at, updated_at ) values ( 70, '0-545-01022-7', 'Pragmatic Programmer', '1999-10-01', 320, NOW(), NOW() );
INSERT INTO Book( id, isbn, title,release_date, page_count, created_at, updated_at ) values ( 80, '0-545-01022-8', 'War and Peace', '1867-01-01', 1225, NOW(), NOW() );
INSERT INTO Book( id, isbn, title,release_date, page_count, created_at, updated_at ) values ( 90, '0-545-01022-9',  'Anna Karenina', '1877-01-01', 864, NOW(), NOW() );
INSERT INTO Book( id, isbn, title,release_date, page_count, created_at, updated_at ) values ( 100, '0-545-01022-0', 'The Adventures of Tom Sawyer', '1876-01-01', 274, NOW(), NOW() );

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