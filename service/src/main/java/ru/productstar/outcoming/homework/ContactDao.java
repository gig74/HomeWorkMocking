package ru.productstar.outcoming.homework;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.Collections;
import java.util.List;

public class ContactDao {

    private static final String GET_ALL_ACCOUNTS_SQL = "" +
            "SELECT" +
            "    ID, " +
            "    NAME, " +
            "    SURNAME, " +
            "    PHONE_NUMBER, " +
            "    EMAIL " +
            "FROM CONTACT ";

    private static final String GET_CONTACT_SQL = "" +
            "SELECT" +
            "    ID, " +
            "    NAME, " +
            "    SURNAME, " +
            "    PHONE_NUMBER, " +
            "    EMAIL " +
            "FROM CONTACT " +
            "WHERE ID = :id";

    private static final String CREATE_CONTACT_SQL = "" +
            "INSERT INTO CONTACT(NAME, SURNAME, PHONE_NUMBER, EMAIL)" +
            "VALUES(:name, :surname, :phoneNumber, :email)";

    private static final String UPDATE_CONTACT_PHONENUMBER_SQL = "" +
            "UPDATE CONTACT SET PHONE_NUMBER = :phoneNumber";

    private static final String UPDATE_CONTACT_EMAIL_SQL = "" +
            "UPDATE CONTACT SET EMAIL = :email";

    private static final String DELETE_CONTACT_SQL = "" +
            "DELETE FROM CONTACT " +
            "WHERE ID = :id";

    private static final RowMapper<Contact> CONTACT_ROW_MAPPER =
            (rs, i) -> new Contact(rs.getLong("ID"), rs.getString("NAME"),
                    rs.getString("SURNAME"), rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER"));

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public ContactDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    public List<Contact> getAllContacts() {
        return namedJdbcTemplate.query(
                GET_ALL_ACCOUNTS_SQL,
                CONTACT_ROW_MAPPER
        );
    }

    public Contact getContact(long contactId) {
        return namedJdbcTemplate.queryForObject(
                GET_CONTACT_SQL,
                new MapSqlParameterSource()
                        .addValue("id", contactId),
                CONTACT_ROW_MAPPER
        );
    }

    public long addContact(Contact contact) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(
                CREATE_CONTACT_SQL,
                new MapSqlParameterSource()
                        .addValue("name", contact.getName())
                        .addValue("surname", contact.getSurname())
                        .addValue("phoneNumber", contact.getPhone())
                        .addValue("email", contact.getEmail()),
                keyHolder,
                new String[]{"id"}
        );

        return keyHolder.getKey().longValue();
    }

    public void updatePhoneNumber(long contactId, String phoneNumber) {

        namedJdbcTemplate.update(
                UPDATE_CONTACT_PHONENUMBER_SQL,
                new MapSqlParameterSource()
                        .addValue("id", contactId)
                        .addValue("phoneNumber", phoneNumber)
        );
    }

    public void updateEmail(long contactId, String email) {
        namedJdbcTemplate.update(
                UPDATE_CONTACT_EMAIL_SQL,
                new MapSqlParameterSource()
                        .addValue("id", contactId)
                        .addValue("email", email)
        );
    }

    public void deleteContact(long contactId) {
        namedJdbcTemplate.update(
                DELETE_CONTACT_SQL,
                new MapSqlParameterSource()
                        .addValue("id", contactId)
        );
    }
}
