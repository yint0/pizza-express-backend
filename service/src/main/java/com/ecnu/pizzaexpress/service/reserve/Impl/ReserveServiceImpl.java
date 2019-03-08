package com.ecnu.pizzaexpress.service.reserve.Impl;

import com.ecnu.pizzaexpress.service.reserve.IReserveService;

public class ReserveServiceImpl implements IReserveService {

  @Override
  public int createMaterial(String material, int number, int factoryId) {
    return 0;
  }

  @Override
  public int addReserve(int number, int factoryId, int materialId, String material) {
    return 0;
  }

  @Override
  public int deleteMaterial(int factoryId, String material) {
    return 0;
  }

  @Override
  public int getMaterialInfo(int factoryId, String material) {
    return 0;
  }

  @Override
  public int decreaseReserve(int number, int factoryId, String material) {
    return 0;
  }

  @Override
  public int getReserve(int factoryId, int materialId) {
    return 0;
  }
}
