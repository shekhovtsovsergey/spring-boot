CREATE TABLE PRODUCT
(
    ID                 BIGSERIAL        NOT NULL PRIMARY KEY,
    TITLE              VARCHAR(255)     NOT NULL,
    COST               DECIMAL(10, 2)   NOT NULL
);


CREATE TABLE USERS
(
    ID                 BIGSERIAL        NOT NULL PRIMARY KEY,
    USERNAME           VARCHAR(255)     NOT NULL
);



CREATE TABLE ORDERS
(
    ID                 BIGSERIAL        NOT NULL PRIMARY KEY,

    product_id         BIGSERIAL        REFERENCES PRODUCT (id),
    user_id            BIGSERIAL        REFERENCES USERS (id)
);