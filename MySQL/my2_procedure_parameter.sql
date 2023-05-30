use my2;
delimiter //
create PROCEDURE getCusById
(IN cusNum INT(11))
BEGIN
SELECT * FROM customers WHERE customerNumber = cusNum;
END // 
call getCusById(175);

delimiter //
CREATE PROCEDURE getCustomersCountByCity(
IN in_city VARCHAR(50),
OUT total INT
)
BEGIN
SELECT COUNT(customerNumber) INTO total
FROM customers
WHERE city = in_city;
END //
call getCustomersCountByCity('Lyon', @total);
select @total;

delimiter //
CREATE PROCEDURE SetCounter(
	INOUT counter INT,
    IN inc INT
)
BEGIN
SET counter = counter + inc;
END //

SET @counter = 1;
call SetCounter(@counter,1);
call SetCounter(@counter,1);
call SetCounter(@counter,5);
select @counter;