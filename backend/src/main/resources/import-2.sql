INSERT INTO ADDRESSES(id, is_deleted, street_id, address) VALUES (1,0,1,'11/13');
INSERT INTO ADDRESSES(id, is_deleted, street_id, address) VALUES (2,0,2,'7');
INSERT INTO ADDRESSES(id, is_deleted, street_id, address) VALUES (3,0,12,'25');
INSERT INTO ADDRESSES(id, is_deleted, street_id, address) VALUES (4,0,12,'15');

INSERT INTO USERS(id, is_deleted, address_id, first_name, last_name, username, password, email, phone_number) VALUES (1,0,1,'Csiszer','Csongor','csiszercsongor', 'alma1234','cscs@gmail.com','+40751558796');
INSERT INTO USERS(id, is_deleted, address_id, first_name, last_name, username, password, email, phone_number) VALUES (2,0,2,'József','Attila','jozsi', 'alma1234','jozsefattila@gmail.com','+36705478896');
INSERT INTO USERS(id, is_deleted, address_id, first_name, last_name, username, password, email, phone_number) VALUES (3,0,3,'Nagy','Sándor','sanyi', 'alma1234','nagysandor@gmail.com','+36705489963');