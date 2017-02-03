alter table CETA_CUSTOMER add column TYPE_ varchar(50) ^
update CETA_CUSTOMER set TYPE_ = 'NEW' where TYPE_ is null ;
alter table CETA_CUSTOMER alter column TYPE_ set not null ;
