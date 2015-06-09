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

import com.toaker.common.tlog.TLog;

import net.soulwolf.structure.AppStructure;

import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * author : Soulwolf Create by 2015/6/9 14:54
 * email  : ToakerQin@gmail.com.
 */
public class RetrofitComponent {

    private static final String LOG_TAG = "RetrofitComponent:";

    private static RestAdapter mRestAdapter;

    public static <T> T buildService(Class<T> servie){
        if(mRestAdapter == null){
            synchronized (RetrofitComponent.class){
                if(mRestAdapter == null){
                    RestAdapter.Builder builder = new RestAdapter.Builder();
                    builder.setClient(new OkClient(OkHttpComponent.build()));
                    builder.setConverter(new GsonConverter(GsonComponent.build()));
                    builder.setEndpoint(Endpoints.newFixedEndpoint(AppStructure.getInstance().getServer()));
                    builder.setLogLevel(AppStructure.isDebug() ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE);
                    mRestAdapter = builder.build();
                    if(AppStructure.isDebug()){
                        TLog.i(LOG_TAG,"RetrofitComponent initialize!");
                    }
                }
            }
        }
        return mRestAdapter.create(servie);
    }
}
