INSERT INTO template.role(id, name) VALUES (1, 'ROLE_ADMIN')
INSERT INTO template.user(id, username, password) VALUES (1, 'admin', '$2a$10$sNjDiMEFQ39hhrg9b9DzzudclhukD8OM34nI1bouHoFyaUvuqRIQW');
INSERT INTO template.user_roles(user_id, roles_id) VALUES (1, 1)
