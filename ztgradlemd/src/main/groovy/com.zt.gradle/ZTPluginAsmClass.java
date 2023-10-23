package com.zt.gradle;

import com.android.build.api.instrumentation.FramesComputationMode;
import com.android.build.api.instrumentation.InstrumentationScope;
import com.android.build.api.variant.AndroidComponentsExtension;
import com.android.build.api.variant.Component;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author: zeting
 * @date: 2023/10/23
 * AsmClassVisitorFactory 简单使用
 */
public class ZTPluginAsmClass implements Plugin<Project> {
     private static final String TAG = "ASM-plugin" ;
    @Override
    public void apply(Project target) {
        System.out.println(TAG+">>>>>>  apply 开始");
        AndroidComponentsExtension comp = target.getExtensions().getByType(AndroidComponentsExtension.class);
        comp.onVariants(comp.selector().all(), new Action<Component>() {
            @Override
            public void execute(Component variant) {
                System.out.println(TAG+">>>>>>  execute variant");
                variant.transformClassesWith(NewThreadVisitorFactory.class, InstrumentationScope.ALL, v -> null);
                variant.setAsmFramesComputationMode(FramesComputationMode.COMPUTE_FRAMES_FOR_INSTRUMENTED_METHODS);
            }
        });

        System.out.println(TAG+">>>>>>  apply 结束");
    }
}
