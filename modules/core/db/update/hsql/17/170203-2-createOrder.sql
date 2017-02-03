alter table CETA_ORDER add constraint FK_CETA_ORDER_CUSTOMER foreign key (CUSTOMER_ID) references CETA_CUSTOMER(ID);
create index IDX_CETA_ORDER_CUSTOMER on CETA_ORDER (CUSTOMER_ID);
