-- 코드를 입력하세요
# 거래완료, 중고거래총금액 
select b.user_id, b.nickname, sum(a.price) as total_sales
from used_goods_board as a
join used_goods_user as b 
on b.user_id = a.writer_id
where a.status = 'DONE'
group by b.user_id
having total_sales >= 700000
order by total_sales asc;

