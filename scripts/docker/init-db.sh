#!/bin/bash
set -e

# Verifica se o banco de dados já existe
if psql -U "$POSTGRES_USER" -lqt | cut -d \| -f 1 | grep -qw "$POSTGRES_DB"; then
    echo "Database $POSTGRES_DB already exists"
else
    echo "Creating database $POSTGRES_DB"
    createdb -U "$POSTGRES_USER" "$POSTGRES_DB"
fi
