package com.king.generator;

import org.mybatis.generator.api.ShellRunner;

public class MysqlGeneratorConfig {
	
 public	static void main(String[] args) {

	 System.out.println(MySQLPaginationPlugin.class.getClassLoader());
        String config= MySQLPaginationPlugin.class.getClassLoader().getResource("generatorConfig.xml").getFile();

        String[] arg= { "-configfile", config, "-overwrite"};

        ShellRunner.main(arg);

 }

}
