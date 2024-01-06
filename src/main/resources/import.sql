INSERT INTO addresses (id, complement, neighborhood, number, public_place, zip_code, city_id) VALUES (1, 'Complement 1', 'Neighborhood 1', 1, 'Public Place 1', '00000-000', 1), (2, 'Complement 2', 'Neighborhood 2', 2, 'Public Place 2', '00000-000', 2);
INSERT INTO states (id, name) values (1, 'State 1'), (2, 'State 2');
INSERT INTO cities (id, name, state_id) values (1, 'City 1', 1), (2, 'City 2', 2);
INSERT INTO users (id, email, name, password, created_at) values (1, 'user1@emial.com', 'user1', '112', '2020-01-01 00:00:00'), (2, 'user2@emial.com', 'user2', '112', '2020-01-01 00:00:00');
INSERT INTO payment_methods (id, description) values (1, 'Payment Method 1'), (2, 'Payment Method 2');
INSERT INTO orders (id, canceled_at, confirmed_at, created_at, delivered_at, shipping_fee, status, subtotal, total) VALUES (1, '2020-01-01 00:00:00', '2020-01-01 00:00:00', '2020-01-01 00:00:00', '2020-01-01 00:00:00', 1.00, 'delivered', 1.00, 2.00), (2, '2020-01-01 00:00:00', '2020-01-01 00:00:00', '2020-01-01 00:00:00', '2020-01-01 00:00:00', 2.00, 'delivered', 2.00, 4.00);
INSERT INTO order_items (note, quantity, total_price, unit_price, product_id) VALUES ('Note 1', 1, 1.00, 1.00, 1), ('Note 2', 2, 2.00, 2.00, 2);
INSERT INTO products (id, description, name, price, product_photo_id, active) VALUES (1, 'Description 1', 'Product 1', 1.00, 1, 1), (2, 'Description 2', 'Product 2', 2.00, 2, 1);
INSERT INTO kitchens (id, name) VALUES (1, 'Kitchen 1'), (2, 'Kitchen 2');
INSERT INTO restaurants (name, shipping_fee, kitchen_id) VALUES ('Restaurant 1', 1.00, 1), ('Restaurant 2', 2.00, 2);
INSERT INTO user_groups (id, name) VALUES (1, 'User Group 1'), (2, 'User Group 2');
INSERT INTO permissions (id, name, description) VALUES (1, 'Permission 1', 'Permission 1'), (2, 'Permission 2', 'Permission 2');
INSERT INTO product_photos (id, content_type, description, file_name, file_size) VALUES (1, 'image/png', 'Description 1', 'File Name 1', 1), (2, 'image/png', 'Description 2', 'File Name 2', 2);

