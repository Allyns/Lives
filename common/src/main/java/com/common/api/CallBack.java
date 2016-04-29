package com.common.api;

import android.widget.Toast;

import com.common.EasyApp;
import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * author miekoz on 2016/3/17.
 * email  meikoz@126.com
 */
public abstract class CallBack<T> {
    public Type type;

    public CallBack() {
        type = getSuperclassTypeParameter(getClass());
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public abstract void onSuccess(T result);

    public void onFailure(String message) {
        Toast.makeText(EasyApp.getInstance(), message, Toast.LENGTH_SHORT).show();
    }
}
