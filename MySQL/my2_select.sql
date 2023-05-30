-- in ra cac chuc danh cua doanh nghiep
select distinct jobTitle
from employees;

-- lay ra thong tin cua tat ca khach hang
select * from customers;

-- lay ra thong tin cua tat ca san pham co gia lon hon 30
select * from products
where buyPrice > 30;

-- lấy ra tất cả thông tin khách hàng ở USA
select * from customers
where Country = 'USA';

-- Thống kê các quốc gia không có nhân viên hỗ trợ
select distinct country
from customers
where salesRepEmployeeNumber is null;

-- In ra tên và số lượng 5sp có số lượng tồn kho lớn nhất
select productName, 
	   sum(quantityInStock) as tongXe
from products
group by productName
order by tongXe desc
limit 5;

-- in ra 5 loại sp có chênh lệch giá sx & giá bán

-- in ra các trạng thái đơn hàng
select distinct status
from orders;

-- in ra tổng số đơn được đặt trong tháng 5/2005
select count(orderNumber) as tongDonHang
from orders
where orderDate
between '2005-05-01' and '2005-05-31'; 

select * from orders o;
-- sắp xếp theo giá
select productName, buyPrice, MSRP
from products
order by buyPrice asc;

select productName, buyPrice, MSRP
from products
where MSRP >= 100
order by MSRP asc;

select lastName, firstName, jobTitle
from employees
order by firstName
limit 2,5;  -- lấy 5 ptu từ ptu 3 
