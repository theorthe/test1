package test.zsc.management.mapper;

import org.apache.ibatis.annotations.Mapper;
import test.zsc.management.pojo.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /**
     * 批量插入员工工作经历信息
     */
    public void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工的ID批量删除工作经历信息
     */
    void deleteByEmpIds(List<Integer> empIds);
}
