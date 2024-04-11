package com.example.superoheltespringboot.Repository;

import com.example.superoheltespringboot.Model.MarvelCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MarvelCharacterRepository
{
    private final JdbcTemplate jdbcTemplate;
    private String sql;

    @Autowired
    public MarvelCharacterRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<MarvelCharacter> findAll()
    {
        sql = "select * from superhelte";
        return jdbcTemplate.query(sql,MarvelCharacterRowMapper());
    }

    public MarvelCharacter save (MarvelCharacter marvelCharacter)
    {
        if (marvelCharacter.getId()==null)
        {
            sql = "insert into superhelte (names,alias,powers,alignment) values(?,?,?,?)";
            jdbcTemplate.update(sql, marvelCharacter.getName(),marvelCharacter.getAlias(),marvelCharacter.getPowers(),marvelCharacter.getAlignment());
        }else
        {
            sql = "update superhelte set names=?,alias=?,powers=?,alignment=? where id=?";
            jdbcTemplate.update(sql, marvelCharacter.getName(), marvelCharacter.getAlias(), marvelCharacter.getPowers(), marvelCharacter.getAlignment());
        }
        return marvelCharacter;
    }

    public void delete (String alias)
    {
        sql = "delete from marvel_character where alias=?";
        jdbcTemplate.update(sql,alias);
    }

    public Optional findByAlias (String alias)
    {
        sql = "select * from marvel_character where Alias=?";
        MarvelCharacter marvelCharacter = jdbcTemplate.queryForObject(sql, new Object[]{alias},MarvelCharacterRowMapper());
        return Optional.ofNullable(marvelCharacter);
    }

    private RowMapper<MarvelCharacter> MarvelCharacterRowMapper() {
        return (rs, rowNum) -> {
            MarvelCharacter MarvelCharacter = new MarvelCharacter();
            MarvelCharacter.setId(rs.getLong("id"));
            MarvelCharacter.setName(rs.getString("name"));
            MarvelCharacter.setAlias(rs.getString("alias"));
            MarvelCharacter.setPowers(rs.getString("powers"));
            MarvelCharacter.setAlignment(rs.getString("alignment"));
            return MarvelCharacter;
        };
    }
}
