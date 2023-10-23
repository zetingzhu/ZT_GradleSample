package com.zt.gradle

import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformException
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import org.gradle.api.Plugin
import org.gradle.api.Project

class ZTPluginT1 extends Transform implements Plugin<Project> {
    @Override
    void apply(Project project) {
        System.out.println("============开始============");
        println "11111111111"
        project.android.registerTransform(this)
        System.out.println("============结束============");
    }


    @Override
    String getName() {
        return "AAAa"
    }

    @Override
    Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    @Override
    Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    boolean isIncremental() {
        return true
    }

    @Override
    void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        System.out.println("============transform============");
        transformInvocation.inputs.each { input ->
            System.out.println("============ transformInvocation.inputs ============");
            // 包含我们手写的 Class 类及 R.class、BuildConfig.class 等
            input.directoryInputs.each { directoryInput ->
                if (directoryInput.file.isDirectory()) {
                    System.out.println("============ 文件夹")
                    String path = directoryInput.file.absolutePath
                    System.out.println("[InjectTransform] Begin to inject: $path")
                } else if (directoryInput.file.isFile()) {
                    System.out.println("============ 文件 ")
                }

                // 获取输出目录
                def dest = transformInvocation.outputProvider.getContentLocation(directoryInput.name,
                        directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
                System.out.println("[InjectTransform] Directory output dest: $dest.absolutePath")

                // 将input的目录复制到output指定目录
                FileUtils.copyDirectory(directoryInput.file, dest)
            }

            // jar文件，如第三方依赖
            input.jarInputs.each { jarInput ->
                def dest = transformInvocation.outputProvider.getContentLocation(jarInput.name,
                        jarInput.contentTypes, jarInput.scopes, Format.JAR)
                FileUtils.copyFile(jarInput.file, dest)
            }
        }
    }
}