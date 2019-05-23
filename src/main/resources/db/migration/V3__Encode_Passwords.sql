create extension if not exists pgcrypto;

update USERS set USER_PASSWORD = crypt(USER_PASSWORD, gen_salt('bf', 8));