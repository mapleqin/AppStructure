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
package net.soulwolf.structure;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.toaker.common.tlog.TLog;

import net.soulwolf.structure.component.OkHttpComponent;
import net.soulwolf.structure.config.AppStructureConfig;

import java.io.File;

/**
 * author : Soulwolf Create by 2015/6/8 11:20
 * email  : ToakerQin@gmail.com.
 */
public final class AppStructure {

    private static boolean isDebug;

    private static final String LOG_TAG = "AppStructure:";

    private static AppStructure mAppStructure;

    private Context mContext;

    private int mScreenWidth;

    private int mScreenHeight;

    private AppStructureConfig mAppStructureConfig;

    public static void init(AppStructureConfig config){
        if(config == null){
            throw new IllegalArgumentException("AppStructure AppStructureConfig initialization parameters can not be NULL!");
        }
        isDebug = config.isDebug();
        if(mAppStructure == null){
            synchronized (AppStructure.class){
                if(mAppStructure == null){
                    mAppStructure = new AppStructure(config);
                }
            }
        }
        OkHttpComponent.init(config.getHttpConfig());
        TLog.i(LOG_TAG,"AppStructure init!");
    }

    public static AppStructure getInstance(){
        if(mAppStructure == null){
            throw new IllegalStateException("AppStructure uninitialized!");
        }
        return mAppStructure;
    }

    public static boolean isDebug(){
        return isDebug;
    }

    AppStructure(AppStructureConfig config){
        this.mAppStructureConfig = config;
        this.mContext = config.getContext();
        DisplayMetrics metrics = new DisplayMetrics();
        ((WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getMetrics(metrics);
        this.mScreenWidth = metrics.widthPixels;
        this.mScreenHeight = metrics.heightPixels;
    }

    public Context getContext(){
        return mContext;
    }

    public int getScreenWidth(){
        return mScreenWidth;
    }

    public int getScreenHeight(){
        return mScreenHeight;
    }

    public File getCacheDir(){
        return mContext.getCacheDir();
    }

    public String getServer(){
        if(mAppStructureConfig != null){
            return mAppStructureConfig.getServerUrl();
        }
        return null;
    }

    public AppStructureConfig getAppStructureConfig() {
        return mAppStructureConfig;
    }
}
