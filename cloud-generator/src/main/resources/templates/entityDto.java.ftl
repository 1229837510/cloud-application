package ${package.Other};

<#list table.importPackages as pkg>
    import ${pkg};
</#list>

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
* <p>
    * DTO
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Data

public class ${entity}DTO implements Serializable {
private static final long serialVersionUID = 1L;
<#-- ----------  BEGIN 字段循环遍历  ---------->
<#list table.fields as field>
    private ${field.propertyType} ${field.propertyName};
</#list>
<#------------  END 字段循环遍历  ---------->

}