# SmartInvent

Web-сервис для автоматизированной работы логистической системы.

## Гайд по запуску через Docker

1) Клонируйте к себе проект <br>
2) В файле `docker-compose.yml` измените следующие поля конфигурации для базы данных на свои:
```yaml
environment:
      - MYSQL_DATABASE=
      - MYSQL_PASSWORD=
      - MYSQL_ROOT_PASSWORD=
```
3) Через консоль по пути склонированного проекта выполните команду для сборки Maven:
```sh
  mvn clean install
```
4) Выполните команду для сборки Docker:
```sh
  docker compose build
```
5) Выполните команду для запуска Docker:
```sh
  docker-compose up
```

## Гайд по запуску без Docker

1) Клонируйте к себе проект <br>
2) Откройте проект в IDE <br>
3) В файле `application.yml` измените следующие поля конфигурации для базы данных на свои:
```yaml
spring:
  datasource:
    url: 
    username: 
    password: 
```
4) Запустите приложение через класс `WarehouseAppApplication`.
