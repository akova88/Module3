delimiter //
create procedure toDeposit(
IN cash double,
IN customerId int
)
begin
Declare createdAt DateTime;
Declare balance1 double;
set @createdAt = now();
 
INSERT INTO deposits (`created_at`,`customer_id`, `transaction_amount`) 
VALUES (@createdAt, customerId, cash);

select balance into balance1 from customers where `id` = customerId;
update customers set balance = (balance1 + cash) where `id` = customerId;
end //
drop procedure toWithdraws;
delimiter //
create procedure toWithdraws(
IN customerId int,
IN money double
)
begin
	declare balance1 double;
    select balance into balance1 
    from `customers` WHERE `id` = customerId;
    
    if (balance1 >= money) then
		INSERT INTO withdraws (`created_at`,`customer_id`, `transaction_amount`) 
		VALUES (Date(now()), customerId, money);
		
		update customers set balance = (balance1 - money) where `id` = customerId;
    end if;
end //

drop procedure toTransfers;
delimiter //
create procedure toTransfers(
IN  idSend int,
IN 	idRecip int,
IN 	moneyTrans double
)
begin
declare balance1 double;
declare balance2 double;
	select balance into balance1 
    from `customers` WHERE `id` = idSend;
    
    select balance into balance2 
    from `customers` WHERE `id` = idRecip;
    
    if (balance1 >= moneyTrans*1.1) then
    
		INSERT INTO `transfers` (`created_at`, `fees`, `fees_amount`, `transaction_amount`, `transfer_amount`, `recipient_id`, `sender_id`) 
        VALUES (Date(now()), '0.1', moneyTrans*0.1, moneyTrans*1.1, moneyTrans, idRecip, idSend);
		
		update customers set balance = (balance1 - (moneyTrans*1.1)) where `id` = idSend;
        update customers set balance = (balance2 + moneyTrans) where `id` = idRecip;
    end if;
end //





