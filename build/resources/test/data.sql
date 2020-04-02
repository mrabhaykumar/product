Drop table if exists price;
DROP TABLE IF EXISTS product;


CREATE TABLE product (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  product_id VARCHAR(10) NOT NULL,
  seller_id VARCHAR(50) NOT NULL,
  title VARCHAR(50) NOT NULL,
  manufacturer VARCHAR(250) DEFAULT NULL,
  is_low_quantity BOOLEAN DEFAULT FALSE,
  is_sold_out BOOLEAN DEFAULT FALSE,
  is_back_order BOOLEAN DEFAULT FALSE,
  metafields CLOB DEFAULT NULL,
  requires_shipping BOOLEAN DEFAULT FALSE,
  is_visible BOOLEAN DEFAULT FALSE,
  published_at TIMESTAMP DEFAULT NULL,
  created_at TIMESTAMP DEFAULT NULL,
  updated_at TIMESTAMP NOT NULL,
  workflow_status VARCHAR(50) NOT NULL
);
CREATE TABLE price(
    id INT AUTO_INCREMENT  PRIMARY KEY,
    product_id VARCHAR(10) NOT NULL,
    range varchar(50),
    min NUMERIC(8,2),
    max NUMERIC(8,2),
    foreign key (product_id) references product(product_id)
);

INSERT INTO
  product(
    product_id,
    seller_id,
    title,
    manufacturer,
    is_low_quantity,
    is_sold_out,
    is_back_order,
    metafields,
    requires_shipping,
    is_visible,
    published_at,
    created_at,
    updated_at,
    workflow_status
  )
VALUES(
    '123',
    'GAG',
    ' AGA ',
    ' GAG ',
    FALSE,
    FALSE,
    FALSE,
    '[    {      "key": "Capacity",      "value": "full"    },    {      "key": "cat",      "value": "dog"    }  ]',
    FALSE,
    FALSE,
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    CURRENT_TIMESTAMP(),
    ' GAGAG '
  );
INSERT INTO price (
product_id,
range,
min,
max
)values (
123,
'4.5-5.6',
4.5,
5.6
);
