CREATE TABLE section
(
    id   BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR

);

CREATE TABLE category
(
    id         BIGSERIAL NOT NULL PRIMARY KEY,
    section_id BIGINT,
    name       VARCHAR,
    CONSTRAINT section_id FOREIGN KEY (section_id) REFERENCES Section (id)
);

CREATE TABLE auction
(
    id          BIGSERIAL NOT NULL PRIMARY KEY,
    title       VARCHAR   NOT NULL,
    description VARCHAR   NOT NULL,
    owner_id    BIGINT,
    category_id BIGINT,
    price       INTEGER,
    CONSTRAINT category_id FOREIGN KEY (category_id) REFERENCES category (id),
    CONSTRAINT owner_id FOREIGN KEY (owner_id) REFERENCES users (id)
);


CREATE TABLE parameter
(
    id   BIGSERIAL NOT NULL PRIMARY KEY,
    name VARCHAR   NOT NULL
);
CREATE TABLE auction_parameter
(
    auction_id   BIGINT,
    parameter_id BIGINT,
    value        VARCHAR,
    FOREIGN KEY (auction_id) REFERENCES auction (id),
    FOREIGN KEY (parameter_id) REFERENCES parameter (id),
    CONSTRAINT auction_parameter_id PRIMARY KEY (auction_id, parameter_id)
);


CREATE TABLE photo
(
    id         BIGSERIAL NOT NULL PRIMARY KEY,
    auction_id BIGINT,
    link       VARCHAR,
    position   BIGINT,
    FOREIGN KEY (auction_id) REFERENCES auction (id)
);





