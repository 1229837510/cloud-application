import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.cloud.common.base.web.BaseEntity;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Generator {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/movies?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("xiaofang") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("D://common-generator"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.cloud.common") // 设置父包名
                            .moduleName("movie") // 设置父包模块名
                            .entity("po")
                           // .pathInfo(Collections.singletonMap(OutputFile.controller,""))
                         //   .other("admin.dto")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D://common-generator")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    //builder.addInclude("admin_user","movies","person","rate","relation","result","sys_user","movie_tags") // 设置需要生成的表名
                   builder.addInclude("sys_user") // 设置需要生成的表名
                            .addTablePrefix("sys_", "c_")
                            // entity
                            .entityBuilder()
                            .superClass(BaseEntity.class)
                            .addSuperEntityColumns("create_time","create_id","update_time","update_id","deleted")
                            // 忽略的列
                            .addIgnoreColumns("create_time","create_id","update_time","update_id","deleted")
                            .logicDeleteColumnName("deleted")
                            .enableLombok()
                           .fileOverride()
                            .entityBuilder()
                            // service
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .logicDeleteColumnName("deleted")
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            // 映射路径使用连字符格式，而不是驼峰
                            .enableHyphenStyle()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            //生成通用的resultMap
                            .enableBaseResultMap()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");

                })
                .injectionConfig(consumer -> {

                    Map<String, String> customFile = new HashMap<>();
                    // DTO
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
