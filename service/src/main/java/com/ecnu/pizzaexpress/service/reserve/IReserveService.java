package com.ecnu.pizzaexpress.service.reserve;

public interface IReserveService {

  int createMaterial(String material, int number, int factoryId);

  int addReserve(int number, int factoryId, int materialId, String material);

  int deleteMaterial(int factoryId, String material);

  int getMaterialInfo(int factoryId, String material);

  int decreaseReserve(int number, int factoryId, String material);

  int getReserve(int factoryId, int materialId);
}
