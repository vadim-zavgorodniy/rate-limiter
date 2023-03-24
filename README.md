# Rate-limiter

## Описание
Сервис предназначен для использования в качестве ограничителя количества 
запросов в секунду к ресурсам.
Все данные содержатся в памяти, синхронизация между экземплярами не поддерживается.
Т.о. корректно работать может только в одном экземпляре.

Проверка превышения лимита доступна по URL: `rate-limit/{resource-name}`

В случае если лимит обращений к ресурсу не превышен - ответом будет HTTP код 200.
Если лимит превышен, то ответом будет код 429 (too many requests).

## Конфигурация
В файле конфигурации можно задать имя ресурса и количество разрешенных запросов к
нему в единицу времени. Допускается указать секунды, минуты и часы.
```yaml
rate-limiter:
  limits:
    test1:
      count: 10
      unit: SECONDS
    test2:
      count: 100
      unit: MINUTES
    test3:
      count: 1000
      unit: HOURS
```

## Сборка
Проект использует java-17 и spring-boot.

Собирается командой
```shell
mvn clean package
```
