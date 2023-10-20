-- 코드를 입력하세요
select fd.product_id, fd.product_name, (fd.price * sum(fo.amount))total_sales
from food_product fd left join food_order fo
on fd.product_id = fo.product_id
where fo.produce_date like '2022-05%'
group by fd.product_id
order by total_sales desc, fd.product_id asc;