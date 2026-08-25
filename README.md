# GetWorker

Внутренний справочник сотрудников компании Silkway Transit.

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
