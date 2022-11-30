## Условие задачи: 

Необходимо реализовать консольное CRUD приложение, которое взаимодействует с БД и позволяет выполнять все CRUD операции над сущностями:

Writer (id, firstName, lastName, List<Post> posts)
Post (id, content, created, updated, List<Label> labels)
Label (id, name)
PostStatus (enum ACTIVE, UNDER_REVIEW, DELETED)

Требования:
Придерживаться шаблона MVC (пакеты model, repository, service, controller, view)
Для миграции БД использовать https://www.liquibase.org/
Сервисный слой приложения должен быть покрыт юнит тестами (junit + mockito).

Слои:
model - POJO клаcсы
repository - классы, реализующие доступ к текстовым файлам
controller - обработка запросов от пользователя
view - все данные, необходимые для работы с консолью
Например: Writer, WriterRepository, WriterController, WriterView и т.д.

Для репозиторного слоя желательно использовать базовый интерфейс:
interface GenericRepository<T,ID>
interface WriterRepository extends GenericRepository<Writer, Long>
class JdbcWriterRepositoryImpl implements WriterRepository

Для импорта библиотек использовать Maven.
Технологии: Java, MySQL, JDBC, Maven, Liquibase, JUnit, Mockito.
Результатом выполнения проекта должен быть отдельный репозиторий на github, с описанием задачи, проекта и инструкцией по локальному запуску проекта.

==================================================================

## ИНСТРУКЦИЯ ПО ЗАПУСКУ ПРИЛОЖЕНИЯ:

1. Предварительно убедитесь, что на вашем компьютере установлены JVM и JRE.
2. Скачать программу CRUDAPP.jar по ссылке https://github.com/IreneYanushkevich/CRUD_2.0/blob/master/CRUD_2.0.jar                                                                                                                                                                
3. Вызвать командную строку сочетанием клавиш WIN+R.
4. Прописать в командной строке: cd пробел и путь к папке со скачанной программой + Enter (пример: C:\Users\Irene>cd C:\Users\Irene\IdeaProjects\CRUD_2.0\out\artifacts\CRUD_2.0_jar)
5. Запустить программу, прописав в командной строке: java -jar CRUD_2.0.jar
