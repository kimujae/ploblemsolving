-- 코드를 입력하세요
SELECT fh.flavor from first_half fh left join icecream_info ii on fh.flavor = ii.flavor where fh.total_order > 3000 and ii.ingredient_type = 'fruit_based' order by fh.total_order desc;