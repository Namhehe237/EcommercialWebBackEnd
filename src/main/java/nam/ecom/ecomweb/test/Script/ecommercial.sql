DROP DATABASE Ecommercial;

CREATE DATABASE Ecommercial;

USE Ecommercial;

CREATE TABLE products (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    category VARCHAR(100),
    image VARCHAR(255),
    price DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


-- Add indexes for better performance
CREATE INDEX idx_category ON products(category);
CREATE INDEX idx_name ON products(name);

INSERT INTO products (name, quantity, category, image, price) VALUES
-- Electronics
('MacBook Pro M2', 30, 'Electronics', 'https://images.unsplash.com/photo-1517336714731-489689fd1ca8', 2499.99),
('Wireless Earbuds', 150, 'Electronics', 'https://images.unsplash.com/photo-1590658268037-6bf12165a8df', 199.99),
('Smart Watch Series 8', 45, 'Electronics', 'https://images.unsplash.com/photo-1579586337278-3befd40fd17a', 399.99),
('4K Monitor', 25, 'Electronics', 'https://images.unsplash.com/photo-1527443224154-c4a3942d3acf', 299.99),
('Gaming Keyboard', 60, 'Electronics', 'https://images.unsplash.com/photo-1587829741301-dc798b83add3', 129.99),
('Wireless Mouse', 100, 'Electronics', 'https://images.unsplash.com/photo-1615663245857-ac93bb7c39e7', 49.99),
('Bluetooth Speaker', 80, 'Electronics', 'https://images.unsplash.com/photo-1608043152269-423dbba4e7e1', 79.99),
('Tablet Pro', 35, 'Electronics', 'https://images.unsplash.com/photo-1561154464-82e9adf32764', 599.99),
('Gaming Console', 40, 'Electronics', 'https://images.unsplash.com/photo-1486401899868-0e435ed85128', 499.99),
('Webcam HD', 70, 'Electronics', 'https://images.unsplash.com/photo-1587826080692-f439cd0b70da', 89.99),

-- Clothing
('Running Shoes', 120, 'Clothing', 'https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a', 79.99),
('Denim Jacket', 45, 'Clothing', 'https://images.unsplash.com/photo-1576995853123-5a10305d93c0', 49.99),
('Winter Coat', 30, 'Clothing', 'https://images.unsplash.com/photo-1539533113208-f6df8cc8b543', 89.99),
('Yoga Pants', 90, 'Clothing', 'https://images.unsplash.com/photo-1548663512-4a8a539d7f12', 39.99),
('Cotton T-Shirt', 200, 'Clothing', 'https://images.unsplash.com/photo-1521572163474-6864f9cf17ab', 15.99),
('Sneakers', 85, 'Clothing', 'https://images.unsplash.com/photo-1549298916-b41d501d3772', 69.99),
('Hooded Sweatshirt', 75, 'Clothing', 'https://images.unsplash.com/photo-1556821840-3a63f95609a7', 49.99),
('Athletic Shorts', 110, 'Clothing', 'https://images.unsplash.com/photo-1591195853828-11db59a44f6b', 24.99),
('Wool Sweater', 40, 'Clothing', 'https://images.unsplash.com/photo-1586337126523-52894e41337c', 59.99),
('Rain Jacket', 55, 'Clothing', 'https://images.unsplash.com/photo-1545594861-3bef43ff2fc8', 69.99),

-- Home & Living
('Coffee Maker', 40, 'Home', 'https://images.unsplash.com/photo-1517668808822-9ebb02f2a0e6', 99.99),
('Desk Lamp', 65, 'Home', 'https://images.unsplash.com/photo-1534277662486-69c369ff4b3e', 29.99),
('Throw Pillow', 150, 'Home', 'https://images.unsplash.com/photo-1584100936595-c0654b55a2e6', 19.99),
('Wall Clock', 85, 'Home', 'https://images.unsplash.com/photo-1563861826100-9cb868fdbe1c', 34.99),
('Bedding Set', 45, 'Home', 'https://images.unsplash.com/photo-1522771739844-6a9f6d5f14af', 79.99),
('Air Purifier', 30, 'Home', 'https://images.unsplash.com/photo-1626436629565-5671eb1677b5', 129.99),
('Kitchen Blender', 55, 'Home', 'https://images.unsplash.com/photo-1619067321826-4e97f5ed9200', 89.99),
('Plant Pot', 200, 'Home', 'https://images.unsplash.com/photo-1485955900006-10f4d324d411', 15.99),
('Storage Basket', 120, 'Home', 'https://images.unsplash.com/photo-1519710164239-da123dc03ef4', 12.99),
('Floor Rug', 40, 'Home', 'https://images.unsplash.com/photo-1576075796033-848c2a5f3696', 149.99),

-- Accessories
('Leather Wallet', 100, 'Accessories', 'https://images.unsplash.com/photo-1627123424574-724758594e93', 29.99),
('Sunglasses', 80, 'Accessories', 'https://images.unsplash.com/photo-1511499767150-a48a237f0083', 19.99),
('Watch Classic', 35, 'Accessories', 'https://images.unsplash.com/photo-1524592094714-0f0654e20314', 99.99),
('Backpack', 70, 'Accessories', 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62', 59.99),
('Leather Belt', 90, 'Accessories', 'https://images.unsplash.com/photo-1624668433951-350a8692e0b3', 39.99),
('Scarf', 120, 'Accessories', 'https://images.unsplash.com/photo-1601925260368-ae2f83cf8b7f', 24.99),
('Phone Case', 200, 'Accessories', 'https://images.unsplash.com/photo-1541877944-ac82a091518a', 12.99),
('Travel Bag', 45, 'Accessories', 'https://images.unsplash.com/photo-1553062407-98eeb64c6a62', 79.99),
('Umbrella', 150, 'Accessories', 'https://images.unsplash.com/photo-1558181050-78c519357f37', 15.99),
('Hat', 95, 'Accessories', 'https://images.unsplash.com/photo-1534215754734-18e55d13e346', 25.99),

-- Sports & Outdoors
('Yoga Mat', 80, 'Sports', 'https://images.unsplash.com/photo-1544367567-0f2fcb009e0b', 39.99),
('Tennis Racket', 40, 'Sports', 'https://images.unsplash.com/photo-1622279457989-5c4d90f8f407', 59.99),
('Basketball', 60, 'Sports', 'https://images.unsplash.com/photo-1546519638-68e109498ffc', 29.99),
('Camping Tent', 25, 'Sports', 'https://images.unsplash.com/photo-1536425463272-227d2feac46f', 199.99),
('Fitness Tracker', 100, 'Sports', 'https://images.unsplash.com/photo-1557166983-5453526355ad', 99.99),
('Water Bottle', 250, 'Sports', 'https://images.unsplash.com/photo-1602143407151-7111542de6e8', 14.99),
('Bike Helmet', 70, 'Sports', 'https://images.unsplash.com/photo-1557803175-06c9e37f0549', 49.99),
('Jump Rope', 150, 'Sports', 'https://images.unsplash.com/photo-1601422407692-ec4eeec1d9b3', 9.99),
('Dumbbell Set', 40, 'Sports', 'https://images.unsplash.com/photo-1638536532686-d610adbe8db5', 149.99),
('Hiking Backpack', 35, 'Sports', 'https://images.unsplash.com/photo-1601662528567-526cd06f6582', 89.99);

DROP TABLE users;

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL ,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_id INT NOT NULL,
    rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);

INSERT INTO reviews (product_id, rating, comment) VALUES
-- Reviews for Electronics
(1, 5, 'The MacBook Pro M2 is super fast and lightweight. Highly recommend!'),
(1, 4, 'Great laptop, but a bit expensive for the features.'),
(2, 4, 'Wireless earbuds have great sound, but the fit could be better.'),
(3, 5, 'Smart Watch Series 8 is a game changer for fitness tracking.'),
(4, 3, 'The 4K Monitor has stunning visuals, but the build quality feels cheap.'),
(5, 5, 'The gaming keyboard is excellent, very responsive keys!'),
(6, 4, 'Good wireless mouse, but battery life could be improved.'),
(7, 5, 'Bluetooth speaker is loud and clear, perfect for parties.'),
(8, 4, 'The Tablet Pro is a great device for work and entertainment.'),
(9, 5, 'Best gaming console I have ever owned!'),

-- Reviews for Clothing
(11, 5, 'These running shoes are super comfortable and durable.'),
(12, 4, 'Denim jacket fits well, but color fades after washing.'),
(13, 3, 'The winter coat is warm but not water-resistant.'),
(14, 5, 'Yoga pants are stretchy and comfortable for workouts.'),
(15, 4, 'Cotton T-shirt is soft and breathable, great for daily wear.'),
(16, 5, 'The sneakers are stylish and offer good support.'),
(17, 4, 'The hoodie is cozy, but the material attracts lint easily.'),
(18, 5, 'Athletic shorts are lightweight and perfect for running.'),
(19, 5, 'The wool sweater is soft and warm, perfect for winter.'),
(20, 4, 'Rain jacket works well, but feels a bit stiff.'),

-- Reviews for Home & Living
(21, 5, 'The coffee maker brews perfect coffee every morning.'),
(22, 4, 'Desk lamp is bright and adjustable, but cord is short.'),
(23, 5, 'Throw pillow is soft and adds a nice touch to the sofa.'),
(24, 4, 'Wall clock is stylish but a bit loud.'),
(25, 3, 'The bedding set is comfy but wrinkles easily.'),
(26, 5, 'Air purifier is efficient and quiet, great for allergies.'),
(27, 4, 'The kitchen blender works well, but cleaning is tricky.'),
(28, 5, 'Plant pot is simple yet elegant, perfect for indoor decor.'),
(29, 4, 'The storage basket is durable and holds a lot.'),
(30, 5, 'Floor rug is soft and looks beautiful in the living room.'),

-- Reviews for Accessories
(31, 5, 'Leather wallet is slim and functional, great quality.'),
(32, 4, 'Sunglasses are stylish, but the frame feels fragile.'),
(33, 5, 'Classic watch is elegant and works perfectly.'),
(34, 4, 'Backpack is spacious and comfortable for daily use.'),
(35, 3, 'Leather belt is good, but buckle feels cheap.'),
(36, 5, 'Scarf is soft and keeps you warm, highly recommend.'),
(37, 4, 'Phone case protects well but adds bulk.'),
(38, 5, 'Travel bag is sturdy and fits all essentials.'),
(39, 4, 'Umbrella is compact and works well in light rain.'),
(40, 5, 'Hat is comfortable and blocks the sun effectively.'),

-- Reviews for Sports & Outdoors
(41, 5, 'Yoga mat has great grip and cushioning, perfect for poses.'),
(42, 4, 'Tennis racket is lightweight and well-balanced.'),
(43, 5, 'Basketball is durable and has a nice bounce.'),
(44, 3, 'Camping tent is spacious but difficult to set up.'),
(45, 5, 'Fitness tracker is accurate and easy to use.'),
(46, 4, 'Water bottle is sturdy but slightly heavy.'),
(47, 5, 'Bike helmet is comfortable and provides excellent safety.'),
(48, 4, 'Jump rope is good, but handles are a bit slippery.'),
(49, 5, 'Dumbbell set is durable and perfect for home workouts.'),
(50, 4, 'Hiking backpack is comfortable and has plenty of storage.');




	