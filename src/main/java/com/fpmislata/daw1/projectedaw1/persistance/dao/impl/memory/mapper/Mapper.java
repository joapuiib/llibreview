package com.fpmislata.daw1.projectedaw1.persistance.dao.impl.memory.mapper;

import java.util.List;

public interface Mapper<A, B> {
    B map(A a);
    List<B> map(List<A> aList);
}
