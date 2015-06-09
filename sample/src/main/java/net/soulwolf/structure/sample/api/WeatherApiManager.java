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
package net.soulwolf.structure.sample.api;

import net.soulwolf.structure.sample.model.WeatherData;

import java.util.concurrent.Callable;

import retrofit.RestAdapter;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.AndroidSubscriptions;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.observers.Observers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * author : Soulwolf Create by 2015/6/8 15:33
 * email  : ToakerQin@gmail.com.
 */
public class WeatherApiManager {

    private static final RestAdapter restAdapter = new RestAdapter.Builder()
            .setEndpoint("http://api.openweathermap.org/data/2.5")
            .build();

    private static final WeatherApiService apiManager = restAdapter.create(WeatherApiService.class);

    public static Observable<WeatherData> getWeatherData(final String city,final Callable<WeatherData> callable) {
//        return Observable.create(new Observable.OnSubscribe<WeatherData>() {
//            @Override
//            public void call(Subscriber<? super WeatherData> subscriber) {
//                try {
//                    subscriber.onNext(apiManager.getWeather(city, "metric",callable));
//                    subscriber.onCompleted();
//                } catch (Exception e) {
//                    subscriber.onError(e);
//                }
//            }
//        }).subscribeOn(Schedulers.newThread());
        Observable.create(new Observable.OnSubscribe<WeatherData>() {
            @Override
            public void call(Subscriber<? super WeatherData> subscriber) {
                try {
                    subscriber.onNext(apiManager.getWeather(city,"metric",callable));
                    subscriber.onCompleted();
                }catch (Exception e){
                    subscriber.onError(e);
                }
            }
        }).subscribe(data -> System.out.println(data.toString()));
        return null;
    }
}
