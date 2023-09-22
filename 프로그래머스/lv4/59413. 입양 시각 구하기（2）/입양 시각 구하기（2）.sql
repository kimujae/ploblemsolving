-- 코드를 입력하세요
WITH RECURSIVE GET_HOUR (HOUR)
  AS (SELECT 0
       UNION ALL
      SELECT HOUR+1
        FROM GET_HOUR
       WHERE HOUR < 23
     )


SELECT g.hour , count(a.animal_id) as count
from  get_hour g  left join animal_outs a on  g.hour = hour(a.datetime) 
group by hour
order by hour asc;

# SELECT
#     A.HOUR,
#     COUNT(ANIMAL_ID)AS COUNT
# FROM
#     A
# LEFT OUTER JOIN ANIMAL_OUTS B ON
#     A.HOUR = HOUR(B.DATETIME)
# GROUP BY 
#     A.HOUR
# ORDER BY
#     A.HOUR