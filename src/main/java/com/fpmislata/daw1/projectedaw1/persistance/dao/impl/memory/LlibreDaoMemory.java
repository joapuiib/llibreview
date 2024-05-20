package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory;

import com.fpmislata.daw1.projectedaw1.domain.entity.Llibre;
import com.fpmislata.daw1.projectedaw1.persistance.dao.LlibreDao;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.LlibreTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.ValoracioTableMemory;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.LlibreRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.data.record.ValoracioRecord;
import com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper.LlibreMapper;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LlibreDaoMemory implements LlibreDao {

    private final LlibreTableMemory llibreTableMemory;
    private final ValoracioTableMemory valoracioTableMemory;
    private final LlibreMapper llibreMapper = new LlibreMapper();

    public LlibreDaoMemory(LlibreTableMemory llibreTableMemory, ValoracioTableMemory valoracioTableMemory) {
        this.llibreTableMemory = llibreTableMemory;
        this.valoracioTableMemory = valoracioTableMemory;
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
        List<ValoracioRecord> valoracioRecordList = valoracioTableMemory.get();
        Map<String, Integer> countMap = valoracioRecordList.stream()
                .collect(HashMap<String, Integer>::new, (map, valoracioRecord) -> {
                    map.put(valoracioRecord.getIsbn(), map.getOrDefault(valoracioRecord.getIsbn(), 0) + 1);
                }, Map::putAll);
        return llibreList.stream()
                .sorted((l1, l2) -> countMap.getOrDefault(l2.getIsbn(), 0).compareTo(countMap.getOrDefault(l1.getIsbn(), 0)))
                .limit(n)
                .toList();
    }

    @Override
    public List<Llibre> findBestRated(int n) {
        List<Llibre> llibreList = this.findAll();
        List<ValoracioRecord> valoracioRecordList = valoracioTableMemory.get();
        Map<String, Pair<Integer, Double>> averageValoracioMap = valoracioRecordList.stream()
                .collect(HashMap<String, Pair<Integer, Double>>::new, (map, valoracioRecord) -> {
                    Pair<Integer, Double> pair = map.getOrDefault(valoracioRecord.getIsbn(), new Pair<>(0, 0.0));
                    map.put(valoracioRecord.getIsbn(), new Pair<>(pair.getValue0() + 1, (pair.getValue1() * pair.getValue0() + valoracioRecord.getValoracio()) / (pair.getValue0() + 1)));
                }, Map::putAll);
        return llibreList.stream()
                .sorted((l1, l2) -> averageValoracioMap.getOrDefault(l2.getIsbn(), new Pair<>(0, 0.0)).getValue1().compareTo(averageValoracioMap.getOrDefault(l1.getIsbn(), new Pair<>(0, 0.0)).getValue1()))
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
