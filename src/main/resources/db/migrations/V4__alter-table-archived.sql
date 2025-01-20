alter table topics add is_archived tinyint;
update topics set is_archived = 0;

alter table responses add is_archived tinyint;
update responses set is_archived = 0;