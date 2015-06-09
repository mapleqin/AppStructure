/**
 * <pre>
 * Copyright 2014-2019 Soulwolf AppStructure
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </pre>
 */
package net.soulwolf.structure.component;

import android.content.Context;
import android.util.Config;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.toaker.common.tlog.TLog;

import net.soulwolf.structure.AppStructure;
import net.soulwolf.structure.config.AppStructureConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * author : Soulwolf Create by 2015/6/9 10:38
 * email  : ToakerQin@gmail.com.
 */
public class OkHttpComponent {

    private static final String LOG_TAG = "OkHttpComponent:";

    private static OkHttpClient mOkHttpClient;

    public static void init(AppStructureConfig.HttpConfig config){
        if(config == null){
            throw new IllegalArgumentException("OkHttpComponent HttpConfig initialization parameters can not be NULL!");
        }
        if(mOkHttpClient == null){
            synchronized (OkHttpComponent.class){
                if(mOkHttpClient == null){
                    File cacheDir = new File(AppStructure.getInstance().getCacheDir(), config.getCacheDir());
                    mOkHttpClient = new OkHttpClient();
                    mOkHttpClient.setCache(new Cache(cacheDir, config.getCacheSize()));
                    mOkHttpClient.setConnectTimeout(config.getConnectTimeout(), TimeUnit.MILLISECONDS);
                    mOkHttpClient.setReadTimeout(config.getReadTimeout(), TimeUnit.MILLISECONDS);
                    if(AppStructure.isDebug()){
                        TLog.i(LOG_TAG,"OkHttpComponent initialization!");
                    }
                }
            }
        }
    }

    public static  OkHttpClient build(){
        if(mOkHttpClient == null){
            throw new IllegalStateException("OkHttpComponent not be initialize!");
        }
        return mOkHttpClient;
    }
}
