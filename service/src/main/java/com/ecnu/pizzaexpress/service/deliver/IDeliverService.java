package com.ecnu.pizzaexpress.service.deliver;

public interface IDeliverService {

  int chooseFactoryList(String factoryAddress, String userAddress);

  int calculateTimeSpend(String factoryAddress, String userAddress);

  int checkDeliverStaff(int factoryId);

}
