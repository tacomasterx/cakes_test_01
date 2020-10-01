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
);