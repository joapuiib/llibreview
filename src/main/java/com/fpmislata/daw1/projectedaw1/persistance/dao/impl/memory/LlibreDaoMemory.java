package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.RatingTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.RatingRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.LlibreMapper;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LlibreDaoMemory implements LlibreDao {

    private final LlibreTableMemory llibreTableMemory;
    private final RatingTableMemory ratingTableMemory;
    private final LlibreMapper llibreMapper = new LlibreMapper();

    public LlibreDaoMemory(LlibreTableMemory llibreTableMemory, RatingTableMemory ratingTableMemory) {
        this.llibreTableMemory = llibreTableMemory;
        this.ratingTableMemory = ratingTableMemory;
    }

    @Override
    public List<Llibre> findAll() {
        List<LlibreRecord> llibreRecordList = llibreTableMemory.get();
        return llibreMapper.map(llibreRecordList);
    }

    @Override
    public Llibre findByIsbn(String isbn) {
        LlibreRecord llibreRecord = llibreTableMemory.get().stream()
                .filter(llibreRecord1 -> llibreRecord1.getIsbn().equals(isbn))
                .findFirst()
                .orElse(null);

        if (llibreRecord == null) {
            return null;
        }
        return llibreMapper.map(llibreRecord);
    }

    @Override
    public List<Llibre> findLatest(int n) {
        List<Llibre> llibreList = this.findAll();
        llibreList.sort((l1, l2) -> l2.getDataPublicacio().compareTo(l1.getDataPublicacio()));
        n = Math.min(n, llibreList.size());
        return llibreList.subList(0, n);
    }

    @Override
    public List<Llibre> findMostRead(int n) {
        List<Llibre> llibreList = this.findAll();
        List<RatingRecord> ratingRecordList = ratingTableMemory.get();
        Map<String, Integer> countMap = ratingRecordList.stream()
                .collect(HashMap<String, Integer>::new, (map, ratingRecord) -> {
                    map.put(ratingRecord.getIsbn(), map.getOrDefault(ratingRecord.getIsbn(), 0) + 1);
                }, Map::putAll);
        return llibreList.stream()
                .sorted((l1, l2) -> countMap.getOrDefault(l2.getIsbn(), 0).compareTo(countMap.getOrDefault(l1.getIsbn(), 0)))
                .limit(n)
                .toList();
    }

    @Override
    public List<Llibre> findBestRated(int n) {
        List<Llibre> llibreList = this.findAll();
        List<RatingRecord> ratingRecordList = ratingTableMemory.get();
        Map<String, Pair<Integer, Double>> averageRatingMap = ratingRecordList.stream()
                .collect(HashMap<String, Pair<Integer, Double>>::new, (map, ratingRecord) -> {
                    Pair<Integer, Double> pair = map.getOrDefault(ratingRecord.getIsbn(), new Pair<>(0, 0.0));
                    map.put(ratingRecord.getIsbn(), new Pair<>(pair.getValue0() + 1, (pair.getValue1() * pair.getValue0() + ratingRecord.getRating()) / (pair.getValue0() + 1)));
                }, Map::putAll);
        return llibreList.stream()
                .sorted((l1, l2) -> averageRatingMap.getOrDefault(l2.getIsbn(), new Pair<>(0, 0.0)).getValue1().compareTo(averageRatingMap.getOrDefault(l1.getIsbn(), new Pair<>(0, 0.0)).getValue1()))
                .limit(n)
                .toList();
    }

    @Override
    public boolean insert(Llibre llibre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Llibre delete(String isbn) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
