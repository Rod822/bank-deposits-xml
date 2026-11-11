# Банківські вклади: XML

Лабораторна робота 2 з ООП, варіант 16 «Банківські вклади».

## Що є в проєкті

- `deposits.xml`: 16 вкладів, кореневий елемент `bank`;
- `deposits.xsd`: схема з переліком типів вкладу, межами для суми,
  відсотка і строку та атрибутом `account-id` типу ID;
- `XmlValidator`: перевірка XML за схемою;
- парсери SAX, DOM і StAX. Вклад збирається в одному спільному класі
  `DepositBuilder`, а парсери тільки читають файл;
- `ProfitabilityComparator`: сортування за річним відсотком;
- `group-by-type.xsl`: розбиває вклади на підсписки за типом вкладу;
- повідомлення програми виводяться через log4j2.

## Запуск

Клас `ua.knu.bank.BankApplication`. Аргумент `sax`, `dom` або `stax`
вибирає парсер, без аргументу використовується SAX. Результат XSL
записується у файл `deposits-by-type.xml`.

Тести: `mvn test`.

## Патерни

- Template Method: `DepositParser` (спільний `parse`, у кожного парсера
  свій `readFile`);
- Factory Method: `ParserFactory`;
- Builder: `DepositBuilder` збирає вклад по частинах.
