INSERT INTO users (id, username, password, email, address, is_admin) VALUES
(1, 'admin', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'admin@chicken.com', '123 Admin Street', true),
(2, 'john', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a', 'john@email.com', '456 Customer Ave', false);

INSERT INTO orders (id, user_id, delivery_address, order_date, status) VALUES
(1, 2, '456 Customer Ave, Apt 2B', '2023-07-01 10:30:00', 'PENDING'),
(2, 2, '789 New Address, Suite 5', '2023-07-02 14:45:00', 'COMPLETED');