package com.ecnu.pizzaexpress.model;

import java.util.Date;
import lombok.Data;

@Data
public class MaterialLog {

  private int id;

  private int adminId;

  private int materialId;

  private int factoryId;

  private Date operateTime;

  private String content;
}