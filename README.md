Visitors Management System for managing visitors visiting a society (or institution). It allows visitors to check-in digitally to eliminate the tedious registeration and paperwork. Additionally, it also keeps a track of every visitor and their timings. Generally we have guards who enter details in some notebook ti maintain a log. It is really unpleasent for the visitors to stand at the gate and give details about the visit. To ease the process of registration, In-time, Out-time, time tracking and logging the history, this VMS can be of great use.

There are are mainly three role and for each role we designed the API's :
  1. Admin Panel's API : Responsible for CRUD User (In society/Institution), CRUD Gatekeeper and Daily Visit Report generation
  2. Gatekeeper API : Add new Visitor, Update visitor (if already exists), Add visit details, Update entry and exit.
  3. User Panel API : Approve visit/reject, getAll pending visits (if many comes to visit make waiting).

Tech Stack:
  1. Java
  2. Spring and Spring Boot
  3. REST 
  4. Bootify
  5. MySql
  6. JDBC
  7. Postman & Swagger
  8. Lombok
