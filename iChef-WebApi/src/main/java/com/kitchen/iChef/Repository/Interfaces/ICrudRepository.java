package com.kitchen.iChef.Repository.Interfaces;

import java.util.List;


public interface ICrudRepository<E, ID> {

    E findOne(ID id);

    List<E> findAll();

    E save(E entity);

    E delete(ID id);

    E update(E entity);

}
