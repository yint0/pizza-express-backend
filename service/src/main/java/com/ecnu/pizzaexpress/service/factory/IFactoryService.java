package com.ecnu.pizzaexpress.service.factory;

public interface IFactoryService {

  int createFactory(int factoryId, String factoryAddress);

  int modifyFactoryInfo(int factoryId, String factoryAddress, String factoryStatus);

  int getFactoryInfo(int factoryId, String factoryAddress, String factoryStatus);

  int modifyFactorySta(int factoryId, String factoryStatus);

  int getFactoryList();
}
