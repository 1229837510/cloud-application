package ${package.Other};

import com.cloud.common.base.web.AbsVo;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
* <p>
*   ${entity}Dto
* </p>
*
* @author ${author}
* @since ${date}
*/
@Data
public class ${entity}Dto extends AbsVo {

    private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    /**
    * ${field.comment}
    */
    private ${field.propertyType} ${field.propertyName};

</#list>
<#------------  END 字段循环遍历  ---------->

}