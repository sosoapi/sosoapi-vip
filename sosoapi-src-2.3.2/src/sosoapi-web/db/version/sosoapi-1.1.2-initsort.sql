/*
重置t_module中的排序权重
*/
SET @rn = 0;
SET @did = 0;

UPDATE t_module m
INNER JOIN (
	SELECT
		id,
		doc_id,
		sort_weight,
		CASE
	WHEN (@did = doc_id) THEN
		@rn :=@rn + 1
	ELSE
		@rn := 1
	END rownum,
	@did := doc_id AS did
FROM
	t_module
ORDER BY
	doc_id ASC,
	sort_weight ASC,
	create_date ASC
) tmp ON m.id = tmp.id
SET m.sort_weight = tmp.rownum;


/*
重置t_inter中的排序权重
*/
SET @rn = 0;
SET @mid = 0;
set @did = 0;

update t_inter ti inner join
(SELECT
	i.id,
	i.doc_id,
	i.module_id,
	i.sort_weight,
	CASE
		WHEN (@mid = ifnull(i.module_id,-1) and @did = i.doc_id)
			THEN
				@rn :=@rn + 1
			ELSE
				@rn := 1
	END rownum,
	@mid := ifnull(i.module_id,-1) as mid,
  @did := i.doc_id as did
FROM
	t_inter i
LEFT JOIN t_module m ON i.module_id = m.id
ORDER BY
	i.doc_id ASC,
	i.module_id ASC,
	i.sort_weight ASC,
	i.modify_date DESC) tmp
on ti.id = tmp.id
set ti.sort_weight = tmp.rownum;


/*
重置t_inter_param中的排序权重
*/
SET @rn = 0;
SET @iid = 0;

UPDATE t_inter_param ip
INNER JOIN (
	SELECT
		id,
		doc_id,
		inter_id,
		sort_weight,
		CASE
	WHEN (@iid = ifnull(inter_id ,- 1)) THEN
		@rn :=@rn + 1
	ELSE
		@rn := 1
	END rownum,
	@iid := ifnull(inter_id ,- 1) AS iid
FROM
	t_inter_param
ORDER BY
	doc_id ASC,
	inter_id ASC,
	sort_weight ASC,
	create_date ASC
) tmp ON ip.id = tmp.id
SET ip.sort_weight = tmp.rownum;


/*
重置t_inter_resp中的排序权重
*/
SET @rn = 0;
SET @iid = 0;

UPDATE t_inter_resp ir
INNER JOIN (
	SELECT
		id,
		doc_id,
		inter_id,
		sort_weight,
		CASE
	WHEN (@iid = ifnull(inter_id ,- 1)) THEN
		@rn :=@rn + 1
	ELSE
		@rn := 1
	END rownum,
	@iid := ifnull(inter_id ,- 1) AS iid
FROM
	t_inter_resp
ORDER BY
	doc_id ASC,
	inter_id ASC,
	sort_weight ASC,
	create_date ASC
) tmp ON ir.id = tmp.id
SET ir.sort_weight = tmp.rownum;
