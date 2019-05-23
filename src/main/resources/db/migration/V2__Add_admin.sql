insert into USERS (USER_ID, USER_ISACTIVE, USER_EMAIL, USER_LOGIN, USER_NAME, USER_PIBY, USER_PID,
USER_PN, USER_PS, USER_PASSWORD, USER_PATRONYMIC, USER_SURNAME)
    values (1, true, '', 'admin', 'admin', '', '', '', '', 'a', '', '');

insert into user_role (user_id, roles)
    values ((1, 'USER'), (1, 'ADMIN'));