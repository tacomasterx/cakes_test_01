alter table CAKES_CAKE_INVENTORY add constraint FK_CAKES_CAKE_INVENTORY_ON_STORE foreign key (STORE_ID) references CAKES_STORE(ID);
create index IDX_CAKES_CAKE_INVENTORY_ON_STORE on CAKES_CAKE_INVENTORY (STORE_ID);
