CREATE TABLE "user_group"
(
    id         BIGSERIAL PRIMARY KEY,
    group_name VARCHAR(128) NOT NULL,
    is_active  BOOLEAN      NOT NULL
);

INSERT INTO "user_group" (group_name, is_active) VALUES ('Admin', true);
INSERT INTO "user_group" (group_name, is_active) VALUES ('Supervisor', true);
INSERT INTO "user_group" (group_name, is_active) VALUES ('Operator', true);
INSERT INTO "user_group" (group_name, is_active) VALUES ('Test Group', false);
