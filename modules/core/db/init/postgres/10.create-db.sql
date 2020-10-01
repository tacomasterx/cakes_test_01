-- begin CAKES_STORE
create table CAKES_STORE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    CODE varchar(255),
    --
    primary key (ID)
)^
-- end CAKES_STORE
-- begin CAKES_CAKE
create table CAKES_CAKE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    PRICE decimal(19, 2),
    PRICE_GROUP_ID uuid,
    WEIGHT integer,
    SKU bigint,
    --
    primary key (ID)
)^
-- end CAKES_CAKE
-- begin CAKES_REFRIGERATOR
create table CAKES_REFRIGERATOR (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    STORE_ID uuid,
    --
    primary key (ID)
)^
-- end CAKES_REFRIGERATOR
-- begin CAKES_PRICE_GORUP
create table CAKES_PRICE_GORUP (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end CAKES_PRICE_GORUP
-- begin CAKES_CAKE_INVENTORY
create table CAKES_CAKE_INVENTORY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CAKE_ID uuid,
    STATUS integer,
    STORE_ID uuid,
    REFRIGERATOR_ID uuid,
    --
    primary key (ID)
)^
-- end CAKES_CAKE_INVENTORY
