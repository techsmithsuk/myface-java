# Create the `posted_at` column (initially allow nulls)
ALTER TABLE posts ADD COLUMN posted_at datetime;

# Not ideal - set the date to now for all pre-existing posts.
UPDATE posts SET posted_at = DATE_ADD(NOW(), INTERVAL FLOOR(RAND() * -10000) SECOND) WHERE posts.posted_at is NULL;

# Finally make the column not-null, so that in the future all posts must have a datetime.
ALTER TABLE posts MODIFY COLUMN posted_at datetime NOT NULL;

SELECT * from users where last_name LIKE 'abram';