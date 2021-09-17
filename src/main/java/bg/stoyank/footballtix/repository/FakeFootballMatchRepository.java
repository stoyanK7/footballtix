package bg.stoyank.footballtix.repository;

import bg.stoyank.footballtix.model.FootballMatch;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("FakeFootballMatchRepository")
public class FakeFootballMatchRepository  implements FootballMatchRepository {

    private final List<FootballMatch> db;

    public FakeFootballMatchRepository() {
        this.db = new ArrayList<FootballMatch>();
    }

    @Override
    public List<FootballMatch> findAll() {
        return db;
    }

    @Override
    public List<FootballMatch> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<FootballMatch> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<FootballMatch> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(FootballMatch entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends FootballMatch> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends FootballMatch> S save(S entity) {
        System.out.println(db);
        db.add(entity);
        return null;
    }

    @Override
    public <S extends FootballMatch> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<FootballMatch> findById(Integer id) {
        for (FootballMatch footballMatch:
             db) {
            if(footballMatch.getId().equals((id))) {
                return Optional.of(footballMatch);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer id) {
        if(this.findById(id).isPresent()) {
            return true;
        }
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends FootballMatch> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends FootballMatch> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<FootballMatch> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public FootballMatch getOne(Integer integer) {
        return null;
    }

    @Override
    public FootballMatch getById(Integer integer) {
        return null;
    }

    @Override
    public <S extends FootballMatch> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends FootballMatch> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends FootballMatch> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends FootballMatch> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends FootballMatch> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends FootballMatch> boolean exists(Example<S> example) {
        return false;
    }
}
