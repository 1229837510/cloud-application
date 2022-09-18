import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.cloud.common.base.web.AbsPo;
import com.cloud.common.base.web.BaseEntity;
import org.omg.CORBA.MARSHAL;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Generator {
    private static final String name = "movie";
    private static final String commonPath = "D:\\common-generator\\";
    private static final boolean isBasePo = false;

    public static void main(String[] args) {
        String[] tables = new String[]{"sys_user", "result"};
        Map<OutputFile, String> pathInfo = new HashMap<>();
        pathInfo.put(OutputFile.entity, commonPath + name + "\\po");
        pathInfo.put(OutputFile.service, commonPath + name + "\\service");
        pathInfo.put(OutputFile.mapper, commonPath + name + "\\mapper");
        pathInfo.put(OutputFile.controller, commonPath + name + "\\controller");
        pathInfo.put(OutputFile.xml, commonPath + name + "\\xml");
        pathInfo.put(OutputFile.other, commonPath + name + "\\other");
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/movies?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("fangcy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir("D://common-generator")// 指定输出目录
                            .commentDate("yyyy-MM-dd");
                })
                .packageConfig(builder -> {
                    builder.parent("com.cloud")
                            .controller(name + ".controller")
                            .mapper(name + ".mapper")
                            .service(name + ".service")
                            .entity("common." + name + ".po")
                            .other("common." + name + ".dto")
                            .pathInfo(pathInfo).build();
                })
                .strategyConfig(builder -> {
                    //builder.addInclude("admin_user","movies","person","rate","relation","result","sys_user","movie_tags") // 设置需要生成的表名
                    builder.addInclude(tables) // 设置需要生成的表名
                            .addTablePrefix("sys_", "c_", "t_")
                            // entity
                            .entityBuilder()
                            .fileOverride()
                            .superClass(isBasePo ? BaseEntity.class : AbsPo.class)
                            .addSuperEntityColumns("create_time", "create_id", "update_time", "update_id", "deleted")
                            // 忽略的列
                            .addIgnoreColumns("create_time", "create_id", "update_time", "update_id", "deleted")
                            .logicDeleteColumnName("deleted")
                            .enableLombok()

                            // service
                            .serviceBuilder()
                            .fileOverride()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            // controller
                            .controllerBuilder()
                            .fileOverride()
                            // 映射路径使用连字符格式，而不是驼峰
                            .enableHyphenStyle()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .fileOverride()
                            .mapperBuilder()
                            //生成通用的resultMap
                            .enableBaseResultMap()
                            //   .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");

                })
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    consumer.beforeOutputFile(((tableInfo, map) -> {
                        customFile.put(tableInfo.getEntityName() + "Dto.java", "templates/entityDto.java.ftl");
                    }));
                    consumer.fileOverride();
                    consumer.customFile(customFile);
                })
                .templateConfig(new Consumer<TemplateConfig.Builder>() {
                    @Override
                    public void accept(TemplateConfig.Builder builder) {
                        // 实体类使用我们自定义模板
                        builder.entity("templates/entity.java");
                        builder.controller("templates/controller.java");
                        builder.mapper("templates/mapper.java");
                        builder.xml("templates/mapper.xml");
                        builder.service("templates/service.java");
                        builder.serviceImpl(null);
                    }
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
