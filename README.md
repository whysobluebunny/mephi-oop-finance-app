# Личный финансовый менеджер

Проект представляет собой backend-приложение для управления личными финансами. Пользователи могут добавлять доходы и расходы, устанавливать бюджеты на категории и получать статистику по своим финансам. Приложение работает в консольном режиме и сохраняет данные между сессиями.

---

## Содержание
1. [Технологии](#технологии)
2. [Сборка и запуск](#сборка-и-запуск)
3. [Запуск тестов](#запуск-тестов)
4. [Инструкция для пользователя](#инструкция-для-пользователя)
    - [Регистрация и авторизация](#регистрация-и-авторизация)
    - [Добавление доходов и расходов](#добавление-доходов-и-расходов)
    - [Установка бюджета](#установка-бюджета)
    - [Перевод средств между пользователями](#перевод-средств-между-пользователями)
    - [Просмотр статистики](#просмотр-статистики)
5. [Пример работы](#пример-работы)

---

## Технологии

- **Язык программирования**: Java
- **Сборка**: Maven
- **Библиотеки**:
    - Lombok (для сокращения boilerplate-кода)
    - Jackson (для сериализации и десериализации данных)
    - JUnit 5 (для unit-тестов)

---

## Сборка и запуск

1. Убедитесь, что у вас установлены:
    - Java JDK 11 или выше.
    - Apache Maven.

2. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/whysobluebunny/mephi-oop-finance-app.git
   cd mephi-oop-finance-app
   ```

3. Соберите проект с помощью Maven:
   ```bash
   mvn clean install
   ```

4. Запустите приложение:
   ```bash
   java -jar target/finance-manager-1.0-SNAPSHOT.jar
   ```

---

## Запуск тестов

Для запуска unit-тестов выполните команду:
```bash
mvn clean test
```

---

## Инструкция для пользователя

### Регистрация и авторизация

1. **Регистрация**:
    - Введите команду `register` и следуйте инструкциям:
      ```bash
      Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): register
      Enter username: testUser
      Enter password: password123
      ```
    - После успешной регистрации вы увидите сообщение: `User registered successfully.`

2. **Авторизация**:
    - Введите команду `login` и следуйте инструкциям:
      ```bash
      Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): login
      Enter username: testUser
      Enter password: password123
      ```
    - После успешной авторизации вы увидите сообщение: `Login successful.`

---

### Добавление доходов и расходов

1. **Добавление дохода**:
    - Введите команду `addIncome` и следуйте инструкциям:
      ```bash
      Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): addIncome
      Enter category: Salary
      Enter amount: 20000
      ```
    - После успешного добавления вы увидите сообщение: `Income added successfully.`

2. **Добавление расхода**:
    - Введите команду `addExpense` и следуйте инструкциям:
      ```bash
      Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): addExpense
      Enter category: Food
      Enter amount: 300
      ```
    - После успешного добавления вы увидите сообщение: `Expense added successfully.`

---

### Установка бюджета

1. Введите команду `setBudget` и следуйте инструкциям:
   ```bash
   Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): setBudget
   Enter category: Food
   Enter limit: 4000
   ```
2. После успешной установки бюджета вы увидите сообщение: `Budget set successfully.`

---

### Перевод средств между пользователями

1. Введите команду `transfer` и следуйте инструкциям:
   ```bash
   Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): transfer
   Enter recipient username: anotherUser
   Enter amount: 1000
   ```
2. После успешного перевода вы увидите сообщение: `Transfer successful.`

---

### Просмотр статистики

1. Введите команду `showStatistics`:
   ```bash
   Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): showStatistics
   ```
2. Вы увидите отчет в формате:
   ```
   Total income: 63000.0
   Income by category:
   Salary: 60000.0
   Bonus: 3000.0
   Total expenses: 8300.0
   Budget by category:
   Utilities: 2500.0, Remaining budget: -500.0
   Food: 4000.0, Remaining budget: 3200.0
   Entertainment: 3000.0, Remaining budget: 0.0
   ```

---

### Сохранение данных

Приложение автоматически сохраняет данные пользователей и их финансовые операции в файл `finance_data.json` при завершении работы. При следующем запуске данные загружаются из этого файла, что позволяет продолжить работу с того места, где вы остановились.

#### Как это работает:
1. **Сохранение данных**:
   - При выходе из приложения (команда `exit`) все данные сохраняются в файл `finance_data.json`.
   - Файл создается в той же директории, где находится приложение.

2. **Загрузка данных**:
   - При запуске приложение проверяет наличие файла `finance_data.json`.
   - Если файл существует, данные загружаются, и вы можете продолжить работу.

#### Пример:
- После добавления доходов, расходов и установки бюджетов выполните команду `exit`:
  ```bash
  Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): exit
  ```
- При следующем запуске приложения все ваши данные будут восстановлены.

---

## Пример работы

1. Зарегистрируйте пользователя:
   ```bash
   Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): register
   Enter username: testUser
   Enter password: password123
   ```

2. Авторизуйтесь:
   ```bash
   Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): login
   Enter username: testUser
   Enter password: password123
   ```

3. Добавьте доходы и расходы:
   ```bash
   Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): addIncome
   Enter category: Salary
   Enter amount: 20000

   Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): addExpense
   Enter category: Food
   Enter amount: 300
   ```

4. Установите бюджет:
   ```bash
   Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): setBudget
   Enter category: Food
   Enter limit: 4000
   ```

5. Просмотрите статистику:
   ```bash
   Enter command (register, login, addIncome, addExpense, setBudget, showStatistics, transfer, exit): showStatistics
   ```
