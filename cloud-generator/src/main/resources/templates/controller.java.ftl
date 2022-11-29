package ${package.Controller};

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.RequestMapping;
import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import com.cloud.common.base.config.StringConstant;
import com.cloud.common.base.excetion.CustomException;
import com.cloud.common.base.result.R;
import com.cloud.common.base.web.QueryVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.cloud.common.base.util.BeanCopy;
import lombok.extern.slf4j.Slf4j;
import com.cloud.common.persist.util.QueryUtils;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
@Slf4j
public class ${table.controllerName} {
</#if>

 @Resource
 private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
    * 保存
    * @param ${entity?uncap_first}Dto ${entity?uncap_first}Dto对象
    * @return
    */
    @PostMapping("/save")
    public R save(@RequestBody ${entity}Dto ${entity?uncap_first}Dto){
    log.info("save ${entity}");
    ${entity} ${entity?uncap_first} = BeanCopyUtil.copyBean(${entity?uncap_first}Dto, ${entity}.class);
    return R.status(${table.serviceName?uncap_first}.save(${entity?uncap_first}));
    }

    /**
    * 详情
    * @param ${entity?uncap_first}Dto ${entity?uncap_first}Dto对象
    * @return
    */
    @PostMapping("/detail")
    public R detail(@RequestBody ${entity}Dto ${entity?uncap_first}Dto) {
    log.info("detail ${entity}");
    return R.ok().data("${entity?uncap_first}Dto", ${table.serviceName?uncap_first}.getById(${entity?uncap_first}Dto.getId()));
    }

     /**
     * 删除
     *
     * @param ${entity?uncap_first}Dto ${entity?uncap_first}对象
     * @return
     */
     @PostMapping("/delete")
     public R delete(@RequestBody ${entity}Dto ${entity?uncap_first}Dto) {
     log.info("delete ${entity}");
     boolean success = ${table.serviceName?uncap_first}.removeById(${entity?uncap_first}.getId());
     if (!success) {
     throw new CustomException(StringConstant.DATA_VERSION_ERROR);
     }
     return R.ok();
     }

     /**
     * 批量删除
     *
     * @param paramMap map集合
     * @return
     */
     @PostMapping("/deleteBatch")
     public R deleteBatch(@RequestBody Map<String, Object> paramMap) {
     log.info("deleteBatch ${entity}");
     boolean success = ${table.serviceName?uncap_first}.removeByIds((List) paramMap.get("idList"));
     if (!success) {
     throw new CustomException(StringConstant.DATA_VERSION_ERROR);
     }
     return R.ok();
     }

     /**
     * 列表查询
     *
     * @return
     */
     @PostMapping("/list")
     public R dtoList() {
     log.info("list ${entity}");
     return R.ok().data("list", BeanCopyUtil.copyListProperties(${table.serviceName?uncap_first}.list(), ${entity}Dto::new));
     }

    /**
    * 分页查询
    * @param queryVo 查询条件
    * @return
    */
    @PostMapping("/page")
    public R detail(@RequestBody QueryVo queryVo) {
    log.info("page ${entity}");
    IPage<${entity}Dto> pages = ${table.serviceName?uncap_first}.getBaseMapper().dtoPage(
    QueryUtils.getPage(queryVo.getQuery()),
    QueryUtils.getQueryWrapper(queryVo.getParamMap(), ${entity}Dto.class));
     return R.ok().data("pages",pages);
     }

}
</#if>
