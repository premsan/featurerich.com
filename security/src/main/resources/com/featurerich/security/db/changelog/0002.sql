CREATE TABLE authorities (
	id CHAR(36) NOT NULL,
    version INT NOT NULL,
	name VARCHAR(256) NOT NULL,
	updated_at BIGINT NOT NULL,
    updated_by CHAR(36) NOT NULL,
	CONSTRAINT authorities_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX authorities_ix1 ON authorities (name);

CREATE TABLE roles (
	id CHAR(36) NOT NULL,
    version INT NOT NULL,
	name VARCHAR(256) NOT NULL,
	updated_at BIGINT NOT NULL,
    updated_by CHAR(36) NOT NULL,
	CONSTRAINT roles_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX roles_ix1 ON roles (name);

CREATE TABLE role_authorities (
	id CHAR(36) NOT NULL,
    version INT NOT NULL,
	role_id CHAR(36) NOT NULL,
	authority_id CHAR(36) NOT NULL,
	updated_at BIGINT NOT NULL,
    updated_by CHAR(36) NOT NULL,
	CONSTRAINT role_authorities_pk PRIMARY KEY (id),
    CONSTRAINT role_authorities_fk1 FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    CONSTRAINT role_authorities_fk2 FOREIGN KEY (authority_id) REFERENCES authorities (id) ON DELETE CASCADE
);

CREATE INDEX role_authorities_ix1 ON role_authorities (role_id);
CREATE INDEX role_authorities_ix2 ON role_authorities (authority_id);
CREATE UNIQUE INDEX role_authorities_ix3 ON role_authorities (role_id, authority_id);

CREATE TABLE users (
	id CHAR(36) NOT NULL,
    version INT NOT NULL,
	email VARCHAR(256) NOT NULL,
	disabled BOOLEAN DEFAULT FALSE NOT NULL,
	updated_at BIGINT NOT NULL,
    updated_by CHAR(36) NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE UNIQUE INDEX users_idx1 ON users (email);

CREATE TABLE user_roles (
	id CHAR(36) NOT NULL,
	version INT NOT NULL,
	user_id CHAR(36) NOT NULL,
	role_id CHAR(36) NOT NULL,
	updated_at BIGINT NOT NULL,
    updated_by CHAR(36) NOT NULL,
	CONSTRAINT user_roles_pk PRIMARY KEY (id),
    CONSTRAINT user_roles_fk1 FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT user_roles_fk2 FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE
);

CREATE INDEX user_roles_idx1 ON user_roles (role_id);
CREATE UNIQUE INDEX user_roles_idx2 ON user_roles (user_id, role_id);