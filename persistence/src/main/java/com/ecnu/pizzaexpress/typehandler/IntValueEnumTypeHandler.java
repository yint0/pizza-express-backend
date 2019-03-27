package com.ecnu.pizzaexpress.typehandler;

import com.ecnu.pizzaexpress.global.IntValueEnum;
import com.google.common.base.Preconditions;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class IntValueEnumTypeHandler<E extends Enum<E> & IntValueEnum<E>> extends
    BaseTypeHandler<E> {

  private final Class<IntValueEnum<E>> type;

  public IntValueEnumTypeHandler(Class<IntValueEnum<E>> type) {
    if (type == null) {
      throw new IllegalArgumentException("Type argument cannot be null");
    }
    this.type = type;
  }

  @Override
  public void setNonNullParameter(
      PreparedStatement preparedStatement, int i, E e, JdbcType jdbcType) throws SQLException {
    preparedStatement.setInt(i, e.getValue());
  }

  @SuppressWarnings("unchecked")
  private E convert(int input) {
    IntValueEnum<E>[] enumConstants = Preconditions.checkNotNull(type.getEnumConstants());
    for (IntValueEnum<E> enumConstant : enumConstants) {
      if (enumConstant.getValue() == input) {
        return (E) enumConstant;
      }
    }
    return null;
  }

  @Override
  public E getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
    return convert(resultSet.getInt(columnName));
  }

  @Override
  public E getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
    return convert(resultSet.getInt(columnIndex));
  }

  @Override
  public E getNullableResult(CallableStatement callableStatement, int columnIndex)
      throws SQLException {
    return convert(callableStatement.getInt(columnIndex));
  }
}
