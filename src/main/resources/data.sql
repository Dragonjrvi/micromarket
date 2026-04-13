CREATE DATABASE micromarket;
USE micromarket;

-- =========================
-- MODULO I: CATEGORIAS
-- =========================
CREATE TABLE category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE
);

-- =========================
-- MODULO I: PRODUCTOS
-- =========================
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    barcode VARCHAR(100) NOT NULL UNIQUE,
    price DOUBLE NOT NULL,
    stock INT NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    category_id BIGINT,

    CONSTRAINT fk_product_category
    FOREIGN KEY (category_id)
    REFERENCES category(id)
);

-- =========================
-- MODULO II: PROVEEDORES
-- =========================
CREATE TABLE supplier (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    nit VARCHAR(50) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address VARCHAR(100)
);

-- =========================
-- RELACION MANY TO MANY
-- =========================
CREATE TABLE product_supplier (
    product_id BIGINT,
    supplier_id BIGINT,

    PRIMARY KEY (product_id, supplier_id),

    CONSTRAINT fk_ps_product
    FOREIGN KEY (product_id) REFERENCES product(id),

    CONSTRAINT fk_ps_supplier
    FOREIGN KEY (supplier_id) REFERENCES supplier(id)
);

-- =========================
-- MODULO III: EMPLEADOS
-- =========================
CREATE TABLE employee (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    document VARCHAR(20) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    position ENUM('ADMINISTRADOR', 'CAJERO', 'AUXILIAR') NOT NULL,
    hire_date DATE NOT NULL,
    salary DOUBLE NOT NULL
);

-- =========================
-- MODULO IV: VENTAS
-- =========================
CREATE TABLE sale (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    employee_id BIGINT NOT NULL,
    subtotal DOUBLE NOT NULL,
    iva DOUBLE NOT NULL,
    total DOUBLE NOT NULL,

    CONSTRAINT fk_sale_employee
    FOREIGN KEY (employee_id)
    REFERENCES employee(id)
);

-- =========================
-- DETALLE DE VENTA
-- =========================
CREATE TABLE sale_detail (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    sale_id BIGINT NOT NULL,
    product_id BIGINT NOT NULL,
    quantity INT NOT NULL,
    price DOUBLE NOT NULL,
    total DOUBLE NOT NULL,

    CONSTRAINT fk_detail_sale
    FOREIGN KEY (sale_id)
    REFERENCES sale(id),

    CONSTRAINT fk_detail_product
    FOREIGN KEY (product_id)
    REFERENCES product(id)
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- =========================
-- DATOS DE PRUEBA
-- =========================

-- Categorías
INSERT INTO category (name) VALUES ('Bebidas');
INSERT INTO category (name) VALUES ('Snacks');

-- Productos
INSERT INTO product (name, barcode, price, stock, active, category_id)
VALUES ('Coca Cola', '123456', 3000, 50, TRUE, 1);

INSERT INTO product (name, barcode, price, stock, active, category_id)
VALUES ('Papas Margarita', '789456', 2000, 30, TRUE, 2);

-- Proveedores
INSERT INTO supplier (name, nit, phone, address)
VALUES ('Distribuidora Nacional', '900123456', '3001234567', 'Bogotá');

INSERT INTO supplier (name, nit, phone, address)
VALUES ('Snacks Corp', '900654321', '3017654321', 'Medellín');


-- Empleados
INSERT INTO employee (document, name, position, hire_date, salary)
VALUES ('123456789', 'Juan Pérez', 'CAJERO', '2023-01-10', 1500000);

INSERT INTO employee (document, name, position, hire_date, salary)
VALUES ('987654321', 'Ana Gómez', 'ADMINISTRADOR', '2022-05-20', 2500000);