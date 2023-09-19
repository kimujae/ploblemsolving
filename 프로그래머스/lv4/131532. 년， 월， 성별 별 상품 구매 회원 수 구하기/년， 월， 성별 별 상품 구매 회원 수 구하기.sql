-- 코드를 입력하세요
SELECT date_format(sales_date, '%Y') as YEAR, date_format(sales_date, '%m') as MONTH, gender, count(distinct b.user_id) as users
from user_info a join online_sale b on a.user_id = b.user_id
where gender is not null
group by year, month, gender
order by year asc, month asc, gender asc;