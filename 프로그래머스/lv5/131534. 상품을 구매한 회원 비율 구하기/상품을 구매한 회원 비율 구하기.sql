-- 코드를 입력하세요
with user2021 as
    (
    SELECT user_id 
    from user_info
    where joined like '2021%'
    ),

user2021count as
    (
    select count(user_id) as user2021counts
    from user_info
    where joined like '2021%'
    )
    
SELECT 
    YEAR(online_sale.sales_date) AS year,
    MONTH(online_sale.sales_date) AS month,
    COUNT(distinct online_sale.user_id) AS puchased_users,
    round(COUNT(distinct online_sale.user_id) / user2021count.user2021counts , 1) AS puchased_ratio
FROM online_sale
JOIN user2021 ON online_sale.user_id = user2021.user_id
JOIN user2021count ON 1=1
GROUP BY YEAR(online_sale.sales_date), MONTH(online_sale.sales_date)
ORDER BY year ASC, month ASC;