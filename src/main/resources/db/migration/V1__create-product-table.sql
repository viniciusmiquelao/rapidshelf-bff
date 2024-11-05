CREATE TABLE products (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    name TEXT NOT NULL,
    status TEXT NOT NULL,
    description TEXT NOT NULL,
    price INTEGER NOT NULL
);