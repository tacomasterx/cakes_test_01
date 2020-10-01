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
);