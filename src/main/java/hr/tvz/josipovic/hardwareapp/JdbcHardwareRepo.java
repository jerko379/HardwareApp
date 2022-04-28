package hr.tvz.josipovic.hardwareapp;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcHardwareRepo implements HardwareRepository {


    private static final String  SELECT_ALL = "select  id, code, name, tip , price, quantity from hardware";



    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;


    public JdbcHardwareRepo(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");}



    @Override
    public List<Hardware> findAll() {

        return jdbc.query(SELECT_ALL,this::mapRowtoHardware);
    }


    @Override
    public Optional<Hardware> findByCode(String  code) {
        try{
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " where code = ?", this::mapRowtoHardware , code));
        } catch (EmptyResultDataAccessException e){
            return Optional.empty();
        }
    }


    @Override
    public Optional<Hardware> insert(Hardware hardware) {
        try {
            hardware.setId(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException e){
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) {
        jdbc.update("DELETE FROM hardware WHERE code = ?", code);
    }


    private Hardware mapRowtoHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getString("code"),
                Type.valueOf(rs.getString("tip")),
                rs.getInt("quantity")
        );
    }

    private Integer saveHardwareDetails(Hardware hardware) {
        Map<String, Object> values = new HashMap<>();
        values.put("name", hardware.getName());
        values.put("price", hardware.getPrice());
        values.put("code", hardware.getCode());
        values.put("tip", hardware.getType().toString());
        values.put("quantity", hardware.getQuantity());
        return inserter.executeAndReturnKey(values).intValue();
    }

}