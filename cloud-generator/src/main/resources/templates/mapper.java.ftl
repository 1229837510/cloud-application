package ${package.Mapper};

import ${package.Entity}.${entity};
import com.cloud.common.persist.MyBaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Param;
<#if mapperAnnotation>
import org.apache.ibatis.annotations.Mapper;
</#if>

/**
 * <p>
 * ${table.comment!} Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if mapperAnnotation>
@Mapper
</#if>
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends MyBaseMapper<${entity}> {

  /**
  * 分页查询
  * @param page 分页
  * @param queryWrapper 查询条件
  * @return
  */
  IPage<${entity}Dto> dtoPage(IPage<?> page, @Param(Constants.WRAPPER) Wrapper<${entity}Dto> queryWrapper);
}
</#if>
