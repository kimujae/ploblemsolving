-- 코드를 입력하세요
#SELECT date_format(sales_date, '%Y') as YEAR, date_format(sales_date, '%m') as MONTH, gender, count(distinct b.user_id) as users
#from user_info a join online_sale b on a.user_id = b.user_id
#where gender is not null
#group by year, month, gender
#order by year asc, month asc, gender asc;





select date_format(os.sales_date, '%Y') as year , date_format(os.sales_date, '%m') as month, ui.gender, count(distinct os.user_id)
from user_info ui right join online_sale os 
on ui.user_id = os.user_id
where ui.gender is not null
group by year, month, ui.gender
order by year asc, month asc, ui.gender asc;




