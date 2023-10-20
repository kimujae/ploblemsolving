select bb.author_id, a.author_name, bb.category, bb.total_sales
from author a right join 
(
    select b.author_id, b.category, sum(b.price * bs.sales) as total_sales
    from book b left join book_sales bs
    on b.book_id = bs.book_id
    where bs.sales_date like '2022-01%'
    group by b.author_id, b.category
) bb    
on a.author_id = bb.author_id
order by bb.author_id asc, bb.category desc;