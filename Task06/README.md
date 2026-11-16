# Task 6 Campus Event Registration

Requires Java 17, Maven, and MySQL. Run `schema.sql` in MySQL, then set `DB_URL`, `DB_USER`, and `DB_PASSWORD` as environment variables. From this folder, run `mvn package` and `mvn exec:java`. The database enforces uniqueness on `(student_id, event_name)`; the UI validates fields and uses prepared statements. Database requests run in `SwingWorker`, and the table loads 50 rows per page. Export writes the currently displayed page to CSV.

For reliable paging, ensure `event_name,id` is indexed if the dataset grows. A filter-specific SQL query is parameterized. In a production application, distinguish duplicate-key SQLState/vendor codes rather than treating every integrity violation as a duplicate, and query database uniqueness errors by SQLState. Fill actual connection settings only in environment variables; never commit credentials.
