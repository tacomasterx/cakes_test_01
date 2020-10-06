-- begin CAKES_PRICE_GORUP
create table CAKES_PRICE_GORUP (
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
    --
    primary key (ID)
)^
-- end CAKES_PRICE_GORUP
-- begin CAKES_CAKE
create table CAKES_CAKE (
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
    PRICE decimal(19, 2),
    PRICE_GROUP_ID varchar(36),
    WEIGHT integer,
    SKU bigint,
    GROUP_SKU varchar(7),
    --
    primary key (ID)
)^
-- end CAKES_CAKE
-- begin CAKES_CAKE_INVENTORY
create table CAKES_CAKE_INVENTORY (
    ID varchar(36) not null,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    CAKE_ID varchar(36),
    STATUS integer,
    STORE_ID varchar(36),
    REFRIGERATOR_ID varchar(36),
    --
    primary key (ID)
)^
-- end CAKES_CAKE_INVENTORY
-- begin CAKES_STORE
create table CAKES_STORE (
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
    CODE varchar(255),
    --
    primary key (ID)
)^
-- end CAKES_STORE
-- begin CAKES_REFRIGERATOR
create table CAKES_REFRIGERATOR (
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
    STORE_ID varchar(36),
    --
    primary key (ID)
)^
-- end CAKES_REFRIGERATOR
