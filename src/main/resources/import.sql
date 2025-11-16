-- Hibernate автоматически выполнит этот файл после создания таблиц
-- Вставка категорий (одна строка на INSERT)
INSERT INTO categories (id, name, description) VALUES (1, 'Programming', 'Software development courses');
INSERT INTO categories (id, name, description) VALUES (2, 'Design', 'UI/UX design courses');
INSERT INTO categories (id, name, description) VALUES (3, 'Business', 'Business and management courses');

-- Вставка тегов
INSERT INTO tags (id, name) VALUES (1, 'Java');
INSERT INTO tags (id, name) VALUES (2, 'Spring Boot');
INSERT INTO tags (id, name) VALUES (3, 'Hibernate');
INSERT INTO tags (id, name) VALUES (4, 'JavaScript');
INSERT INTO tags (id, name) VALUES (5, 'React');
INSERT INTO tags (id, name) VALUES (6, 'Database');

-- Вставка пользователей
INSERT INTO users (id, name, email, role, created_at) VALUES (1, 'John Teacher', 'john.teacher@example.com', 'TEACHER', CURRENT_TIMESTAMP);
INSERT INTO users (id, name, email, role, created_at) VALUES (2, 'Jane Instructor', 'jane.instructor@example.com', 'TEACHER', CURRENT_TIMESTAMP);
INSERT INTO users (id, name, email, role, created_at) VALUES (3, 'Student One', 'student1@example.com', 'STUDENT', CURRENT_TIMESTAMP);
INSERT INTO users (id, name, email, role, created_at) VALUES (4, 'Student Two', 'student2@example.com', 'STUDENT', CURRENT_TIMESTAMP);

-- Вставка профилей
INSERT INTO profiles (id, user_id, bio, avatar_url) VALUES (1, 1, 'Experienced Java developer and instructor', '/avatars/john.jpg');
INSERT INTO profiles (id, user_id, bio, avatar_url) VALUES (2, 2, 'Web development specialist', '/avatars/jane.jpg');

-- Вставка курсов
INSERT INTO courses (id, title, description, teacher_id, category_id, duration, start_date, end_date, price, level, created_at) VALUES (1, 'Java Fundamentals', 'Learn Java programming basics', 1, 1, 40, '2024-01-15', '2024-04-15', 99.99, 'BEGINNER', CURRENT_TIMESTAMP);
INSERT INTO courses (id, title, description, teacher_id, category_id, duration, start_date, end_date, price, level, created_at) VALUES (2, 'Spring Boot Masterclass', 'Advanced Spring Boot development', 1, 1, 60, '2024-02-01', '2024-05-01', 149.99, 'ADVANCED', CURRENT_TIMESTAMP);
INSERT INTO courses (id, title, description, teacher_id, category_id, duration, start_date, end_date, price, level, created_at) VALUES (3, 'Web Development', 'Full-stack web development', 2, 1, 50, '2024-01-20', '2024-04-20', 129.99, 'INTERMEDIATE', CURRENT_TIMESTAMP);

-- Связи курсов с тегами
INSERT INTO course_tags (course_id, tag_id) VALUES (1, 1);
INSERT INTO course_tags (course_id, tag_id) VALUES (1, 2);
INSERT INTO course_tags (course_id, tag_id) VALUES (1, 3);
INSERT INTO course_tags (course_id, tag_id) VALUES (2, 2);
INSERT INTO course_tags (course_id, tag_id) VALUES (2, 3);
INSERT INTO course_tags (course_id, tag_id) VALUES (3, 4);
INSERT INTO course_tags (course_id, tag_id) VALUES (3, 5);
INSERT INTO course_tags (course_id, tag_id) VALUES (3, 6);

-- Записи на курсы
INSERT INTO enrollments (id, student_id, course_id, status, enroll_date, progress) VALUES (1, 3, 1, 'ACTIVE', CURRENT_TIMESTAMP, 0.3);
INSERT INTO enrollments (id, student_id, course_id, status, enroll_date, progress) VALUES (2, 4, 1, 'ACTIVE', CURRENT_TIMESTAMP, 0.5);
INSERT INTO enrollments (id, student_id, course_id, status, enroll_date, progress) VALUES (3, 3, 2, 'ACTIVE', CURRENT_TIMESTAMP, 0.1);