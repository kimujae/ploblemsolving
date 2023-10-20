
select food_type, rest_id, rest_name, favorites
from rest_info
where (food_type, favorites) IN 
    (
    select food_type, max(favorites)
    from rest_info
    group by food_type
    )
order by food_type desc;


