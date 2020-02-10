cat docker-entrypoint-initdb.d/001_create_database.sql | mysql -u root -p
cat docker-entrypoint-initdb.d/002_create_tables.sql | mysql -u root -p
