insert into role (role_name)
values ('Loader');
insert into role (role_name)
values ('Manager');
insert into role (role_name)
values ('Moderator');


insert into status_type (status_name)
values ('Opened');
insert into status_type (status_name)
values ('Processing');
insert into status_type (status_name)
values ('Closed');


insert into action_type (action_name)
values ('Import');
insert into action_type (action_name)
values ('Export');


insert into user (login, password,
                  first_name, middle_name, last_name,
                  role_id)
values ('loader1', '1234',
        'Dmitriy', 'Vladimirovich', 'Tsipino',
        1);
insert into user (login, password,
                  first_name, middle_name, last_name,
                  role_id)
values ('loader2', '5678',
        'Ivan', 'Yurievich', 'Kopylov',
        1);
insert into user (login, password,
                  first_name, middle_name, last_name,
                  role_id)
values ('loader3', '5555',
        'Ivan', 'Ivanovich', 'Ivanov',
        1);

insert into user (login, password,
                  first_name, middle_name, last_name,
                  role_id)
values ('manager1', '1111',
        'Maxim', 'Viktorovich', 'Lazarenko',
        2);
insert into user (login, password,
                  first_name, middle_name, last_name,
                  role_id)
values ('manager2', '2222',
        'Darya', 'Nikolaevna', 'Vasina',
        2);

insert into user (login, password,
                  first_name, middle_name, last_name,
                  role_id)
values ('moder1', '3333',
        'Nikita', 'Sergeevich', 'Zhurkin',
        3);


insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price)
values ('LG TV', 'Electric device',
        'High quality big TV', 20,
        'pieces', 25000);
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price)
values ('Intel Core I9', 'Electric device',
        'Fast and parallel processor', 50,
        'pieces', 60000);
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price)
values ('Chair', 'Furniture',
        'Comfortable and soft chair', 35,
        'pieces', 5000);
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price)
values ('Peanut', 'Food',
        'Tasty nuts', 2000,
        'kilograms', 100);
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price)
values ('Apple juice', 'Food',
        'Tasty green juice', 400,
        'liters', 120);
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price)
values ('Green tea', 'Food',
        'Chinese tea', 300,
        'packets', 80);
insert into product (title, category,
                     description, quantity_left,
                     measure_unit, price)
values ('Black tea', 'Food',
        'Indian tea', 200,
        'packets', 60);


insert into action (user_id, product_id, action_type_id, status_type_id,
                    description, quantity)
values (1, 1, 1, 2,
        'description 1', 5);
insert into action (user_id, product_id, action_type_id, status_type_id,
                    description, quantity)
values (2, 1, 2, 2,
        'description 2', 10);
insert into action (user_id, product_id, action_type_id, status_type_id,
                    description, quantity)
values (3, 2, 1, 2,
        'description 3', 10);
insert into action (user_id, product_id, action_type_id,
                    description, quantity)
values (1, 3, 2,
        'description 4', 5);
insert into action (user_id, product_id, action_type_id,
                    description, quantity)
values (1, 4, 1,
        'description 5', 100);
insert into action (user_id, product_id, action_type_id,
                    description, quantity)
values (3, 5, 1,
        'description 6', 150);


insert into action_details (action_id, seller_id)
values (1, 4);
insert into action_details (action_id, seller_id)
values (2, 5);
insert into action_details (action_id, seller_id)
values (3, 4);


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
