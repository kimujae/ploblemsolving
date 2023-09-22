-- 코드를 입력하세요
with a as
(
SELECT car_id, '대여중' as AVAILABILITY
from car_rental_company_rental_history
where start_date < '2022-10-17' and end_date > '2022-10-15'
)

(
    select car_id, '대여 가능' as AVAILABILITY
    from car_rental_company_rental_history
    where car_id not in (select car_id from a)

    union

    select car_id, AVAILABILITY
    from a
) order by car_id desc;