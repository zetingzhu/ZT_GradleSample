package com.zt.java;

import static java.sql.DriverManager.println;

import com.android.build.api.transform.DirectoryInput;
import com.android.build.api.transform.Format;
import com.android.build.api.transform.JarInput;
import com.android.build.api.transform.QualifiedContent;
import com.android.build.api.transform.Transform;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformInvocation;
import com.android.build.api.transform.TransformOutputProvider;
import com.android.build.gradle.internal.pipeline.TransformManager;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

/**
 * @author: zeting
 * @date: 2022/4/12
 */
public class JavaTransform extends Transform {
    private static final String TAG = "ZZT-JavaTransform";

    @Override
    public String getName() {
        return TAG;
    }

    /**
     * public static final Set<ScopeType> EMPTY_SCOPES = ImmutableSet.of();
     * // 代表 javac 编译成的 class 文件，常用
     * public static final Set<ContentType> CONTENT_CLASS = ImmutableSet.of(CLASSES);
     * public static final Set<ContentType> CONTENT_JARS = ImmutableSet.of(CLASSES, RESOURCES);
     * // 这里的 resources 单指 java 的资源
     * public static final Set<ContentType> CONTENT_RESOURCES = ImmutableSet.of(RESOURCES);
     * public static final Set<ContentType> CONTENT_NATIVE_LIBS = ImmutableSet.of(NATIVE_LIBS);
     * public static final Set<ContentType> CONTENT_DEX = ImmutableSet.of(ExtendedContentType.DEX);
     * public static final Set<ContentType> CONTENT_DEX_WITH_RESOURCES = ImmutableSet.of(ExtendedContentType.DEX, RESOURCES);
     */
    @Override
    public Set<QualifiedContent.ContentType> getInputTypes() {
        return TransformManager.CONTENT_CLASS;
    }

    /**
     * public static final Set<ScopeType> PROJECT_ONLY = ImmutableSet.of(Scope.PROJECT);
     * public static final Set<ScopeType> SCOPE_FULL_PROJECT = ImmutableSet.of(Scope.PROJECT, Scope.SUB_PROJECTS, Scope.EXTERNAL_LIBRARIES);
     * public static final Set<ScopeType> SCOPE_FULL_WITH_FEATURES = new ImmutableSet.Builder<ScopeType>().addAll(SCOPE_FULL_PROJECT).add(InternalScope.FEATURES).build();
     * public static final Set<ScopeType> SCOPE_FEATURES = ImmutableSet.of(InternalScope.FEATURES);
     * public static final Set<ScopeType> SCOPE_FULL_LIBRARY_WITH_LOCAL_JARS = ImmutableSet.of(Scope.PROJECT, InternalScope.LOCAL_DEPS);
     * public static final Set<ScopeType> SCOPE_FULL_PROJECT_WITH_LOCAL_JARS = new ImmutableSet.Builder<ScopeType>().addAll(SCOPE_FULL_PROJECT).add(InternalScope.LOCAL_DEPS).build();
     */
    @Override
    public Set<? super QualifiedContent.Scope> getScopes() {
        return TransformManager.SCOPE_FULL_PROJECT;
    }

    @Override
    public boolean isIncremental() {
        return false;
    }

    @Override
    public void transform(TransformInvocation transformInvocation) throws TransformException, InterruptedException, IOException {
        super.transform(transformInvocation);
//        System.out.println(">>>>>>>>>>>>>> transform >>>>>>>>>>>>>>>>>");
//
//        //消费型输入，可以从中获取jar包和class文件夹路径。需要输出给下一个任务
//        Collection<TransformInput> inputs = transformInvocation.getInputs();
//        //引用型输入，无需输出。
//        Collection<TransformInput> referencedInputs = transformInvocation.getReferencedInputs();
//        //OutputProvider管理输出路径，如果消费型输入为空，你会发现OutputProvider == null
//        TransformOutputProvider outputProvider = transformInvocation.getOutputProvider();
//        for (TransformInput input : inputs) {
//
//            // 表示 Jar 包 getJarInputs
//            for (JarInput jarInput : input.getJarInputs()) {
//                println("[InjectTransform]  class 文件: " + jarInput.getFile().getAbsolutePath());
//
//                File dest = outputProvider.getContentLocation(
//                        jarInput.getFile().getAbsolutePath(),
//                        jarInput.getContentTypes(),
//                        jarInput.getScopes(),
//                        Format.JAR);
//                //将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
//                FileUtils.copyFile(jarInput.getFile(), dest);
//            }
//            // 表示目录，包含 class 文件 getDirectoryInputs
//            for (DirectoryInput directoryInput : input.getDirectoryInputs()) {
//                println("[InjectTransform]  class 文件: " + directoryInput.getFile().getAbsolutePath());
//
//                File dest = outputProvider.getContentLocation(directoryInput.getName(),
//                        directoryInput.getContentTypes(), directoryInput.getScopes(),
//                        Format.DIRECTORY);
//
//                println("[InjectTransform] Directory output dest: " + dest.getAbsolutePath());
//
//                //将修改过的字节码copy到dest，就可以实现编译期间干预字节码的目的了
//                FileUtils.copyDirectory(directoryInput.getFile(), dest);
//            }
//        }
    }

}
