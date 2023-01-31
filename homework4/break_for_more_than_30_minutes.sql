SELECT 	r.f1 AS `фильм 1`, r.f1_s AS `время начала`, r.f1_d AS `длительность`, 
		r.f2 AS `фильм 2`, r.f2_s AS `время начала`, timediff(r.f2_s, addtime(r.f1_s, r.f1_d)) AS `длительность перерыва`
FROM
(SELECT f.title AS f1, s.start_time AS f1_s, f.duration AS f1_d,
		LEAD(f.title) OVER (ORDER BY s.start_time) AS f2,
		LEAD(s.start_time) OVER (ORDER BY s.start_time) AS f2_s
 FROM `schedule` AS s JOIN `films` AS f ON s.film_id = f.id) AS r
 WHERE timediff(r.f2_s, addtime(r.f1_s, r.f1_d)) >= '00:30'
 ORDER BY `длительность перерыва` DESC;
