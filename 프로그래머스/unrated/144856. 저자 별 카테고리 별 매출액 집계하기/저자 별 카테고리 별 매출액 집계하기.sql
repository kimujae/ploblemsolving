# -- 코드를 입력하세요
select b.author_id, b.author_name, a.category, sum(a.price * d.sales) as total_sales
from book_sales d 
join book a on d.book_id = a.book_id
join author b on a.author_id = b.author_id
where d.sales_date like "2022-01%"
group by b.author_id ,a.category
order by b.author_id asc, a.category DESC;