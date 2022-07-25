import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * @author HuJiangTao
 * @create 2022/7/24 23:27
 */
public class Test {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/reggie?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true",
                        "root", "123456")
                .globalConfig(builder -> {
                    builder.author("HJT") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir("C:\\Users\\14437\\Desktop\\2"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.itheima.reggie") // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, "C:\\Users\\14437\\Desktop\\2")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("shopping_cart") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
