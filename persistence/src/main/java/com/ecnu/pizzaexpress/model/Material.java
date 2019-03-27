package com.ecnu.pizzaexpress.model;

import com.ecnu.pizzaexpress.constants.MaterialStatus;
import lombok.Data;

@Data
public class Material {

  private int id;

  private String name;

  private int threshold;

  private MaterialStatus status;

}