-- 코드를 입력하세요
select j.flavor
from july j left join first_half fh
on j.flavor = fh.flavor
group by flavor
order by sum(j.total_order + fh.total_order) desc
limit 3;