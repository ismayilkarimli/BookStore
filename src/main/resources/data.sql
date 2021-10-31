INSERT INTO Author( id, name, last_name ) values ( 1, 'Mark', 'Twain' );
INSERT INTO Author( id, name, last_name ) values ( 2, 'Ray', 'Dalio' );
INSERT INTO Author( id, name, last_name ) values ( 3, 'Lev', 'Tolstoy' );

INSERT INTO Book( id, title,release_date, page_count, created_at, updated_at ) values ( 1, 'Principles', NOW(), 592, NOW(), NOW() );

INSERT INTO Author_Book( author_id, book_id ) values ( 2, 1 );