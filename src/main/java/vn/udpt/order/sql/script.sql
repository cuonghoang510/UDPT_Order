CREATE TABLE orders (
                        id VARCHAR(255) PRIMARY KEY,
                        user_id VARCHAR(255),
                        merchant_id VARCHAR(255),
                        event_id VARCHAR(255),
                        full_name VARCHAR(255),
                        amount BIGINT,
                        status VARCHAR(255),
                        email VARCHAR(255),
                        phone_number VARCHAR(255),
                        payment_method VARCHAR(255),
                        created_date TIMESTAMP,
                        expired_date TIMESTAMP,
                        start_event_date TIMESTAMP,
                        promotion_id VARCHAR(255),
                        quantity INT,
                        price BIGINT,
                        callback_url VARCHAR(255),
                        description TEXT
);

CREATE TABLE ticket (
                        id VARCHAR(255) PRIMARY KEY,
                        merchant_id VARCHAR(255),
                        title VARCHAR(255),
                        description TEXT,
                        price BIGINT,
                        stock INT,
                        min_quantity INT,
                        max_quantity INT,
                        location VARCHAR(255),
                        start_date TIMESTAMP,
                        end_date TIMESTAMP,
                        status VARCHAR(255),
                        type VARCHAR(255)
);
