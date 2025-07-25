-- truncate.sql (or your cleanup script)
SET FOREIGN_KEY_CHECKS = 0; -- Disable foreign key checks

-- TRUNCATE TABLE statements for your tables
TRUNCATE TABLE comment; -- Assuming 'comment' is your comment table
TRUNCATE TABLE post;    -- Assuming 'post' is your post table
-- Add more TRUNCATE TABLE statements for other tables as needed

SET FOREIGN_KEY_CHECKS = 1; -- Re-enable foreign key checks