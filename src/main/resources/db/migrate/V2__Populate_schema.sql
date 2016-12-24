INSERT INTO lawAndSocialDb.user (id, username, firstName, lastName, birthdate, passwordHash)
  SELECT nextval('lawAndSocialDb.user_seq'), 'JohnDoug123', 'John', 'Doug', '08.02.1989',
    '$s0$e0801$c+pEdHwfgqpU+bfRvfoi2g==$5gMZSzI2Dx2PEjA1dYc3SNQ6e1HdBoHD4HA0NsU9VSI=)'
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'testUser', 'name1', 'surname2', '05.07.1990',
              '$s0$e0801$5R872TeqCy+HLGtzOolpeA==$L4NTgEEb5Eq0KWeVUaAQyj7co4nivM39ybkS3oWl0hY='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'anotherUser', 'Another', 'User', '06.12.2016',
              '$s0$e0801$CplBQViuloA3913AMLI3zA==$sRct7mJY0lEPNRBTGCiaNygB1HaLU6xjFFYXXnVjttY=)';

INSERT INTO lawAndSocialDb.school (id, user_id, name, country, city, yearsFrom, yearsTo)
  SELECT nextval('lawAndSocialDb.school_seq'), 2, 'School1', 'Russia', 'Moscow', 1985, 1990
  UNION ALL SELECT nextval('lawAndSocialDb.school_seq'), 2, 'School2', 'Ukraine', 'Kiev', 1995, 2000
  UNION ALL SELECT nextval('lawAndSocialDb.school_seq'), 1, 'School3', 'Russia', 'S.Petersburg', 1990, 2000;

INSERT INTO lawAndSocialDb.contacts (id, user_id, email, phone, website)
  SELECT nextval('lawAndSocialDb.contacts_seq'), 1, 'testMail@gmail.com', '+7999111222', 'johndoug.com.ua'
  UNION ALL SELECT nextval('lawAndSocialDb.contacts_seq'), 2, 'test@mail.com', '+7226663331', 'tesuserwebsite.com';
/*
INSERT INTO lawAndSocialDb.user_school (user_id, school_id, yearsFrom, yearsTo)
  SELECT '4', '1', '1994', '2000'
  UNION ALL SELECT '4', '3', '1955', '1960'
  UNION ALL SELECT '1', '2', '1984', '1940';
*/

