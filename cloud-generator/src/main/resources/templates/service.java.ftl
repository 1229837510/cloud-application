package ${package.Service};

import ${package.Entity}.${entity};
import com.cloud.common.persist.MyBaseService;
import ${package.Mapper}.${table.mapperName};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if kotlin>
 class ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public class ${table.serviceName} extends MyBaseService<${table.mapperName},${entity}> {

}
</#if>
