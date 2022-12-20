#
# Copyright (c) 2022. Created by Kristina Barbina.
#

echo "=== Creating the 'dvdrental' database ==="
psql -U postgres -c 'CREATE DATABASE dvdrental'

echo "=== Restore 'dvdrental' database ==="
pg_restore -U postgres -d dvdrental /tmp/dvdrental.tar