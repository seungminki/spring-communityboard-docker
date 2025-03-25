INSERT INTO users (name, email, password)
VALUES
  ('George', 'george@gmail.com', 'password'),
  ('Alice', 'alice@example.com', 'password'),
  ('Bob', 'bob@example.com', 'password');

INSERT INTO posts(title, content, user_id)
VALUES
  ('Test Data Entry', 'This is a sample text for database testing.', 1),
  ('Test Data Entry', 'This is a sample text for database testing.', 2),
  ('Test Data Entry', 'This is a sample text for database testing.', 1);

INSERT INTO comments(post_id, user_id, content)
VALUES
  (1,1, 'This is a sample text'),
  (2,2, 'This is a sample text'),
  (2,2, 'This is a sample text');
