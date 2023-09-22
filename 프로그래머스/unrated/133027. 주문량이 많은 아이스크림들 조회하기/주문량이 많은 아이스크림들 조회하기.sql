-- 코드를 입력하세요
with j as
(
select sum(total_order) as total_order, flavor
from july
group by flavor
)


SELECT j.flavor
from first_half fh join
j on fh.flavor = j.flavor
order by j.total_order + fh.total_order desc
limit 3;