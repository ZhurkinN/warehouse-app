insert into role (role_name)
values ('Loader');
insert into role (role_name)
values ('Sales manager');
insert into role (role_name)
values ('Moderator');
insert into role (role_name)
values ('Collector');
insert into role (role_name)
values ('Assistant');


insert into status_type (status_name)
values ('Opened');
insert into status_type (status_name)
values ('Processing');
insert into status_type (status_name)
values ('Closed');
insert into status_type (status_name)
values ('Declined');


insert into order_type (order_name)
values ('Import');
insert into order_type (order_name)
values ('Export');


insert into user (login, password,
                  first_name, middle_name, last_name,
                  gender, role_id)
values ('load', '$2y$10$T8Cyq6wP4IdJHqhK0psSO.EzkClVbdfr8qhrQwXcG.YVYgsPuwu5y',
        'Dmitriy', 'Vladimirovich', 'Tsipino',
        'Male', 1);
insert into user (login, password,
                  first_name, middle_name, last_name,
                  gender, role_id)
values ('loader2', '5678',
        'Ivan', 'Yurievich', 'Kopylov',
        'Other', 1);
insert into user (login, password,
                  first_name, middle_name, last_name,
                  gender, role_id)
values ('collector1', '5555',
        'Ivan', 'Ivanovich', 'Ivanov',
        'Male', 4);

insert into user (login, password,
                  first_name, middle_name, last_name,
                  gender, role_id)
values ('qwe', '$2y$10$67JrNtp517s.JSaHAybCSubWbzCnN5Dt34J7gioNK60ahRVp0FYw6',
        'Maxim', 'Viktorovich', 'Lazarenko',
        'Male', 2);
insert into user (login, password,
                  first_name, middle_name, last_name,
                  gender, role_id)
values ('manager2', '2222',
        'Daria', 'Nikolaevna', 'Vasina',
        'Female', 2);

insert into user (login, password,
                  first_name, middle_name, last_name,
                  gender, role_id)
values ('collector2', '3333',
        'Nikita', 'Sergeevich', 'Zhurkin',
        'Male', 4);
insert into user (login, password,
                  first_name, middle_name, last_name,
                  gender, role_id)
values ('ass', '$2y$10$h9NawfatyFpQ8i0srU6F1eprO6Oxhx2cNPn2Mw8E37mH9YEkdh7ae',
        'Sergey', 'Sergeevich', 'Sergeev',
        'Male', 5);


insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price, warehouse_position)
values ('LG TV', 'Electric device',
        'High quality big TV', 20,
        'шт.', 25000, 'A1');
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price, warehouse_position)
values ('Intel Core I9', 'Electric device',
        'Fast and parallel processor', 50,
        'шт.', 60000, 'A3');
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price, warehouse_position)
values ('Chair', 'Furniture',
        'Comfortable and soft chair', 35,
        'шт.', 5000, 'A5');
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price, warehouse_position)
values ('Peanut', 'Food',
        'Tasty nuts', 2000,
        'кг.', 100, 'B2');
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price, warehouse_position)
values ('Apple juice', 'Food',
        'Tasty green juice', 400,
        'л.', 120, 'C4');
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price, warehouse_position)
values ('Green tea', 'Food',
        'Chinese tea', 300,
        'шт.', 80, 'A3');
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price, warehouse_position)
values ('Black tea', 'Food',
        'Indian tea', 200,
        'шт.', 60, 'F3');


insert into `orders` (manager_id, assistant_id, order_type_id, status_type_id, is_approved,
                      description, contact_number)
values (4, 7, 1, 2, true,
        'description 1', '79238489823');
insert into `orders` (manager_id, assistant_id, order_type_id, status_type_id, is_approved,
                      description, contact_number)
values (4, 7, 2, 2, true,
        'description 2', '70038129823');
insert into `orders` (manager_id, assistant_id, order_type_id, status_type_id, is_approved,
                      description, contact_number)
values (5, 7, 1, 2, true,
        'description 3', '79658480923');
insert into `orders` (manager_id, assistant_id, order_type_id, status_type_id, is_approved,
                      description, contact_number)
values (5, 7, 2, 1, true,
        'description 4', '79274487823');
insert into `orders` (manager_id, assistant_id, order_type_id, status_type_id, is_approved,
                      description, contact_number)
values (4, 7, 1, 1, true,
        'description 5', '79232487623');
insert into `orders` (manager_id, assistant_id, order_type_id, status_type_id, is_approved,
                      description, contact_number)
values (5, 7, 1, 1, true,
        'description 6', '79223659823');


insert into order_products (order_id, product_id, quantity)
values (1, 1, 5);
insert into order_products (order_id, product_id, quantity)
values (1, 2, 5);
insert into order_products (order_id, product_id, quantity)
values (1, 3, 7);
insert into order_products (order_id, product_id, quantity)
values (2, 1, 7);
insert into order_products (order_id, product_id, quantity)
values (2, 4, 200);
insert into order_products (order_id, product_id, quantity)
values (3, 5, 50);
insert into order_products (order_id, product_id, quantity)
values (4, 4, 46);
insert into order_products (order_id, product_id, quantity)
values (5, 6, 20);
insert into order_products (order_id, product_id, quantity)
values (6, 7, 10);


insert into order_details (order_id, worker_id)
values (1, 1);
insert into order_details (order_id, worker_id)
values (2, 7);
insert into order_details (order_id, worker_id)
values (3, 2);


insert into provider (name, address, telephone_number, email)
values ('LG', 'Tokyo', '79515671287', 'lg@lg.com');
insert into provider (name, address, telephone_number, email)
values ('IKea', 'Stockholm', '79451728402', 'ikea@ikea.com');
insert into provider (name, address, telephone_number, email)
values ('Intel', 'Seoul', '79923827401', 'inel@intel.com');
insert into provider (name, address, telephone_number, email)
values ('Armenia', 'Yerevan', '89242557832', 'armenia@arm.ar');
insert into provider (name, address, telephone_number, email)
values ('Dobryi', 'Moscow', '89341238532', 'dobr@dobr.ru');
insert into provider (name, address, telephone_number, email)
values ('Akhmat', 'Moscow', '89508009393', 'axmat@axmat.ru');


insert into product_providers (product_id, provider_id)
values (1, 1);
insert into product_providers (product_id, provider_id)
values (2, 3);
insert into product_providers (product_id, provider_id)
values (3, 2);
insert into product_providers (product_id, provider_id)
values (4, 4);
insert into product_providers (product_id, provider_id)
values (5, 5);
insert into product_providers (product_id, provider_id)
values (6, 6);
insert into product_providers (product_id, provider_id)
values (7, 6);
