USE QuanLyBanHang;
INSERT INTO customer
VALUES 	(1, 'Minh Quan', 10 ),
		(2, 'Ngoc Oanh', 20),
		(3, 'Hong Ha', 50);
        
INSERT INTO `order`
VALUES 	(1, 1, '2006/3/21', null),
		(2, 2, '2006/3/23', null),
		(3, 1, '2006/3/16', null);

INSERT INTO `Product`
VALUES 	(1, 'May Giat', 3),
		(2, 'Tu Lanh', 5),
		(3, 'Dieu Hoa', 5),
		(4, 'Quat', 5),
		(5, 'Bep Dien', 5);
        
INSERT INTO `orderdetail`
VALUES	(1,1,3),
		(1,3,7),
		(1,4,2),
		(2,1,1),
		(3,1,8),
		(2,5,4),
		(2,3,3);
        
-- Hiển thị các thông tin  gồm oID, oDate, oPrice của tất cả các hóa đơn trong bảng Order
SELECT o.oID, o.oDate, o.oTotalPrice
FROM `order` o;
-- Hiển thị danh sách các khách hàng đã mua hàng, và danh sách sản phẩm được mua bởi các khách
SELECT c.cName, p.pName, o.oID
FROM customer c join `order` o on c.cID = o.cID 
				join orderdetail od on od.oID = o.oID
                JOIN product p on p.pID = od.pID;
-- Hiển thị tên những khách hàng không mua bất kỳ một sản phẩm nào
SELECT c.cName
FROM customer c LEFT JOIN `order` o on c.cID = o.cID
WHERE o.cID is NULL;
-- Hiển thị mã hóa đơn, ngày bán và giá tiền của từng hóa đơn 
-- (giá một hóa đơn được tính bằng tổng giá bán của từng loại mặt hàng xuất hiện trong hóa đơn. 
-- Giá bán của từng loại được tính = odQTY*pPrice)
SELECT o.oID, o.oDate, sum(od.odQTY * p.pPrice) as ToTal
FROM `order` o 	join orderdetail od on o.oID = od.oID
				join product p on p.pID = od.pID
GROUP BY o.oID;

