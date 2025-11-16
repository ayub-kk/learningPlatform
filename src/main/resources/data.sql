-- Insert categories
INSERT INTO categories (name, description) VALUES
                                               ('Programming', 'Software development courses'),
                                               ('Design', 'UI/UX design courses'),
                                               ('Business', 'Business and management courses');

-- Insert tags
INSERT INTO tags (name) VALUES
                            ('Java'), ('Spring Boot'), ('Hibernate'),
                            ('JavaScript'), ('React'), ('Database');

-- Insert users
INSERT INTO users (name, email, role, created_at) VALUES
                                                      ('John Teacher', 'john.teacher@example.com', 'TEACHER', CURRENT_TIMESTAMP),
                                                      ('Jane Instructor', 'jane.instructor@example.com', 'TEACHER', CURRENT_TIMESTAMP),
                                                      ('Student One', 'student1@example.com', 'STUDENT', CURRENT_TIMESTAMP),
                                                      ('Student Two', 'student2@example.com', 'STUDENT', CURRENT_TIMESTAMP);

-- Insert profiles
INSERT INTO profiles (user_id, bio, avatar_url) VALUES
                                                    (1, 'Experienced Java developer and instructor', '/avatars/john.jpg'),
                                                    (2, 'Web development specialist', '/avatars/jane.jpg');

-- Insert courses
INSERT INTO courses (title, description, teacher_id, category_id, duration, start_date, end_date, price, level, created_at) VALUES
                                                    ('Java Fundamentals', 'Learn Java programming basics', 1, 1, 40, '2024-01-15', '2024-04-15', 99.99, 'BEGINNER', CURRENT_TIMESTAMP),
                                                    ('Spring Boot Masterclass', 'Advanced Spring Boot development', 1, 1, 60, '2024-02-01', '2024-05-01', 149.99, 'ADVANCED', CURRENT_TIMESTAMP),
                                                    ('Web Development', 'Full-stack web development', 2, 1, 50, '2024-01-20', '2024-04-20', 129.99, 'INTERMEDIATE', CURRENT_TIMESTAMP);