package com.zt.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class ZTPlugin implements Plugin<Project> {
    @Override
    void apply(Project project) {
        project.task('ZZTTask') {
            println "Hello gradle plugin ZTPlugin"
            doLast {
                println "大家好,我是一个自定义插件，在这里写下你的具体功能"
            }
        }
        System.out.println("============开始============");
//        AppExtension android = (AppExtension) project.getExtensions().getByType(AppExtension.class);
//        android.registerTransform(new JavaTransformT1(project));
        System.out.println("============结束============");
    }
}