# Create the `posted_at` column (initially allow nulls)
ALTER TABLE posts ADD COLUMN posted_at datetime;

# Not ideal - set the date to now for all pre-existing posts.
UPDATE posts SET posted_at = NOW();

# Finally make the column not-null, so that in the future all posts must have a datetime.
ALTER TABLE posts MODIFY COLUMN posted_at datetime NOT NULL;