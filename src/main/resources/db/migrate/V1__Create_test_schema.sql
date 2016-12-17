CREATE SCHEMA lawAndSocialDb;

CREATE SEQUENCE lawAndSocialDb.user_seq;
/*DO
$$
BEGIN
  CREATE SEQUENCE unit10db.user_seq;
  EXCEPTION WHEN duplicate_table THEN
  -- do nothing, it's already there
END
$$ LANGUAGE plpgsql;*/

CREATE TABLE lawAndSocialDb.user (
  id           INTEGER PRIMARY KEY,
  username     VARCHAR(255) NOT NULL,
  firstName    VARCHAR(255) NOT NULL,
  lastName     VARCHAR(255) NOT NULL,
  email        VARCHAR(255) NOT NULL,
  birthdate    VARCHAR(255) NOT NULL,
  passwordHash VARCHAR(255) NOT NULL
);


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
