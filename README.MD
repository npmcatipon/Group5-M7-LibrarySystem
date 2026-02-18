# Group 5 Library System (Module 6)  
## How to create database, table and insert dummy data.
```sql
psql.exe -U postgres -f create-database.sql
psql.exe -U postgres -d group5 -f create-table.sql
psql.exe -U postgres -d group5 -f insert-data.sql
```  
The command above will create the following tables.
- users
- book
- loan

Things to do:
### 1. Migrate to Maven Project
- [x] import hibernate-core and postgresql dependencies in pom.xml
- [x] configure persistence.xml
- [x] configure jdbc connection
### 2. Create Entities using JPA annotations
- [x] put all entities in a single package
- [x] create entity class for Book, Loan and User
### 3. Practice Repository Pattern
- [x] create repository pacakge and put all Repository classes there
- [x] create repository interface
- [x] create repository implementation class per entity class
### 4. Retain all functionalities from Module 5 Project
- [ ] display all books, display available books, display all borrowed books, borrow book, return book, add book, remove book, update book
- [x] try to put these functionalities in a service classes (BookService)
- [x] put service class in service package
