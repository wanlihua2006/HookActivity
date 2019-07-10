package com.example.wlh.hookactivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class InStrumentationProxy extends Instrumentation {
    private final Instrumentation mInstrumentation;

    public InStrumentationProxy(Instrumentation instrumentation) {
        //Toast.makeText(MainActivity.this,"hook ok",Toast.LENGTH_SHORT).show();
        mInstrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
                                            Intent intent, int requestCode, Bundle options) {
        //Toast.makeText(MainActivity.this,"hook ativtiy sucess -- who " + who);
        Log.w("InStrumentationProxy","wanlihua debug hook ativtiy sucess -- who " + who);

        try {
            Method execStartActivity = Instrumentation.class.getDeclaredMethod("execStartActivity",Context.class,IBinder.class,
                    IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
            return (ActivityResult)execStartActivity.invoke(mInstrumentation,who,contextThread,token,target,intent,requestCode,options);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
