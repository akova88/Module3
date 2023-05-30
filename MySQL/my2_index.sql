SELECT * FROM customers WHERE customerName = 'Land of Toys Inc.';
EXPLAIN SELECT * FROM customers WHERE customerName = 'Land of Toys Inc.';
alter table customers add index idx_customerName(customerName);
alter table customers add index idx_full_name(contactFirstName, contactLastName);
SELECT * from customers WHERE contactFirstName = 'Jean' or contactFirstName = 'King';
alter table customers drop index idx_full_name;