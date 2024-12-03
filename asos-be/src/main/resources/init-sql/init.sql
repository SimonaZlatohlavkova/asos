DROP TABLE IF EXISTS "order_products";
DROP TABLE IF EXISTS "orders";
DROP TABLE IF EXISTS "addresses";
DROP TABLE IF EXISTS "sales";
DROP TABLE IF EXISTS "deliveries";
DROP TABLE IF EXISTS "products";
DROP TABLE IF EXISTS "users";

CREATE TABLE "users" (
  "id" integer PRIMARY KEY,
  "name" varchar(50),
  "surname" varchar(100),
  "email" varchar(100) UNIQUE,
  "password" varchar(100),
  "created_at" timestamp
);

CREATE TABLE "products" (
  "id" integer PRIMARY KEY,
  "name" varchar(100) NOT NULL,
  "url" varchar(524) NOT NULL,
  "description" varchar(1024),
  "original_price" numeric(10,2) NOT NULL,
  "created_at" timestamp,
  "stock" integer
);

CREATE TABLE "sales" (
  "id" integer PRIMARY KEY,
  "product_id" integer,
  "sale_price" numeric(10,2) NOT NULL,
  "date_from" timestamp,
  "date_to" timestamp,
  "created_at" timestamp
);

CREATE TABLE "deliveries" (
  "id" integer PRIMARY KEY,
  "name" varchar(255) NOT NULL,
  "price" numeric(10,2) NOT NULL,
  "created_at" timestamp
);

CREATE TABLE "orders" (
  "id" integer PRIMARY KEY,
  "user_id" integer,
  "address_id" integer,
  "delivery_id" integer,
  "order_date" timestamp NOT NULL,
  "total_price" numeric(10,2) NOT NULL,
  "status" varchar(50),
  "created_at" timestamp
);

CREATE TABLE "order_products" (
  "order_id" integer,
  "product_id" integer,
  "quantity" integer NOT NULL DEFAULT 1,
  "price" numeric(10,2) NOT NULL
);

CREATE TABLE "addresses" (
  "id" integer PRIMARY KEY,
  "street" varchar(255) NOT NULL,
  "city" varchar(100) NOT NULL,
  "state" varchar(100) NOT NULL,
  "postal_code" varchar(20) NOT NULL,
  "country" varchar(100) NOT NULL,
  "house_number" varchar(100) NOT NULL,
  "created_at" timestamp
);

ALTER TABLE "sales" ADD FOREIGN KEY ("product_id") REFERENCES "products" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("address_id") REFERENCES "addresses" ("id");

ALTER TABLE "orders" ADD FOREIGN KEY ("delivery_id") REFERENCES "deliveries" ("id");

ALTER TABLE "order_products" ADD FOREIGN KEY ("order_id") REFERENCES "orders" ("id");

ALTER TABLE "order_products" ADD FOREIGN KEY ("product_id") REFERENCES "products" ("id");
