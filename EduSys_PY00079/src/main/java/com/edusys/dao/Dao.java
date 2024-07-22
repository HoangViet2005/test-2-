/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.edusys.dao;

import java.util.List;

public abstract class Dao<EntityType, KeyType> {
    public abstract void insert(EntityType entiTy);
    public abstract void update(EntityType entiTy);
    public abstract void delete(KeyType id);
    public abstract List<EntityType> selectAll();
    public abstract EntityType selectByID(KeyType id);
    public abstract List<EntityType> selectBySql(String sql, Object...args);
    
}
