# Group 5 Library System (Module 7)  
## How to create database, table and insert dummy data.
```sql
psql.exe -U postgres -f create-database.sql
psql.exe -U postgres -d group5 -f create-table.sql
psql.exe -U postgres -d group5 -f insert-data.sql
```  

---  
- [x] 1. Convert your M6 project into a **RESTful** API using *Spark Java*  
- [x] 2. Create a BookController class.  
- [x] 3. Create a route that utilizes/represents all the M6 functionalities:
   - [x] a. Display All Books  
   - [x] b. Display Available Books  
   - [x] c. Display All Borrowed Books  
   - [x] d. Borrow Book  
   - [x] e. Return Book  
   - [x] f. Add Book  
   - [x] g. Remove Book  
   - [x] h. Update Book  
- [x] 4. All interactions (input and output) must be made through the API.  
- [ ] 5. Create an API specification for your API. It must at least indicate the HTTP Method, Resource Path, Query Parameters, URI Parameters, Request Body, and the Response Body.
- [ ] 6. Bonus Points:  
   - [ ] a. +1 for Clever Design  
   - [ ] b. +1 for Validation  
   - [ ] c. +1 for Proper Use of HTTP Status  
