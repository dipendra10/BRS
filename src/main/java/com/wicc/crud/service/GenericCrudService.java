package com.wicc.crud.service;


import java.io.IOException;
import java.util.List;

public interface GenericCrudService<D , ID>{

    D save (D d) throws IOException;
    List<D> findAll();
    D findById(ID id) throws IOException;

    D viewById(ID id) throws IOException;

    void deleteById(ID id);
}
