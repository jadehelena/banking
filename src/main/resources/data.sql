INSERT INTO ROLE(id, name) VALUES (1, 'admin');
INSERT INTO USER(id, name, password) VALUES (1, 'admin', '$2a$10$sFKmbxbG4ryhwPNx/l3pgOJSt.fW1z6YcUnuE2X8APA/Z3NI/oSpq');
INSERT INTO USER_ROLES(user_id, roles_id) VALUES (1, 1);