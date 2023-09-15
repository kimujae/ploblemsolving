-- 코드를 입력하세요
SELECT ii.ingredient_type, sum(fh.total_order) 
from first_half fh left join icecream_info ii on fh.flavor = ii.flavor
group by ii.ingredient_type
order by total_order asc;