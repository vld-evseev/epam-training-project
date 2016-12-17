INSERT INTO lawAndSocialDb.user (id, username, firstName, lastName, email, birthdate, passwordHash)
  SELECT nextval('lawAndSocialDb.user_seq'), 'User1', 'fn1', 'ln1', 's@g.er', '01.12.1958', 'hash1'
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'User2', 'fn2', 'ln2', 's2@g.er', '04.02.1994', 'hash2'
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'User3', 'fn3', 'ln3', 's3@g.er', '12.06.1981', 'hash3'
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'testUser', 'name1', 'surname2', 'test@mail.com', '05.07.1990',
              '$s0$e0801$5R872TeqCy+HLGtzOolpeA==$L4NTgEEb5Eq0KWeVUaAQyj7co4nivM39ybkS3oWl0hY=';
