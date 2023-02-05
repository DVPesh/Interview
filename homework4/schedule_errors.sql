SELECT 	f1 AS `фильм 1`, f1_s AS `время начала`, f1_d AS `длительность`, 
		f2 AS `фильм 2`, f2_s AS `время начала`, f2_d AS `длительность` 
FROM
(SELECT f.title AS f1, s.start_time AS f1_s, f.duration AS f1_d,
		LEAD(f.title) OVER (ORDER BY s.start_time) AS f2,
		LEAD(s.start_time) OVER (ORDER BY s.start_time) AS f2_s,
		LEAD(f.duration) OVER (ORDER BY s.start_time) AS f2_d
 FROM `schedule` AS s JOIN `films` AS f ON s.film_id = f.id) AS r
 WHERE r.f2_s < addtime(r.f1_s, r.f1_d);
