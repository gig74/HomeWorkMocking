package ru.productstar.outcoming.homework;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;
@Service
@Profile("testBasic")
public class ContactDaoImpl implements ContactDao {
    private static final String GET_CONTACT_SQL = "" +
            "SELECT" +
            "    ID, " +
            "    NAME, " +
            "    SURNAME, " +
            "    PHONE_NUMBER, " +
            "    EMAIL " +
            "FROM CONTACT " +
            "WHERE ID = :id";
    private static final RowMapper<Contact> CONTACT_ROW_MAPPER =
            (rs, i) -> new Contact(rs.getLong("ID"), rs.getString("NAME"),
                    rs.getString("SURNAME"), rs.getString("EMAIL"),
                    rs.getString("PHONE_NUMBER"));
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    public ContactDaoImpl(@Autowired NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }
    public Contact getContact(long contactId) {
        return namedJdbcTemplate.queryForObject(
                GET_CONTACT_SQL,
                new MapSqlParameterSource()
                        .addValue("id", contactId),
                CONTACT_ROW_MAPPER
        );
    }

}
