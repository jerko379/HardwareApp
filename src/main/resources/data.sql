insert into hardware (code, name, tip, price, quantity)
values ('NRTX3080', 'Nvidia RTX 3080 ', 'MBO', 2000, 6);

insert into hardware (code, name, tip, price, quantity)
values ('NRTX2070', 'Nvidia RTX 2070 ', 'GPU', 3000, 11);

insert into hardware (code, name, tip, price, quantity)
values ('I57300HQ', 'Intel i5 7300HQ ', 'CPU', 1500, 3);



insert into review (title, content, rating, hardware_Id)
values ('10/10 would recommend', 'buy this very good gpu', 5, 1);

insert into review (title, content, rating, hardware_Id)
values ('2/10 would not recommend', 'dont buy this bad gpu', 2, 2);

insert into review (title, content, rating, hardware_Id)
values ('0/10 awful cpu', 'cpu for microwave', 1, 3);

insert into review (title, content, rating, hardware_Id)
values ('DONT BUY', 'cpu for toaster', 2, 3);


insert into usr(id, username, pass)
values (1, 'usr', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
       (2, 'admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'); -- password = admin
insert into authority (id, authority_name)
values (1, 'ROLE_ADMIN'),
       (2, 'ROLE_USER');
insert into user_authority (user_id, authority_id)
values (1, 2),
       (2, 1);