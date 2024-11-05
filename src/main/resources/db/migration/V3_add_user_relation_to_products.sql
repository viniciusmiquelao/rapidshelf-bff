ALTER TABLE products
ADD COLUMN user_id TEXT NOT NULL;

ALTER TABLE products
ADD CONSTRAINT fk_products_user
FOREIGN KEY (user_id) REFERENCES users(id);