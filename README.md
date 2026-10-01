# GetWorker

Mock справочник сотрудников компании.

Веб-приложение использует OpenLDAP для авторизации пользователей, получения контактных данных сотрудников и поиска по справочнику.

## Запуск проекта

### Требования:

- git
- docker
- docker compose

### Быстрый запуск:

1.

   ```bash
   git clone https://github.com/satiristica/getworker.git
   ```

2.

   ```bash
   cd getworker
   ```

3.

   ```bash
   docker compose up --build
   ```

При первом запуске docker автоматически загрузит необходимые образы и соберёт backend

Страница авторизации:

```text
http://localhost:8080/pages/login/index.html
```

для проверки тестового пользователя:

```text
login: john.smith
password: password
```

остановить запущенные контейнеры:

```bash
docker compose down
```


## Архитектура 

Представляет из себя монолитное Spring Boot приложение с функциональными модулями. Backend и фронтенд собираются в одно приложение и запускаются вместе. OpenLDAP запускается в отдельном докер-контейнере. 

### Основные компоненты: 
- auth: принимает данные авторизации и проверяет пользователя через LDAP.
- directory: предоставляет список сотрудников и обрабатывает поиск 
- ldap: создает LDAP соединения, делает авторизацию и поиск сотрудников
- static/pages: страницы авторизации и справочника 
- docker: описание ЛДАП и тестовые данные 

### Workflow авторизации: 
```text
Login Page
    -> AuthController
    -> AuthService
    -> LdapAuthService
    -> LdapConnection
    -> OpenLDAP
```

### Workflow получения сотрудников: 
```text 
Directory
    -> DirectoryController
    -> DirectoryService
    -> LdapDirectoryService
    -> LdapConnection 
    -> OpenLDAP
``` 

Springboot возвращает data в json, после JS создает строки таблицы на странице

Подробное описание архитектуры и модулей в docs/README.md
