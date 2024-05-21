package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class Mapper<A, B> {
    abstract B map(A a);
    public List<B> map(List<A> aList){
        List<B> bList = new ArrayList<>();
        for(A a : aList){
            bList.add(map(a));
        }
        return bList;
    }
}
