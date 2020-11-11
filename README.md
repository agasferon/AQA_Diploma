### Дипломный проект профессии «Тестировщик ПО»

#### Описание приложения
Приложение представляет из себя веб-сервис.

![main page](./documentation/main_page.png)

Приложение предлагает купить тур по определённой цене с помощью двух способов:
- Обычная оплата по дебетовой карте
- Уникальная технология: выдача кредита по данным банковской карты

Приложение должно в собственной СУБД сохранять информацию о том, каким способом был совершён платёж и успешно ли он был совершён (при этом данные карт сохранять не допускается).

#### Шаги для запуска приложения
1. Открыть проект
1. Выполнить в терминале команду ``Docker-compose up``
1. В отдельном окне терминала выполнить команду ``java -jar ./artifacts/app-shop.jar``
1. Для запуска тестов выполнить команду ``gradlew clean test allureReport``
1. Для генерации отчета о тестировании выполнить команду ``gradlew allureReport``  
Отчет будет находиться в директории: ``./build/reports/allure-report/index.html``

Для тестирования приложения с СУБД PostgreSQL необходимо в файле application.properties в строке ``spring.datasource.url=`` заменить ``jdbc:mysql://localhost:3306/app`` на ``jdbc:postgresql://localhost:5432/app``


#### Документация к проекту
1. [План автоматизации](./documentation/Plan.md)
1. [Отчет по тестированию](./documentation/Report.md)
1. [Отчет по автоматизации](./documentation/Summary.md)

[![Build status](https://ci.appveyor.com/api/projects/status/baio8em638weqs6a/branch/master?svg=true)](https://ci.appveyor.com/project/agasferon/aqa-diploma/branch/master)
[![Build Status](https://travis-ci.org/agasferon/AQA_Diploma.svg?branch=master)](https://travis-ci.org/agasferon/AQA_Diploma)
![Java CI with Gradle](https://github.com/agasferon/AQA_Diploma/workflows/Java%20CI%20with%20Gradle/badge.svg)
