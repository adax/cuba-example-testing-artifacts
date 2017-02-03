-- begin CETA_CUSTOMER
create table CETA_CUSTOMER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    FIRST_NAME varchar(255),
    TYPE_ varchar(50) not null,
    --
    primary key (ID)
)^
-- end CETA_CUSTOMER
-- begin CETA_ORDER
create table CETA_ORDER (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    ORDER_DATE date not null,
    CUSTOMER_ID varchar(36) not null,
    --
    primary key (ID)
)^
-- end CETA_ORDER
