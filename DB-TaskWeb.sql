CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username TEXT NOT NULL,
    pass TEXT NOT NULL,
    phone BIGINT,
    age BIGINT,
    gender TEXT NOT NULL,
    create_date DATE,
    update_date DATE
);

CREATE TABLE tasks (
    id BIGSERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    state TEXT NOT NULL,
    create_date DATE,
    update_date DATE,
    user_id BIGINT NOT NULL
);