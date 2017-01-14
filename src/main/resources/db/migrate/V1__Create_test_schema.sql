CREATE SCHEMA lawAndSocialDb;

CREATE SEQUENCE lawAndSocialDb.user_seq;

CREATE TABLE lawAndSocialDb.user (
  id           INTEGER PRIMARY KEY,
  uuid         VARCHAR(255) NOT NULL,
  username     VARCHAR(255) NOT NULL,
  firstName    VARCHAR(255) NOT NULL,
  lastName     VARCHAR(255) NOT NULL,
  patronymic   VARCHAR(255),
  gender       VARCHAR(255) DEFAULT 'UNKNOWN'
    CHECK (gender IN ('MALE', 'FEMALE', 'UNKNOWN')),
  birthdate    VARCHAR(255) NOT NULL,
  avatar       TEXT,
  passwordHash VARCHAR(255) NOT NULL
);


CREATE SEQUENCE lawAndSocialDb.contacts_seq;

CREATE TABLE lawAndSocialDb.contacts (
  id      INTEGER PRIMARY KEY,
  user_id INTEGER      NOT NULL,
  email   VARCHAR(255) NOT NULL,
  phone   VARCHAR(255),
  website VARCHAR(255),
  FOREIGN KEY (user_id) REFERENCES lawAndSocialDb.user (id)
);

CREATE SEQUENCE lawAndSocialDb.school_seq;

CREATE TABLE lawAndSocialDb.school (
  id        INTEGER PRIMARY KEY,
  user_id   INTEGER NOT NULL,
  name      VARCHAR(255),
  country   VARCHAR(255),
  city      VARCHAR(255),
  yearsFrom INTEGER,
  yearsTo   INTEGER,
  FOREIGN KEY (user_id) REFERENCES lawAndSocialDb.user (id)
);

CREATE SEQUENCE lawAndSocialDb.university_seq;

CREATE TABLE lawAndSocialDb.university (
  id        INTEGER PRIMARY KEY,
  user_id   INTEGER NOT NULL,
  name      VARCHAR(255),
  country   VARCHAR(255),
  city      VARCHAR(255),
  yearsFrom INTEGER,
  yearsTo   INTEGER,
  FOREIGN KEY (user_id) REFERENCES lawAndSocialDb.user (id)
);

CREATE TABLE lawAndSocialDb.follow (
  user_id          INTEGER NOT NULL REFERENCES lawAndSocialDb.user (id),
  followed_user_id INTEGER NOT NULL REFERENCES lawAndSocialDb.user (id),
  CONSTRAINT followed_user_key PRIMARY KEY (user_id, followed_user_id)
);

CREATE TABLE lawAndSocialDb.message_history (
  from_user_id INTEGER NOT NULL,
  to_user_id   INTEGER NOT NULL,
  date_value   BIGINT  NOT NULL,
  text_value   TEXT    NOT NULL,
  FOREIGN KEY (from_user_id) REFERENCES lawAndSocialDb.user (id),
  FOREIGN KEY (to_user_id) REFERENCES lawAndSocialDb.user (id)
);

/*CREATE TABLE lawAndSocialDb.user_school (
  user_id   INTEGER REFERENCES lawAndSocialDb.user (id) ON UPDATE CASCADE,
  school_id INTEGER REFERENCES lawAndSocialDb.school (id) ON UPDATE CASCADE ON DELETE CASCADE,
  yearsFrom INTEGER,
  yearsTo   INTEGER,
  CONSTRAINT user_school_key PRIMARY KEY (user_id, school_id)
);*/


/*DO
$$
BEGIN
  CREATE SEQUENCE unit10db.role_seq;
  EXCEPTION WHEN duplicate_table THEN
  -- do nothing, it's already there
END
$$ LANGUAGE plpgsql;*/
/*
CREATE SEQUENCE lawAndSocialDb.role_seq;

CREATE TABLE unit10dbtest.role (
  id      INTEGER PRIMARY KEY,
  name    VARCHAR(255) NOT NULL,
  user_id INTEGER      NOT NULL,
  FOREIGN KEY (user_id) REFERENCES unit10dbtest.user (id)
);*/
