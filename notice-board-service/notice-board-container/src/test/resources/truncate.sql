-- truncate.sql (or your cleanup script)
SET REFERENTIAL_INTEGRITY FALSE; -- Disable foreign key checks

-- TRUNCATE TABLE statements for your tables
TRUNCATE TABLE comment; -- Assuming 'comment' is your comment table
TRUNCATE TABLE post;    -- Assuming 'post' is your post table
-- Add more TRUNCATE TABLE statements for other tables as needed

SET REFERENTIAL_INTEGRITY TRUE; -- Re-enable foreign key checks