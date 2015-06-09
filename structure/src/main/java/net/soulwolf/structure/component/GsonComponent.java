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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * author : Soulwolf Create by 2015/6/9 10:35
 * email  : ToakerQin@gmail.com.
 */
public class GsonComponent {

    public static Gson build(){
        GsonBuilder builder = new GsonBuilder();
        //builder.setFieldNamingStrategy(new SerializedName());
        return builder.create();
    }
}
