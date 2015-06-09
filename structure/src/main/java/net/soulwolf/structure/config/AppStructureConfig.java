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
package net.soulwolf.structure.config;

import android.content.Context;

import com.toaker.common.tlog.TLog;

import net.soulwolf.structure.AppStructure;

/**
 * author : Soulwolf Create by 2015/6/9 11:05
 * email  : ToakerQin@gmail.com.
 */
public class AppStructureConfig {

    private static final String LOG_TAG = "AppStructureConfig:";

    private Context mContext;

    private HttpConfig mHttpConfig;

    private String mServerUrl;

    private boolean isDebug = true;

    private AppStructureConfig(Context context){
        this.mContext = context;
    }

    public static AppStructureConfig create(Context context){
        if(context == null){
            throw new IllegalArgumentException("AppStructureConfig Context initialization parameters can not be NULL!");
        }
        if(AppStructure.isDebug()){
            TLog.i(LOG_TAG,"AppStructureConfig create!");
        }
        return new AppStructureConfig(context);
    }

    public AppStructureConfig setHttpConfig(HttpConfig config){
        this.mHttpConfig = config;
        return this;
    }

    public AppStructureConfig setServerUrl(String serverUrl){
        this.mServerUrl = serverUrl;
        return this;
    }

    public AppStructureConfig setDebug(boolean debug){
        this.isDebug = debug;
        return this;
    }

    public boolean isDebug(){
        return isDebug;
    }

    public Context getContext() {
        return mContext;
    }

    public HttpConfig getHttpConfig() {
        return mHttpConfig;
    }

    public String getServerUrl() {
        return mServerUrl;
    }

    public static class HttpConfig{

        private String mCacheDir = "ok-http";

        private long   mCacheSize = 1024 * 1024 * 8;

        private long   mConnectTimeout = 8000;

        private long   mReadTimeout    = 5000;

        public static HttpConfig create(){
            return new HttpConfig();
        }

        public HttpConfig setCacheDirName(String dirName){
            this.mCacheDir = dirName;
            return this;
        }

        public HttpConfig setCacheSize(long maxSize){
            this.mCacheSize = maxSize;
            return this;
        }

        public HttpConfig setReadTimeout(long readTimeout){
            this.mReadTimeout = readTimeout;
            return this;
        }

        public HttpConfig setConnectTimeout(long connectTimeout){
            mConnectTimeout = connectTimeout;
            return this;
        }

        public String getCacheDir() {
            return mCacheDir;
        }

        public long getCacheSize() {
            return mCacheSize;
        }

        public long getConnectTimeout() {
            return mConnectTimeout;
        }

        public long getReadTimeout() {
            return mReadTimeout;
        }
    }
}
