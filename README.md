# crtr-test-task
Стэк: Java 8, Spring Boot 2.3.3 + thymeleaf + lombok, БД postgresql 11.9
Скрипты для базы:
1) Создать пользователя
CREATE USER test WITH PASSWORD 'test';
2) Создать бд, с которой будем работать
CREATE DATABASE test;
3) Дать права на бд пользователю
GRANT ALL PRIVILEGES ON DATABASE test to test;

Запустить приложение.
Таблицы сгенерятся автоматически, после надо будет создать процедуру в бд.
Создать процедуру для получения статуса (категории) по ИИН:
create or replace procedure GET_USER_CATEGORY_BY_IIN(in iin_in BIGINT, inout category_out varchar(50))
    language plpgsql
as $$
begin
    select c.name into category_out FROM t_user u JOIN t_category c on u.category_id = c.id WHERE u.iin = iin_in;
end;
$$;
