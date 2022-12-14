# CRUD приложение для работы с данными книг и читателей
CRUD app. Tech stack: java spring, thymeleaf, HTML, CSS, postgres, JDBC Template

## Web-страницы:

### Список людей:
<img src="https://github.com/ShulV/CRUD-app-1/blob/main/doc/doc_images/people.png" alt="index.html" width="350">

### Более подробно об одном человеке:
<div>
<img src="https://github.com/ShulV/CRUD-app-1/blob/main/doc/doc_images/people__id.png" alt="person.html - 1" width="350">
<img src="https://github.com/ShulV/CRUD-app-1/blob/main/doc/doc_images/people__id-without-book.png" alt="person.html - 2" width="350">
</div>

### Редактирование человека:
<img src="https://github.com/ShulV/CRUD-app-1/blob/main/doc/doc_images/people__id-edit.png" alt="edit-person.html" width="350">

### Список книг:
<img src="https://github.com/ShulV/CRUD-app-1/blob/main/doc/doc_images/books.png" alt="books.html" width="350">

### Более подробно об одной книге:
<div>
<img src="https://github.com/ShulV/CRUD-app-1/blob/main/doc/doc_images/books__id.png" alt="book.html - 1" width="350">
<img src="https://github.com/ShulV/CRUD-app-1/blob/main/doc/doc_images/books__id-without-person.png" alt="book.html - 2" width="350">
</div>

### Редактирование книги:
<img src="https://github.com/ShulV/CRUD-app-1/blob/main/doc/doc_images/books__id-edit.png" alt="book-edit.html - 2" width="350">

## Реляционная модель БД:
![rel model](https://github.com/ShulV/CRUD-app-1/blob/main/doc/doc_images/db_structure.png)

## Скрипты работы с базой данных (PostgreSQL):

### Код генерации БД (DB generation SQL script):

```
CREATE TABLE people(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "name" varchar(50) NOT NULL,
    patronymic varchar(50) NOT NULL,
    surname varchar(50) NOT NULL,
    birthday date NOT NULL,
    email varchar(50) NOT NULL UNIQUE,
);

CREATE TABLE books(
    id integer NOT NULL GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    "name" varchar(100) NOT NULL,
    author varchar(100) NOT NULL,
    date date NOT NULL,
    person_id int DEFAULT NULL REFERENCES people(id) ON DELETE Set null ON UPDATE Cascade
);
```

### Пример добавления пользователей:

```
INSERT INTO public.people(
	name, patronymic, surname, birthday)
	VALUES ('Виктор', 'Максимович', 'Шульпов', '14.07.2001'),
	('Илья', 'Игоревич', 'Шинтяпин', '15.05.2001'),
	('Максим', 'Владимирович', 'Иванов', '16.10.2000');
```
	
### Пример добавления книг:

```
INSERT INTO public.books(
	name, author, date)
	VALUES ('Война и мир', 'Лев Толстой', '01.01.1862'),
	('Отцы и дети', 'Иван Тургенев', '01.01.1868'),
	('Старик и море', 'Эрнест Хемингуэй', '01.01.1952');
```

### Выборка всех людей:

```
SELECT * FROM People;
```

### Выборка всех книг:

```
SELECT * FROM Books;
```

### Выборка человека по id:

```
SELECT * FROM People WHERE id=?;
```

### Выборка книги по id:

```
SELECT * FROM Books WHERE id=?;
```

### Пример выборки человека, который взял книгу:

```
select p.id, p.name, p.patronymic, p.surname, p.birthday from people as p
    join books as b
    on p.id = b.person_id
    where b.id = 4;
```

### Пример редактирования данных человека:

```
update people set name = ?, patronymic = ?, surname = ?, birthday = ?, email = ? where id = ?;
```

### Пример редактирования данных книги:

```
update books set name = ?, author = ?, date = ? where id = ?;
```

### Пример удаления человека по id:

```
DELETE FROM People WHERE id = ?;
```

### Пример освобождения книги:

```
UPDATE Books SET person_id = NULL WHERE id = ?;
```

### Пример присваивания книги:

```
UPDATE Books SET person_id = ? WHERE id = ?;
```
