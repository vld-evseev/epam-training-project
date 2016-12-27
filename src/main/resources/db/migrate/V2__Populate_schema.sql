INSERT INTO lawAndSocialDb.user (id, username, firstName, lastName, patronymic, gender, birthdate, passwordHash)
  SELECT nextval('lawAndSocialDb.user_seq'), 'JohnDoug123', 'John', 'Doug', 'Albert', 'MALE', '08.02.1989',
    '$s0$e0801$c+pEdHwfgqpU+bfRvfoi2g==$5gMZSzI2Dx2PEjA1dYc3SNQ6e1HdBoHD4HA0NsU9VSI='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'testUser', 'name1', 'surname2', NULL, NULL, '05.07.1990',
              '$s0$e0801$6zvGkIzar10PijTyyrqQyg==$gizYA/v7z48i5V9D7njSluNTdJP/vsp3pUDav7F/TzU='
  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'anotherUser', 'Another', 'User', NULL, 'FEMALE', '06.12.2016',
              '$s0$e0801$5R872TeqCy+HLGtzOolpeA==$L4NTgEEb5Eq0KWeVUaAQyj7co4nivM39ybkS3oWl0hY=';

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

