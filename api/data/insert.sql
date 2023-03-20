insert into account (email, account_name, account_password, active) values ('admin@gmail.com.br', 'admin', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);
insert into account (email, account_name, account_password, active) values ('user@gmail.com.br', 'user', '$2a$10$VrIbJURwINOR5HOrWFFTNOwSILsioRJSuOGAg8Luvr9qZDSOl5JXG', true);

insert into permission (function, account_id) values ('ADMIN', 1);
insert into permission (function, account_id) values ('USER', 1);
insert into permission (function, account_id) values ('USER', 2);