# Industrial programming
## Java semester project

Необходимо реализовать консольное приложение, которое:
1)  Читает данные из входного файла;
2)  Обрабатывает полученную информацию;
3)  Записывает данные в выходной файл;
 
Входной и выходной файл могут быть следующих форматов: plain text, xml, json. Так же входные и выходные файлы могут быть архивированы и зашифрованы, разными engines (только архивирован, только зашифрован, сперва архивирован, а потом зашифрован и наоборот).
 
«Тип» входного и выходного файла задаются параметрами консоли.
Приложение реализовать двумя способами: без использования Design Patterns и c использованием Design Patterns (Decorator и Builder … можно оформить Builder в виде Singleton-а), сравнить реализации.
 
Обработка информации на первом этапе: найти все арифметические операции во входном файле и заменить на результаты в выходном файле.
Реализовать фильтрацию двумя способами без использования регулярных выражений и с использованием регулярных выражений (а так же третьим :) найти библиотеку, которая все делает за вас, парсинг и калькуляцию, такие есть и не одна). Провести сравнительный анализ 2-х вариантов реализации.

Следующий этап: добавить UI based реализацию на любой графической java библиотеке. А так же реализовать логику как web service.

Готовая реализация приложения на Java-fx включена в этот проект в ветке master.

Вёрстка веб-сервиса на java с помошью spring-boot в ветке web-service. (в будующем планирую полностью доделать web реализацию)
