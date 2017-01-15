package com.epam.training.lawAndSocial;

import com.epam.training.lawAndSocial.dao.UserDao;
import com.epam.training.lawAndSocial.dao.pg.PgUserDao;
import com.epam.training.lawAndSocial.db.H2DataSourceTest;
import com.epam.training.lawAndSocial.model.Gender;
import com.epam.training.lawAndSocial.model.User;
import com.epam.training.lawAndSocial.service.SecurityService;
import com.epam.training.lawAndSocial.service.impl.SecurityServiceImpl;
import com.epam.training.lawAndSocial.service.model.UserService;
import com.epam.training.lawAndSocial.service.model.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.UUID;

public class SqlGenerator extends H2DataSourceTest {

    private DataSource dataSource;

    @Before
    public void initDataSource() {
        dataSource = getDataSource();
    }

    private final static long beginTime = Timestamp.valueOf("1940-01-01 00:00:00").getTime();
    private final static long endTime = Timestamp.valueOf("1990-12-31 00:00:00").getTime();

    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");

    private static String[] Beginning = {"Kr", "Ca", "Ra", "Mrok", "Cru",
            "Ray", "Bre", "Zed", "Drak", "Mor", "Jag", "Mer", "Jar", "Mjol",
            "Zork", "Mad", "Cry", "Zur", "Creo", "Azak", "Azur", "Rei", "Cro",
            "Mar", "Luk"};
    private static String[] Middle = {"air", "ir", "mi", "sor", "mee", "clo",
            "red", "cra", "ark", "arc", "miri", "lori", "cres", "mur", "zer",
            "marac", "zoir", "slamar", "salmar", "urak"};
    private static String[] End = {"d", "ed", "ark", "arc", "es", "er", "der",
            "tron", "med", "ure", "zur", "cred", "mur"};

    private static Random rand = new Random();

    public static String generateName() {
        return Beginning[rand.nextInt(Beginning.length)] +
                Middle[rand.nextInt(Middle.length)] +
                End[rand.nextInt(End.length)];
    }

    private long generateBirthDate(long beginTime, long endTime) {
        long diff = endTime - beginTime + 1;
        return beginTime + (long) (Math.random() * diff);
    }


    @Test
    public void generateUsers() {
        final SecurityService securityService = new SecurityServiceImpl();
        final UserDao userDao = new PgUserDao(dataSource);
        final UserService userService = new UserServiceImpl(securityService, userDao);

        System.out.println(generateBirthDate(beginTime, endTime));

        final String pattern = "INSERT INTO lawAndSocialDb.user (id, uuid, username, firstName, lastName, patronymic, gender, birthdate, avatar, passwordHash)\n" +
                "  SELECT nextval('lawAndSocialDb.user_seq'), '14675655-63bb-4dad-99c2-005768902ade', 'JohnDoug123', 'John', 'Doug', 'Albert', 'MALE', '08.02.1989', NULL,\n" +
                "    '$s0$e0801$c+pEdHwfgqpU+bfRvfoi2g==$5gMZSzI2Dx2PEjA1dYc3SNQ6e1HdBoHD4HA0NsU9VSI='\n" +
                "  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '337daaed-b5c7-4fb8-bb49-b96816a89699', 'testUser', 'name1', 'surname2', NULL, NULL, '05.07.1990', NULL,\n" +
                "              '$s0$e0801$6zvGkIzar10PijTyyrqQyg==$gizYA/v7z48i5V9D7njSluNTdJP/vsp3pUDav7F/TzU='\n" +
                "  UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), 'b2ddc07f-8e5c-40b5-ac06-dc124cab7cc6', 'anotherUser', 'Another', 'User', NULL, 'FEMALE', '06.12.2016', NULL,\n" +
                "              '$s0$e0801$5R872TeqCy+HLGtzOolpeA==$L4NTgEEb5Eq0KWeVUaAQyj7co4nivM39ybkS3oWl0hY='";

        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pattern);

        for (int i = 0; i < 100; i++) {
            final long date = generateBirthDate(beginTime, endTime);
            Gender randomGender = Gender.values()[(int) (Math.random() * Gender.values().length)];
            final User user = User.builder()
                    .uuid(UUID.randomUUID().toString())
                    .userName(generateName())
                    .firstName(generateName())
                    .lastName(generateName())
                    .date(simpleDateFormat.format(date))
                    .gender(randomGender)
                    .passwordHash(securityService.encrypt("password" + i))
                    .build();

            stringBuilder.append(" UNION ALL SELECT nextval('lawAndSocialDb.user_seq'), '")
                    .append(user.getUuid()).append("','")
                    .append(user.getUserName()).append("','");
            stringBuilder.append(user.getFirstName()).append("','");
            stringBuilder.append(user.getLastName()).append("',");
            stringBuilder.append("NULL").append(",");
            stringBuilder.append("NULL").append(",'");
            stringBuilder.append(user.getDate()).append("',");
            stringBuilder.append("NULL").append(",'");
            stringBuilder.append(user.getPasswordHash()).append("'\n");

            /*userService.add(user);*/
        }

        stringBuilder.append(";");

        System.out.println(stringBuilder.toString());

        /*final int offset = 20;
        for (int i = 0; i < 100; i += offset) {
            final List<User> userList = userService.getUsers(i);
            for (User user : userList) {
                System.out.println(user.toString());
            }
            System.out.println("--------------------------");
        }*/


    }

}
