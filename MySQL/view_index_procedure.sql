CREATE DATABASE demo1;
USE demo1;
CREATE TABLE products(
id int not null PRIMARY KEY AUTO_INCREMENT,
productCode int,
productName VARCHAR(45),
productPrice FLOAT,
productAmount int,
productDescription VARCHAR(50),
productStatus VARCHAR(20)
);
INSERT INTO products
VALUES 	('1', 'A001', 'Iphone1', '300', '5', 'Điện thoại', 'new'),
		('2', 'A002', 'Iphone2', '400', '11', 'Điện thoại', 'new'),
        ('3', 'A003', 'Iphone3', '500', '6', 'Điện thoại', 'new'),
        ('4', 'A004', 'Iphone4', '600', '8', 'Điện thoại', 'new'),
        ('5', 'A005', 'Iphone5', '700', '10', 'Điện thoại', 'new');

EXPLAIN SELECT * FROM products;
-- Tạo Unique Index trên bảng Products (sử dụng cột productCode để tạo chỉ mục)
ALTER TABLE products ADD INDEX idx_productCode(productCode);
alter table products drop index idx_productCode;
EXPLAIN SELECT * FROM products WHERE productCode = 'A003';
alter table products add index idx_product(productName, productPrice);
EXPLAIN SELECT * FROM products WHERE productName = 'Iphone5';
-- Tạo view lấy về các thông tin: productCode, productName, productPrice, productStatus từ bảng products.
create view products_view as
select productCode, productName, productPrice
FROM products;
select * from products_view;
-- Tiến hành sửa đổi view
create or replace view products_view as
select productCode, productName, productPrice, productAmount, productStatus
from products
where productStatus = 'new';
select * from products_view;
-- Tiến hành xóa view
drop view products_view;
-- Tạo store procedure lấy tất cả thông tin của tất cả các sản phẩm trong bảng product
delimiter //
create procedure showProduct()
begin
select * from products;
end //
delimiter ;
call showProduct();
-- Tạo store procedure thêm một sản phẩm mới
delimiter //
create procedure addProduct(
in newProductCode VARCHAR(20),
in newProductName VARCHAR(45),
in newProductPrice FLOAT,
in newProductAmount int,
in newProductDescription VARCHAR(50),
in newProductStatus VARCHAR(20)
)
begin
	insert into products(productCode, productName, productPrice, productAmount, productDescription, productStatus)
	value (newProductCode, newProductName, newProductPrice, newProductAmount, newProductDescription, newProductStatus);
end //
delimiter ;
call addProduct('A006', 'Iphone6', 800, 15, 'Điện thoại', 'new');
-- Tạo store procedure sửa thông tin sản phẩm theo id
drop procedure if exists editProductById;
delimiter //
create procedure editProductById(
	IN newId int,
	IN newProductCode VARCHAR(20),
	IN newProductName VARCHAR(45),
	IN newProductPrice FLOAT,
	IN newProductAmount int,
	IN newProductDescription VARCHAR(50),
	IN newProductStatus VARCHAR(20)
)
begin
	declare has_error int default 0;
	declare error_message Varchar(255);
		if(not exists (select * from products where id = newId )) then
			set has_error = 1;
			set error_message = concat('ID sản phẩm', newId ,'ko tồn tại');
		end if;
        if(exists (select * from products where productCode = newProductCode)) then
			set has_error = 1;
            set error_message = concat('Code sp', newProductCode,'đã tồn tại');
        end if;
        if newProductPrice <= 0 then
			set has_error = 1;
			set error_message = 'Giá không hợp lệ! Nhập lại';
		end if;
        if newProductAmount < 0 then
			set has_error = 1;
			set error_message = 'Số lượng âm! Nhập lại';
		end if;
        if has_error = 0 then
			update products
            set  
            productCode = newProductCode,
            productName = newProductName,
            productPrice = newProductPrice,
            productAmount = newProductAmount,
            productDescription = newProductDescription,
            productStatus = newProductStatus
            where id = newId;
		end if;
        IF has_error = 1 THEN
			-- Trả về thông tin lỗi
			SELECT has_error AS 'error', error_message AS 'message';
		ELSE
			-- Trả về thông tin chỉnh sửa
			SELECT has_error AS 'error', CONCAT('Đã chỉnh sửa thông tin sp có mã ', newId) AS 'message';
		END IF;	
end //
delimiter ;

call editProductById(5, 'A004', 'fdf', 343, 4, 'fdsf', 'dsfds');
-- Tạo store procedure xoá sản phẩm theo id
delimiter //
create procedure delProductById(
	IN idDel int,
    OUT message varchar(255)
)
begin
	if ( not exists ( select * from products where id = idDel)) then
		set message = 'Không tìm thấy id';
        select message;
	else
		DELETE FROM products WHERE id = idDel;
        set message = concat('Xóa sp có id', idDel, 'thành công!');
        select message;
	end if;
end //
delimiter ;
call delProductById(2, @message);
