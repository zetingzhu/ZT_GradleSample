package com.zt.gradle;

import com.android.build.api.instrumentation.AsmClassVisitorFactory;
import com.android.build.api.instrumentation.ClassContext;
import com.android.build.api.instrumentation.ClassData;
import com.android.build.api.instrumentation.InstrumentationParameters;

import org.objectweb.asm.ClassVisitor;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

/**
 * @author: zeting
 * @date: 2023/10/23
 */
public abstract class NewThreadVisitorFactory implements AsmClassVisitorFactory<InstrumentationParameters.None> {

    private static final String TAG = "ASM-Factory";

    @NotNull
    @Override
    public ClassVisitor createClassVisitor(@NotNull ClassContext classContext, @NotNull ClassVisitor classVisitor) {
        System.out.println(TAG + ">>>>>>  createClassVisitor");
        return new NewThreadClassVisitor(classVisitor);
    }

    @Override
    public boolean isInstrumentable(@NotNull ClassData classData) {
        String className = classData.getClassName();
        if (className.contains("asmtest") || className.startsWith("com.zzt.gradlesample.")) {
            return true;
        }
        return false;
    }
}