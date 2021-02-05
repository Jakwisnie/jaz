
CREATE TABLE userRoles(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    user_role VARCHAR NOT NULL,
    user_id integer
);